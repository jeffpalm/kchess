class Pawn(override val color: PieceColor) : Piece(PieceType.PAWN, color, if (color == PieceColor.WHITE) 'P' else 'p') {
    override fun canMove(move: Movement): Boolean {
        return when {
            color == PieceColor.BLACK && move.deltaY < 1 || move.deltaY > 2 -> false
            color == PieceColor.WHITE && move.deltaY > -1 || move.deltaY < -2 -> false
            // going in correct direction
            move.distanceY == 2 && move.distanceX != 0 -> false
            move.distanceY == 2 -> if (color == PieceColor.WHITE) move.from.y == 6 else move.from.y == 1
            move.distanceX == 1 -> move.distanceY == 1
            else -> true
        }
    }

    override fun canCapture(move: Movement): Boolean {
        return canMove(move) && move.distanceY == 1 && move.distanceX == 1
    }

    override val trajectories: List<MoveTrajectory> = listOf(MoveTrajectory.VERTICAL, MoveTrajectory.DIAGONAL)
}
