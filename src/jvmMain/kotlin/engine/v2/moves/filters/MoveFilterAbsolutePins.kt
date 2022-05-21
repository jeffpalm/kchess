package engine.v2.moves.filters

import engine.v2.*
import engine.v2.moves.IMoveFilter
import engine.v2.moves.MoveGenCtx

class MoveFilterAbsolutePins : IMoveFilter {
    private val bishopDirections: List<Direction> = listOf(
        Direction.NE,
        Direction.NW,
        Direction.SE,
        Direction.SW
    )
    private val rookDirections: List<Direction> = listOf(
        Direction.N,
        Direction.W,
        Direction.S,
        Direction.E,
    )
    private val allDirections: List<Direction> = buildList {
        addAll(bishopDirections)
        addAll(rookDirections)
    }

    override fun shouldRun(ctx: MoveGenCtx): Boolean {
        return true
    }

    override suspend fun run(ctx: MoveGenCtx): MoveGenCtx {
        val absolutePins = getAbsolutePins(ctx, allDirections)

        ctx.filterMoves { !(Square[it.first.ordinal].and(absolutePins) != 0UL && Square[it.second.ordinal].and(absolutePins) == 0UL) }

        return ctx
    }

    private fun getAbsolutePins(ctx: MoveGenCtx, directions: List<Direction>): ULong {
        val (board, turn) = ctx.data
        var output: ULong = 0UL

        for (direction in directions) {
            val xRay = CompassRose.ray(friendlyKing(board, turn), direction)
            val enemyOnXRay = when (direction) {
                in bishopDirections -> when (turn) {
                    Color.WHITE -> xRay and (board.blackBishops or board.blackQueens)
                    Color.BLACK -> xRay and (board.whiteBishops or board.whiteQueens)
                }
                in rookDirections -> when (turn) {
                    Color.WHITE -> xRay and (board.blackRooks or board.blackQueens)
                    Color.BLACK -> xRay and (board.whiteRooks or board.whiteQueens)
                }
                else -> throw IllegalArgumentException("Invalid direction")
            }
            if (enemyOnXRay == 0UL) continue

            val closestEnemy = when(direction) {
                Direction.NW, Direction.N, Direction.NE, Direction.E -> enemyOnXRay.takeLowestOneBit()
                Direction.SE, Direction.S, Direction.SW, Direction.W -> enemyOnXRay.takeHighestOneBit()
                else -> throw IllegalArgumentException("Invalid direction")
            }
            val protectRay = board.rayMoves(closestEnemy, Direction.inv(direction), Color.inv(turn))
            output = output or protectRay
        }
        return output
    }

    private fun friendlyKing(board: BitBoard, turn: Color): ULong = when (turn) {
        Color.WHITE -> board.whiteKing
        Color.BLACK -> board.blackKing
    }
}