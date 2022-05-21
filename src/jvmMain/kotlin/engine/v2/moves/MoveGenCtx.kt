package engine.v2.moves

import engine.v2.IGameData
import engine.v2.SquareMap

class MoveGenCtx(val data: IGameData) {
    private var moves: MutableSet<Pair<SquareMap, SquareMap>> = mutableSetOf()

    fun getCount(): Int {
        return moves.size
    }

    fun addMove(move: PseudoMove) {
        moves.add(move.asPair())
    }

    fun addMoves(moves: List<PseudoMove>) {
        this.moves.addAll(moves.map { it.asPair() })
    }

    fun moves(): Set<Pair<SquareMap, SquareMap>> {
        return moves
    }

    fun filterMoves(filter: (Pair<SquareMap, SquareMap>) -> Boolean) {
        moves = moves.filter(filter).toMutableSet()
    }
}