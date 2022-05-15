interface IGame {
    fun generateMoves(): List<Move>
    fun makeMove(move: Move)
    fun undoMove()
    fun isMoveCheck(move: Move): Boolean
    fun getActiveChecks(): List<Move>
    fun isMoveValid(move: Movement): Boolean
}