package engine.v2.moves

import engine.v2.IGameData

class MoveGenCtx(val gameData: IGameData) {
    private val moves: MutableList<PseudoMove> = mutableListOf()

    fun getCount(): Int {
        return moves.size
    }

    fun addMove(move: PseudoMove) {
        moves.add(move)
    }

    fun addMoves(moves: List<PseudoMove>) {
        this.moves.addAll(moves)
    }

    fun getMoves(): List<PseudoMove> {
        return moves
    }

    fun print() {
        for(move in moves) {
            println(move.asString())
        }
    }
}