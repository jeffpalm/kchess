package engine.v1

interface IPiece {
    val type: PieceType
    val color: PieceColor
    val symbol: Char
    val trajectories: List<MoveTrajectory>

    fun canMove(move: Movement): Boolean
}
