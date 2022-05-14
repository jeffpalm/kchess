class Bishop(override val color: PieceColor) :
    Piece(PieceType.BISHOP, color, if (color == PieceColor.WHITE) 'B' else 'b') {
    override fun canMove(move: PotentialMove): Boolean {
        return move.distanceY == move.distanceX
    }

    override val trajectories: List<MoveTrajectory> = listOf(MoveTrajectory.DIAGONAL)
}