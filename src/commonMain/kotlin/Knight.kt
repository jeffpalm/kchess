class Knight(override val color: PieceColor) :
    Piece(PieceType.KNIGHT, color, if (color == PieceColor.WHITE) 'N' else 'n') {
    override fun canMove(move: Movement): Boolean {
        return move.distanceX == 2 && move.distanceY == 1 || move.distanceX == 1 && move.distanceY == 2
    }

    override val trajectories: List<MoveTrajectory> = listOf(MoveTrajectory.KNIGHT)

}