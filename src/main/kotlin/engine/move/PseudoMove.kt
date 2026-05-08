package engine.move

import engine.Piece
import engine.Square

/**
 * A move encoded into a single 64-bit word — `@JvmInline value class`, so
 * no boxing in non-collection contexts. Layout:
 *
 *  - bits  0..5  : `from` square ordinal (6 bits)
 *  - bits  6..11 : `to` square ordinal   (6 bits)
 *  - bits 12..19 : piece char (8 bits)
 *  - bits 20..27 : promotion char (8 bits, `0` for no promotion)
 *
 * Use the `[Square, Square, Char]` factory or [pack] to construct; reading
 * properties unpacks on demand.
 */
@JvmInline
value class PseudoMove(val packed: Long) {
    val from: Square get() = Square[(packed and 0x3FL).toInt()]
    val to: Square get() = Square[((packed shr 6) and 0x3FL).toInt()]
    val piece: Char get() = ((packed shr 12) and 0xFFL).toInt().toChar()
    val promo: Char?
        get() {
            val code = ((packed shr 20) and 0xFFL).toInt()
            return if (code == 0) null else code.toChar()
        }
    val fromBit: ULong get() = 1UL shl (packed and 0x3FL).toInt()
    val toBit: ULong get() = 1UL shl ((packed shr 6) and 0x3FL).toInt()

    companion object {
        fun pack(from: Square, to: Square, piece: Char, promo: Char? = null): Long =
            from.ordinal.toLong() or
                (to.ordinal.toLong() shl 6) or
                (piece.code.toLong() shl 12) or
                ((promo?.code?.toLong() ?: 0L) shl 20)

        operator fun invoke(from: Square, to: Square, piece: Char, promo: Char? = null): PseudoMove =
            PseudoMove(pack(from, to, piece, promo))

        fun getPromoMoves(from: Square, to: Square, piece: Char): List<PseudoMove> = when (piece) {
            Piece.wPawn -> listOf(
                invoke(from, to, piece, 'Q'),
                invoke(from, to, piece, 'R'),
                invoke(from, to, piece, 'B'),
                invoke(from, to, piece, 'N'),
            )
            Piece.bPawn -> listOf(
                invoke(from, to, piece, 'q'),
                invoke(from, to, piece, 'r'),
                invoke(from, to, piece, 'b'),
                invoke(from, to, piece, 'n'),
            )
            else -> throw IllegalArgumentException("getPromoMoves can only be used with pawns")
        }
    }
}
