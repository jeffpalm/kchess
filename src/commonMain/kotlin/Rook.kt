class Rook(override val color: PieceColor) :
    Piece(PieceType.ROOK, color, if (color === PieceColor.WHITE) 'R' else 'r') {

    override fun canMove(move: Movement): Boolean {
        return move.deltaY == 0 || move.deltaX == 0
    }

    override val trajectories: List<MoveTrajectory> = listOf(MoveTrajectory.VERTICAL, MoveTrajectory.HORIZONTAL)
}