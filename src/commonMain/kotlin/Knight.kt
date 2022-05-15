class Knight(override val color: PieceColor) :
    Piece(PieceType.KNIGHT, color, if (color == PieceColor.WHITE) 'N' else 'n') {
    override fun canMove(move: PotentialMove): Boolean {
        return move.distanceX == 2.toUInt() && move.distanceY == 1.toUInt() || move.distanceX == 1.toUInt() && move.distanceY == 2.toUInt()
    }

    override val trajectories: List<MoveTrajectory> = listOf(MoveTrajectory.KNIGHT)

}