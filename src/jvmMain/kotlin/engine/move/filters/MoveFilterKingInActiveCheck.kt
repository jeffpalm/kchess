package engine.move.filters

import engine.Color
import engine.CompassRose
import engine.Direction
import engine.move.IMoveFilter
import engine.move.MoveGenCtx

class MoveFilterKingInActiveCheck : IMoveFilter {
    override fun shouldRun(ctx: MoveGenCtx): Boolean {
        return true
    }

    override suspend fun run(ctx: MoveGenCtx): MoveGenCtx {
        val (board, turn) = ctx.data

        val friendlyKing = board.king(turn)
        val enemyColor = Color.inv(turn)
        var activeKingThreats: ULong = 0UL

        for (direction in Direction.bishops) {
            val enemySquare = board.rayAttack(friendlyKing, direction, turn)
            if (enemySquare != 0UL) {
                val enemyThreats = enemySquare and (board.queens(enemyColor) or board.bishops(enemyColor))
                if (enemyThreats != 0UL) {
                    activeKingThreats = activeKingThreats or board.rayMoves(friendlyKing, direction, turn)
                }
            }
        }

        for (direction in Direction.rooks) {
            val enemySquare = board.rayAttack(friendlyKing, direction, turn)
            if (enemySquare != 0UL) {
                val enemyThreats = enemySquare and (board.queens(enemyColor) or board.rooks(enemyColor))
                if (enemyThreats != 0UL) {
                    activeKingThreats = activeKingThreats or board.rayMoves(friendlyKing, direction, turn)
                }
            }
        }

        activeKingThreats =
            activeKingThreats or CompassRose.knightMoveTargets(friendlyKing).and(board.knights(enemyColor))

       if (activeKingThreats != 0UL) {
            ctx.filterMoves {
                when (it.piece) {
                    'k', 'K' -> it.toBit.and(activeKingThreats) == 0UL
                    else -> it.toBit.and(activeKingThreats) != 0UL
                }
            }
        }

        return ctx
    }
}