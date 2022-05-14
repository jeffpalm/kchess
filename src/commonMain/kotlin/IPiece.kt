interface IPiece {
    val type: PieceType
    val color: PieceColor
    val symbol: Char
    val trajectories: List<MoveTrajectory>

    fun canMove(move: PotentialMove): Boolean
}
