class Knight(override val color: PieceColor) :
    Piece(PieceType.KNIGHT, color, if (color == PieceColor.WHITE) 'N' else 'n') {
    override fun canMove(move: PotentialMove): Boolean {
        return move.distanceX == 2.toUByte() && move.distanceY == 1.toUByte() || move.distanceX == 1.toUByte() && move.distanceY == 2.toUByte()
    }

    override val trajectories: List<MoveTrajectory> = listOf(MoveTrajectory.KNIGHT)
}