package engine.move

import engine.IGameData

class MoveGenCtx(val data: IGameData) {
    private val moves: MutableList<PseudoMove> = ArrayList(64)

    fun addMove(move: PseudoMove) {
        moves.add(move)
    }

    fun addMoves(other: List<PseudoMove>) {
        moves.addAll(other)
    }

    fun moves(): List<PseudoMove> = moves

    /** In-place; keeps the moves where [keep] returns true. */
    fun filterMoves(keep: (PseudoMove) -> Boolean) {
        moves.retainAll { keep(it) }
    }
}
