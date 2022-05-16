package engine

interface IBoard {
    val rep: BoardRep
    fun makeMove(move: Move)
}