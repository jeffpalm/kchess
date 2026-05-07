package engine.move

import engine.BitBoard
import engine.IGameData
import engine.Piece
import engine.Square

class MoveGenCtx(
    var data: IGameData,
    private val moves: ArrayList<PseudoMove> = ArrayList(64),
) {
    private var cachedEnemyAttacks: ULong = 0UL
    private var hasEnemyAttacks: Boolean = false

    init {
        moves.clear()
    }

    /** Re-bind this ctx to [newData] and clear all per-call caches. */
    fun reset(newData: IGameData) {
        data = newData
        moves.clear()
        hasEnemyAttacks = false
    }

    /**
     * Squares attacked by the side NOT to move. Used for king-move legality
     * and castle-path checks. Cached per [MoveGenCtx] instance to avoid
     * recomputing it twice per node.
     */
    fun enemyAttacks(): ULong {
        if (!hasEnemyAttacks) {
            cachedEnemyAttacks = data.board.allAttackTargets(data.turn.inv())
            hasEnemyAttacks = true
        }
        return cachedEnemyAttacks
    }

    fun addMove(move: PseudoMove) {
        moves.add(move)
    }

    fun addMoves(other: List<PseudoMove>) {
        moves.addAll(other)
    }

    fun moves(): List<PseudoMove> = moves

    /**
     * Iterates each set bit of [targets] and pushes a [PseudoMove] for the
     * piece moving from [fromBit] to that target. No promotion expansion —
     * use [addPawnTargets] for pawn moves whose targets may be on a promo
     * rank.
     */
    fun addBitMoves(fromBit: ULong, targets: ULong, piece: Char) {
        if (targets == 0UL) return
        val from = Square.fromBit(fromBit)
        var w = targets
        while (w != 0UL) {
            val toBit = w.takeLowestOneBit()
            moves.add(PseudoMove(from, Square.fromBit(toBit), piece))
            w = w xor toBit
        }
    }

    /**
     * Same shape as [addBitMoves] but expands to four promotion moves whenever
     * a target lands on a promotion rank. Use for pawn pushes and pawn
     * attacks.
     */
    fun addPawnTargets(fromBit: ULong, targets: ULong, piece: Char) {
        if (targets == 0UL) return
        val from = Square.fromBit(fromBit)
        val promoMask = BitBoard.promoSquares(piece)
        var w = targets
        while (w != 0UL) {
            val toBit = w.takeLowestOneBit()
            val to = Square.fromBit(toBit)
            if (toBit and promoMask != 0UL) {
                if (piece == Piece.wPawn) {
                    moves.add(PseudoMove(from, to, piece, 'Q'))
                    moves.add(PseudoMove(from, to, piece, 'R'))
                    moves.add(PseudoMove(from, to, piece, 'B'))
                    moves.add(PseudoMove(from, to, piece, 'N'))
                } else {
                    moves.add(PseudoMove(from, to, piece, 'q'))
                    moves.add(PseudoMove(from, to, piece, 'r'))
                    moves.add(PseudoMove(from, to, piece, 'b'))
                    moves.add(PseudoMove(from, to, piece, 'n'))
                }
            } else {
                moves.add(PseudoMove(from, to, piece))
            }
            w = w xor toBit
        }
    }

    /** In-place; keeps the moves where [keep] returns true. */
    fun filterMoves(keep: (PseudoMove) -> Boolean) {
        moves.retainAll { keep(it) }
    }
}
