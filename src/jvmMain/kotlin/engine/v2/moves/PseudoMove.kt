package engine.v2.moves

import engine.SquareMap
import engine.v2.Piece
import engine.v2.Square

data class PseudoMove(val from: SquareMap, val to: SquareMap, val piece: Char, val promo: Char? = null) {
    val fromBit = Square[from.name]
    val toBit = Square[to.name]
    val asWord: ULong = Square[from.name] or Square[from.name]

    fun asString(): String {
        return "${from.name}${to.name}"
    }

    fun asPair(): Pair<SquareMap, SquareMap> {
        return Pair(from, to)
    }

    companion object {
        fun getPromoMoves(from: SquareMap, to: SquareMap, piece: Char): List<PseudoMove> = when (piece) {
            Piece.whitePawn -> listOf(
                PseudoMove(from, to, piece, 'Q'),
                PseudoMove(from, to, piece, 'R'),
                PseudoMove(from, to, piece, 'B'),
                PseudoMove(from, to, piece, 'N'),
            )
            Piece.blackPawn -> listOf(
                PseudoMove(from, to, piece, 'q'),
                PseudoMove(from, to, piece, 'r'),
                PseudoMove(from, to, piece, 'b'),
                PseudoMove(from, to, piece, 'n'),
            )
            else -> throw IllegalArgumentException("PawnPushPairToPseudoMoves can only be used with pawns")
        }
    }
}
