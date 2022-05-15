
abstract class Piece(override val type: PieceType, override val color: PieceColor, override val symbol: Char) : IPiece {

    abstract override fun canMove(move: Movement): Boolean

    open fun canCapture(move: Movement): Boolean {
        return canMove(move)
    }
}


