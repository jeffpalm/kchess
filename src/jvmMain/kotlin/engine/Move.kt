package engine

import engine.move.Magic

class Move(
    override val from: Square,
    override val to: Square,
    board: Board,
    val prevEnPassantTarget: Square?
) : IMove {
    override val piece: Char =
        board.getPiece(from) ?: throw IllegalArgumentException("No piece at $from")
    override val capture: Char?

    init {
        capture = when (to) {
            prevEnPassantTarget -> board.getPiece(Square[Magic.EnPassantCaptureSq[to]])
            else -> board.getPiece(to)
        }
    }

    fun asString(): String {
        return "${from.name}${to.name}"
    }

    fun isWhiteKingCastle(): Boolean = when (from) {
        Square.e1 -> when (to) {
            Square.g1, Square.c1 -> piece == 'K'
            else -> false
        }
        else -> false
    }

    fun isBlackKingCastle(): Boolean = when (from) {
        Square.e8 -> when (to) {
            Square.g8, Square.c8 -> piece == 'k'
            else -> false
        }
        else -> false
    }

    fun enPassantTarget(): Square? = when {
        isWhiteTwoMoveJump(this) -> {
            val square = Compass.navigate(Sq[from.ordinal], Direction.N)
            Square[square]
        }
        isBlackTwoMoveJump(this) -> {
            val square = Compass.navigate(Sq[from.ordinal], Direction.S)
            Square[square]
        }
        else -> null
    }

    companion object {
        fun isWhitePawnStartingMove(move: Move): Boolean =
            Sq[move.from.ordinal].and(BitBoard.Companion.StartPosition.P).countOneBits() > 0 && move.piece == 'P'

        fun isBlackPawnStartingMove(move: Move): Boolean =
            Sq[move.from.ordinal].and(BitBoard.Companion.StartPosition.p).countOneBits() > 0 && move.piece == 'p'

        fun isWhiteTwoMoveJump(move: Move): Boolean =
            isWhitePawnStartingMove(move) && Sq[move.to.ordinal].and(Sets.RANK4).countOneBits() > 0

        fun isBlackTwoMoveJump(move: Move): Boolean =
            isBlackPawnStartingMove(move) && Sq[move.to.ordinal].and(Sets.RANK5).countOneBits() > 0
    }
}
