package engine.v1

class Queen(override val color: PieceColor) :
    Piece(PieceType.QUEEN, color, if (color == PieceColor.WHITE) 'Q' else 'q') {
    override fun canMove(move: Movement): Boolean {
        return move.deltaY == 0 || move.deltaX == 0 || move.distanceY == move.distanceX
    }

    override val trajectories: List<MoveTrajectory> =
        listOf(MoveTrajectory.DIAGONAL, MoveTrajectory.HORIZONTAL, MoveTrajectory.VERTICAL)
}