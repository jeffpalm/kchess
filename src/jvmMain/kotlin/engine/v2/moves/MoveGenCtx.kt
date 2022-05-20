package engine.v2.moves

import engine.v2.IGameData
import engine.v2.SquareMap

class MoveGenCtx(val gameData: IGameData) {
    private val moves: MutableSet<Pair<SquareMap, SquareMap>> = mutableSetOf()

    fun getCount(): Int {
        return moves.size
    }

    fun addMove(move: PseudoMove) {
        moves.add(move.asPair())
    }

    fun addMoves(moves: List<PseudoMove>) {
        this.moves.addAll(moves.map { it.asPair() })
    }

    fun getMoves(): Set<Pair<SquareMap, SquareMap>> {
        return moves
    }
}