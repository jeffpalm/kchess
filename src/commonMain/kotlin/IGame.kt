interface IGame {
    fun generateMoves(): List<Move>
    fun makeMove(move: Move)
    fun undoMove()
    fun isMoveCheck(move: PotentialMove): Boolean
    fun getActiveChecks(): List<PotentialMove>
    fun isMoveValid(move: PotentialMove): Boolean
}