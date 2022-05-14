class King(override val color: PieceColor) : Piece(PieceType.KING, color, if (color == PieceColor.WHITE) 'K' else 'k') {
    override fun canMove(move: PotentialMove): Boolean {
        if (move.distanceX > 1.toUByte() || move.distanceY > 1.toUByte()) {
            return false
        }
        return move.deltaY == 0.toByte() || move.deltaX == 0.toByte() || move.distanceY == move.distanceX
    }

    override val trajectories: List<MoveTrajectory> =
        listOf(MoveTrajectory.VERTICAL, MoveTrajectory.HORIZONTAL, MoveTrajectory.DIAGONAL)
}