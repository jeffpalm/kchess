package engine.move.filters

import engine.*
import engine.move.IMoveFilter
import engine.move.Magic
import engine.move.MoveGenCtx

class MoveFilterAbsolutePins : IMoveFilter {
    override suspend fun run(ctx: MoveGenCtx): MoveGenCtx {
        val pinAllowedByPiece = computePins(ctx)
        if (pinAllowedByPiece.isEmpty()) return ctx

        ctx.filterMoves {
            when (it.piece) {
                'K', 'k' -> true
                else -> {
                    val allowed = pinAllowedByPiece[it.fromBit]
                    if (allowed == null) true else it.toBit.and(allowed) != 0UL
                }
            }
        }
        return ctx
    }

    private fun computePins(ctx: MoveGenCtx): Map<ULong, ULong> {
        val (board, turn) = ctx.data
        val friendlyKing = board.king(turn)
        val result = mutableMapOf<ULong, ULong>()

        for (direction in Direction.sliding) {
            val xRay = Compass.ray(friendlyKing, direction)
            val enemyOnXRay = when (direction) {
                in Direction.bishops -> when (turn) {
                    Color.WHITE -> xRay and (board.bBishops or board.bQueens)
                    Color.BLACK -> xRay and (board.wBishops or board.wQueens)
                }
                in Direction.rooks -> when (turn) {
                    Color.WHITE -> xRay and (board.bRooks or board.bQueens)
                    Color.BLACK -> xRay and (board.wRooks or board.wQueens)
                }
                else -> throw IllegalArgumentException("Invalid direction")
            }
            if (enemyOnXRay == 0UL) continue

            val closestEnemy = when (direction) {
                in Direction.positive -> enemyOnXRay.takeLowestOneBit()
                in Direction.negative -> enemyOnXRay.takeHighestOneBit()
                else -> throw IllegalArgumentException("Invalid direction")
            }
            val protector = board.rayAttack(closestEnemy, direction.inv(), turn.inv())
            if (protector == 0UL || protector == friendlyKing) continue

            val squaresNextToKing = Magic.Attack.King[Square[friendlyKing]]
            val pathProtectorToEnemy = board.rayMoves(protector, direction, turn).xor(closestEnemy)

            val pathProtectorToKing = if (squaresNextToKing.and(protector) != 0UL) {
                0UL
            } else {
                val rayKingDir = board.rayMoves(friendlyKing, direction, turn.inv())
                if (rayKingDir.and(protector) == 0UL) continue
                rayKingDir.xor(protector)
            }

            val allowed = closestEnemy or pathProtectorToKing or pathProtectorToEnemy
            result[protector] = (result[protector] ?: 0UL) or allowed
        }
        return result
    }
}
