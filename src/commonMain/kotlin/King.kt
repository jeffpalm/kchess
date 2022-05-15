class King(override val color: PieceColor) : Piece(PieceType.KING, color, if (color == PieceColor.WHITE) 'K' else 'k') {
    override fun canMove(move: Movement): Boolean {
        if (move.distanceX > 1.toUInt() || move.distanceY > 1.toUInt()) {
            return false
        }
        return move.deltaY == 0 || move.deltaX == 0 || move.distanceY == move.distanceX
    }

    override val trajectories: List<MoveTrajectory> =
        listOf(MoveTrajectory.VERTICAL, MoveTrajectory.HORIZONTAL, MoveTrajectory.DIAGONAL)
}