package engine.move.filters

import engine.Compass
import engine.Direction
import engine.Piece
import engine.move.IMoveFilter
import engine.move.MoveGenCtx

class MoveFilterKingInActiveCheck : IMoveFilter {

    override suspend fun run(ctx: MoveGenCtx): MoveGenCtx {
        val (board, turn) = ctx.data

        val friendlyKing = board.king(turn)
        val enemyColor = turn.inv()
        var activeKingThreats: ULong = 0UL
        // Threats that impact the king movement only
        var passiveKingThreats: ULong = 0UL

        for (direction in Direction.sliding) {
            val (activeThreats, passiveThreats) = getThreatsByDirection(ctx, direction)
            activeKingThreats = activeKingThreats or activeThreats
            passiveKingThreats = passiveKingThreats or passiveThreats
        }

        activeKingThreats = activeKingThreats or Compass.knightMoveTargets(friendlyKing).and(board.knights(enemyColor))

        if (activeKingThreats != 0UL) {
            ctx.filterMoves {
                when (it.piece) {
                    Piece.wKing, Piece.bKing -> it.toBit.and(activeKingThreats) == 0UL
                    else -> it.toBit.and(activeKingThreats) != 0UL
                }
            }
        }

        if (passiveKingThreats != 0UL) {
            ctx.filterMoves { it.toBit.and(passiveKingThreats) == 0UL }
        }

        return ctx
    }

    private fun getThreatsByDirection(ctx: MoveGenCtx, direction: Direction): Pair<ULong, ULong> {
        val (board, turn) = ctx.data

        val friendlyKing = board.king(turn)
        val enemyColor = turn.inv()

        val enemySquare = board.rayAttack(friendlyKing, direction, turn)
        if (enemySquare != 0UL) {
            val enemyThreats = when (direction) {
                in Direction.bishops -> enemySquare and (board.queens(enemyColor) or board.bishops(enemyColor))
                in Direction.rooks -> enemySquare and (board.queens(enemyColor) or board.rooks(enemyColor))
                else -> throw IllegalStateException("Unknown direction $direction")
            }
            if (enemyThreats != 0UL) {
                val activeKingThreats = board.rayMoves(friendlyKing, direction, turn)
                // Threats that impact the king movement only
                val passiveKingThreats = board.rayMoves(friendlyKing, direction.inv(), turn)
                return activeKingThreats to passiveKingThreats
            }
        }
        return 0UL to 0UL
    }
}