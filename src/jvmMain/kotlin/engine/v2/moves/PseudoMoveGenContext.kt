package engine.v2.moves

import engine.v2.GameData

class PseudoMoveGenContext(val gameData: GameData) {
    private val moves: MutableList<PseudoMove> = mutableListOf()
    private var count: Int = 0

    fun increment(x: Int) {
        count += x
    }

    fun getCount(): Int {
        return count
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