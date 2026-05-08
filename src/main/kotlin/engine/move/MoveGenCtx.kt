package engine.move

import engine.BitBoard
import engine.IGameData
import engine.Piece
import engine.Square

class MoveGenCtx(
    var data: IGameData,
) {
    private var packed: LongArray = LongArray(64)
    private var size: Int = 0

    private var cachedEnemyAttacks: ULong = 0UL
    private var hasEnemyAttacks: Boolean = false

    /** Re-bind this ctx to [newData] and clear all per-call caches. */
    fun reset(newData: IGameData) {
        data = newData
        size = 0
        hasEnemyAttacks = false
    }

    /** Squares attacked by the side NOT to move; cached per [MoveGenCtx]. */
    fun enemyAttacks(): ULong {
        if (!hasEnemyAttacks) {
            cachedEnemyAttacks = data.board.allAttackTargets(data.turn.inv())
            hasEnemyAttacks = true
        }
        return cachedEnemyAttacks
    }

    /** Number of moves currently in the buffer. */
    val movesSize: Int get() = size

    /** [PseudoMove] at index [i]; the value class is unboxed for primitive access. */
    fun moveAt(i: Int): PseudoMove = PseudoMove(packed[i])

    /**
     * Snapshot of the moves as a List. Allocates — only call from non-hot
     * paths (tests, REPL, etc.).
     */
    fun moves(): List<PseudoMove> {
        val out = ArrayList<PseudoMove>(size)
        for (i in 0 until size) out.add(PseudoMove(packed[i]))
        return out
    }

    fun addMove(move: PseudoMove) {
        ensureCapacity(size + 1)
        packed[size++] = move.packed
    }

    fun addMoves(other: List<PseudoMove>) {
        ensureCapacity(size + other.size)
        for (m in other) packed[size++] = m.packed
    }

    /**
     * Iterates each set bit of [targets] and pushes a non-promotion move from
     * [fromBit] to that target. Use [addPawnTargets] for pawn moves whose
     * targets may land on a promotion rank.
     */
    fun addBitMoves(fromBit: ULong, targets: ULong, piece: Char) {
        if (targets == 0UL) return
        val fromOrd = fromBit.countTrailingZeroBits().toLong()
        val pieceShifted = piece.code.toLong() shl 12
        var w = targets
        while (w != 0UL) {
            val toOrd = w.countTrailingZeroBits().toLong()
            ensureCapacity(size + 1)
            packed[size++] = fromOrd or (toOrd shl 6) or pieceShifted
            w = w and (w - 1UL)
        }
    }

    /**
     * Same shape as [addBitMoves] but expands to four promotion moves whenever
     * a target lands on a promotion rank. Use for pawn pushes and pawn
     * attacks.
     */
    fun addPawnTargets(fromBit: ULong, targets: ULong, piece: Char) {
        if (targets == 0UL) return
        val fromOrd = fromBit.countTrailingZeroBits().toLong()
        val pieceShifted = piece.code.toLong() shl 12
        val promoMask = BitBoard.promoSquares(piece)
        val isWhite = piece == Piece.wPawn
        var w = targets
        while (w != 0UL) {
            val toBit = w.takeLowestOneBit()
            val toOrd = toBit.countTrailingZeroBits().toLong()
            val base = fromOrd or (toOrd shl 6) or pieceShifted
            if (toBit and promoMask != 0UL) {
                ensureCapacity(size + 4)
                if (isWhite) {
                    packed[size++] = base or ('Q'.code.toLong() shl 20)
                    packed[size++] = base or ('R'.code.toLong() shl 20)
                    packed[size++] = base or ('B'.code.toLong() shl 20)
                    packed[size++] = base or ('N'.code.toLong() shl 20)
                } else {
                    packed[size++] = base or ('q'.code.toLong() shl 20)
                    packed[size++] = base or ('r'.code.toLong() shl 20)
                    packed[size++] = base or ('b'.code.toLong() shl 20)
                    packed[size++] = base or ('n'.code.toLong() shl 20)
                }
            } else {
                ensureCapacity(size + 1)
                packed[size++] = base
            }
            w = w xor toBit
        }
    }

    /**
     * In-place compact: keeps the moves where [keep] returns true. Inline so
     * the [PseudoMove] value class stays unboxed inside [keep].
     */
    inline fun filterMoves(keep: (PseudoMove) -> Boolean) {
        var dst = 0
        val n = movesSize
        var i = 0
        while (i < n) {
            val p = packedRaw(i)
            if (keep(PseudoMove(p))) {
                setPackedRaw(dst, p)
                dst++
            }
            i++
        }
        truncateTo(dst)
    }

    /** @suppress internal — used by [filterMoves]. */
    @PublishedApi internal fun packedRaw(i: Int): Long = packed[i]
    /** @suppress internal — used by [filterMoves]. */
    @PublishedApi internal fun setPackedRaw(i: Int, value: Long) {
        packed[i] = value
    }
    /** @suppress internal — used by [filterMoves]. */
    @PublishedApi internal fun truncateTo(newSize: Int) {
        size = newSize
    }

    private fun ensureCapacity(needed: Int) {
        if (needed > packed.size) {
            var newCap = packed.size * 2
            while (newCap < needed) newCap *= 2
            packed = packed.copyOf(newCap)
        }
    }
}
