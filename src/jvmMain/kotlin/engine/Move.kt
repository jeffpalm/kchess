package engine

class Move(
    override val fromSquare: SquareMap,
    override val toSquare: SquareMap,
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
        SquareMap.e1 -> when (toSquare) {
            SquareMap.g1, SquareMap.c1 -> piece == 'K'
            else -> false
        }
        else -> false
    }

    fun isBlackKingCastle(): Boolean = when (fromSquare) {
        SquareMap.e8 -> when (toSquare) {
            SquareMap.g8, SquareMap.c8 -> piece == 'k'
            else -> false
        }
        else -> false
    }

    fun enPassantTarget(): String = when {
        isWhiteTwoMoveJump(this) -> {
            val square = Compass.navigate(Square[fromSquare.ordinal], Direction.N)
            Square.Name[square]
        }
        isBlackTwoMoveJump(this) -> {
            val square = Compass.navigate(Square[fromSquare.ordinal], Direction.S)
            Square.Name[square]
        }
        else -> "-"
    }

    companion object {
        fun isWhitePawnStartingMove(move: Move): Boolean =
            Square[move.fromSquare.ordinal].and(BitBoard.Companion.StartPosition.P).countOneBits() > 0 && move.piece == 'P'

        fun isBlackPawnStartingMove(move: Move): Boolean =
            Square[move.fromSquare.ordinal].and(BitBoard.Companion.StartPosition.p).countOneBits() > 0 && move.piece == 'p'

        fun isWhiteTwoMoveJump(move: Move): Boolean =
            isWhitePawnStartingMove(move) && Square[move.toSquare.ordinal].and(Sets.RANK4).countOneBits() > 0

        fun isBlackTwoMoveJump(move: Move): Boolean =
            isBlackPawnStartingMove(move) && Square[move.toSquare.ordinal].and(Sets.RANK5).countOneBits() > 0
    }
}
