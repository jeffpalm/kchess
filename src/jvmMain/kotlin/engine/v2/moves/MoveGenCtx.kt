package engine.v2.moves

import engine.v2.IGameData

class MoveGenCtx(val data: IGameData) {
    private var moves: MutableSet<PseudoMove> = mutableSetOf()

    fun getCount(): Int {
        return moves.size
    }

    fun addMove(move: PseudoMove) {
        moves.add(move)
    }

    fun addMoves(moves: List<PseudoMove>) {
        this.moves.addAll(moves)
    }

    fun moves(): Set<PseudoMove> {
        return moves
    }

    fun filterMoves(filter: (PseudoMove) -> Boolean) {
        moves = moves.filter(filter).toMutableSet()
    }
}