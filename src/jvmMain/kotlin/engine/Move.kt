package engine

class Move(
    override val fromSquare: Square,
    override val toSquare: Square,
    board: Board,
    val prevEnPassantTarget: String
) : IMove {
    override val piece: Char =
        board.getPiece(fromSquare) ?: throw IllegalArgumentException("No piece at $fromSquare")
    override val capture: Char? = board.getPiece(toSquare)

    fun asString(): String {
        return "${fromSquare.name}${toSquare.name}"
    }

    fun isWhiteKingCastle(): Boolean = when (fromSquare) {
        Square.e1 -> when (toSquare) {
            Square.g1, Square.c1 -> piece == 'K'
            else -> false
        }
        else -> false
    }

    fun isBlackKingCastle(): Boolean = when (fromSquare) {
        Square.e8 -> when (toSquare) {
            Square.g8, Square.c8 -> piece == 'k'
            else -> false
        }
        else -> false
    }

    fun enPassantTarget(): String = when {
        isWhiteTwoMoveJump(this) -> {
            val square = Compass.navigate(Sq[fromSquare.ordinal], Direction.N)
            Sq.Name[square]
        }
        isBlackTwoMoveJump(this) -> {
            val square = Compass.navigate(Sq[fromSquare.ordinal], Direction.S)
            Sq.Name[square]
        }
        else -> "-"
    }

    companion object {
        fun isWhitePawnStartingMove(move: Move): Boolean =
            Sq[move.fromSquare.ordinal].and(BitBoard.Companion.StartPosition.P).countOneBits() > 0 && move.piece == 'P'

        fun isBlackPawnStartingMove(move: Move): Boolean =
            Sq[move.fromSquare.ordinal].and(BitBoard.Companion.StartPosition.p).countOneBits() > 0 && move.piece == 'p'

        fun isWhiteTwoMoveJump(move: Move): Boolean =
            isWhitePawnStartingMove(move) && Sq[move.toSquare.ordinal].and(Sets.RANK4).countOneBits() > 0

        fun isBlackTwoMoveJump(move: Move): Boolean =
            isBlackPawnStartingMove(move) && Sq[move.toSquare.ordinal].and(Sets.RANK5).countOneBits() > 0
    }
}
