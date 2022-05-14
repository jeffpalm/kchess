
abstract class Piece(override val type: PieceType, override val color: PieceColor, override val symbol: Char) : IPiece {

    abstract override fun canMove(move: PotentialMove): Boolean

    open fun canCapture(move: PotentialMove): Boolean {
        return canMove(move)
    }
}


