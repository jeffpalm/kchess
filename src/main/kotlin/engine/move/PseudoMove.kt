package engine.move

import engine.Square
import engine.Piece

data class PseudoMove(val from: Square, val to: Square, val piece: Char, val promo: Char? = null) {
    val fromBit: ULong = from.asBit()
    val toBit: ULong = to.asBit()

    companion object {
        fun getPromoMoves(from: Square, to: Square, piece: Char): List<PseudoMove> = when (piece) {
            Piece.wPawn -> listOf(
                PseudoMove(from, to, piece, 'Q'),
                PseudoMove(from, to, piece, 'R'),
                PseudoMove(from, to, piece, 'B'),
                PseudoMove(from, to, piece, 'N'),
            )
            Piece.bPawn -> listOf(
                PseudoMove(from, to, piece, 'q'),
                PseudoMove(from, to, piece, 'r'),
                PseudoMove(from, to, piece, 'b'),
                PseudoMove(from, to, piece, 'n'),
            )
            else -> throw IllegalArgumentException("getPromoMoves can only be used with pawns")
        }
    }
}
