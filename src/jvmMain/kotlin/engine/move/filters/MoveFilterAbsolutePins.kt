package engine.move.filters

import engine.BitBoard
import engine.Color
import engine.Compass
import engine.Direction
import engine.move.IMoveFilter
import engine.move.MoveGenCtx

class MoveFilterAbsolutePins : IMoveFilter {
    override suspend fun run(ctx: MoveGenCtx): MoveGenCtx {
        val absolutePins = getAbsolutePins(ctx, Direction.sliding)

        ctx.filterMoves {
            when (it.piece) {
                'K', 'k' -> true
                else -> !(it.fromBit.and(absolutePins) != 0UL && it.toBit.and(absolutePins) == 0UL)
            }
        }

        return ctx
    }

    private fun getAbsolutePins(ctx: MoveGenCtx, directions: List<Direction>): ULong {
        val (board, turn) = ctx.data
        var output: ULong = 0UL

        for (direction in directions) {
            val xRay = Compass.ray(friendlyKing(board, turn), direction)
            val enemyOnXRay = when (direction) {
                in Direction.bishops -> when (turn) {
                    Color.WHITE -> xRay and (board.blackBishops or board.blackQueens)
                    Color.BLACK -> xRay and (board.whiteBishops or board.whiteQueens)
                }
                in Direction.rooks -> when (turn) {
                    Color.WHITE -> xRay and (board.blackRooks or board.blackQueens)
                    Color.BLACK -> xRay and (board.whiteRooks or board.whiteQueens)
                }
                else -> throw IllegalArgumentException("Invalid direction")
            }
            if (enemyOnXRay == 0UL) continue

            val closestEnemy = when (direction) {
                Direction.NW, Direction.N, Direction.NE, Direction.E -> enemyOnXRay.takeLowestOneBit()
                Direction.SE, Direction.S, Direction.SW, Direction.W -> enemyOnXRay.takeHighestOneBit()
                else -> throw IllegalArgumentException("Invalid direction")
            }
            val protectRay = board.rayMoves(closestEnemy, direction.inv(), turn.inv())
            output = output or protectRay
        }
        return output
    }

    private fun friendlyKing(board: BitBoard, turn: Color): ULong = when (turn) {
        Color.WHITE -> board.whiteKing
        Color.BLACK -> board.blackKing
    }
}