class Pawn(override val color: PieceColor) : Piece(PieceType.PAWN, color, if (color == PieceColor.WHITE) 'P' else 'p') {
    override fun canMove(move: PotentialMove): Boolean {
        return when {
            move.distanceY == 0.toUByte() -> false
            color == PieceColor.BLACK && move.deltaY < 0 || move.deltaY > 2 -> false
            color == PieceColor.WHITE && move.deltaY > 0 || move.deltaY < -2 -> false
            // going in correct direction
            move.distanceY == 2.toUByte() && move.distanceX > 0.toUByte() -> false
            move.distanceY == 2.toUByte() -> if (color == PieceColor.WHITE) move.from.y == 6.toByte() else move.from.y == 1.toByte()
            else -> true
        }
    }

    override fun canCapture(move: PotentialMove): Boolean {
        return canMove(move) && move.distanceY == 1.toUByte() && move.distanceX == 1.toUByte()
    }

    override val trajectories: List<MoveTrajectory> = listOf(MoveTrajectory.VERTICAL, MoveTrajectory.DIAGONAL)
}
