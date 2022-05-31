package engine.move.filters

import engine.Color
import engine.Compass
import engine.Direction
import engine.Square
import engine.move.IMoveFilter
import engine.move.Magic
import engine.move.MoveGenCtx

class MoveFilterAbsolutePins : IMoveFilter {
    override suspend fun run(ctx: MoveGenCtx): MoveGenCtx {
        val absolutePins = getAbsolutePins(ctx)

        ctx.filterMoves {
            when (it.piece) {
                'K', 'k' -> true
                else -> !(it.fromBit.and(absolutePins) != 0UL && it.toBit.and(absolutePins) == 0UL)
            }
        }

        return ctx
    }

    private fun getAbsolutePins(ctx: MoveGenCtx): ULong {
        val (board, turn) = ctx.data
        var output: ULong = 0UL
        val friendlyKing = board.king(turn)

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
            if (protector != friendlyKing) {

                val squaresNextToKing = Magic.Attack.King[Square[friendlyKing]]
                // protector is adjacent to king
                if (squaresNextToKing.and(protector) != 0UL) {
                    output = output or protector
                    continue
                }

                // Use enemy color to include protector bit
                val pathToProtector = board.rayMoves(board.king(turn), direction, turn.inv())
                if (pathToProtector.and(protector) != 0UL) {
                    output = output or protector
                }
            }
        }
        return output
    }
}