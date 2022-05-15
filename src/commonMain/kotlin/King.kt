class King(override val color: PieceColor) : Piece(PieceType.KING, color, if (color == PieceColor.WHITE) 'K' else 'k') {
    override fun canMove(move: Movement): Boolean {
        return when {
            move.distanceX > 2 || move.distanceY > 1 -> false
            move.distanceX == 2 && move.from.x != 4 -> false
            else -> move.deltaY == 0 || move.deltaX == 0 || move.distanceY == move.distanceX
        }
    }

    override val trajectories: List<MoveTrajectory> =
        listOf(MoveTrajectory.VERTICAL, MoveTrajectory.HORIZONTAL, MoveTrajectory.DIAGONAL)

    override fun canCapture(move: Movement): Boolean {
        return canMove(move) && move.distanceX == 1 || move.distanceY == 1
    }
}