package engine.v1

interface IGame {
    fun generateMoves(): List<Move>
    fun makeMove(move: Move)
    fun undoMove()
    fun isMoveCheck(move: Move): Boolean
    fun willMovePutKingInCheck(move: Move): Boolean

    fun isKingInCheck(): Boolean
    fun getActiveChecks(): List<Move>
    fun isMoveValid(move: Movement): Boolean
}