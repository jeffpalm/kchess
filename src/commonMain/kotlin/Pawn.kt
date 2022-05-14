class Pawn(override val color: PieceColor) : Piece(PieceType.PAWN, color, if (color == PieceColor.WHITE) 'P' else 'p') {
    override fun canMove(move: PotentialMove): Boolean {
        return when {
            move.distanceY == 0.toUInt() -> false
            color == PieceColor.BLACK && move.deltaY < 0 || move.deltaY > 2 -> false
            color == PieceColor.WHITE && move.deltaY > 0 || move.deltaY < -2 -> false
            // going in correct direction
            move.distanceY == 2.toUInt() && move.distanceX > 0.toUInt() -> false
            move.distanceY == 2.toUInt() -> if (color == PieceColor.WHITE) move.from.y == 6 else move.from.y == 1
            else -> true
        }
    }

    override fun canCapture(move: PotentialMove): Boolean {
        return canMove(move) && move.distanceY == 1.toUInt() && move.distanceX == 1.toUInt()
    }

    override val trajectories: List<MoveTrajectory> = listOf(MoveTrajectory.VERTICAL, MoveTrajectory.DIAGONAL)
}
