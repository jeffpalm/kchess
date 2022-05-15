interface IGame {
    fun generateMoves(): List<Move>
    fun makeMove(move: Move)
    fun undoMove()
    fun isMoveCheck(move: Movement): Boolean
    fun getActiveChecks(): List<Movement>
    fun isMoveValid(move: Movement): Boolean
}