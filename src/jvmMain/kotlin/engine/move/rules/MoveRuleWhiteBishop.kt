package engine.move.rules

import engine.Color
import engine.Direction
import engine.Piece
import engine.adapter.BitBitsPairToPseudoMoves
import engine.adapter.BitsToListOfBit
import engine.move.IMoveRule
import engine.move.MoveGenCtx

class MoveRuleWhiteBishop : IMoveRule {
    private val directions: List<Direction> = listOf(
        Direction.NE,
        Direction.NW,
        Direction.SW,
        Direction.SE
    )

    override fun shouldRun(ctx: MoveGenCtx): Boolean {
        return ctx.data.turn == Color.WHITE && ctx.data.board.whiteBishops.countOneBits() > 0
    }

    override suspend fun run(ctx: MoveGenCtx) {
        val (board) = ctx.data

        val individualBishops = BitsToListOfBit(board.whiteBishops).output
        for (bishop in individualBishops) {
            for (direction in directions) {
                handleTargetSquares(bishop, direction, ctx)
            }
        }
    }

    private fun handleTargetSquares(x: ULong, direction: Direction, ctx: MoveGenCtx) {
        val targetSquares = ctx.data.board.rayMoves(x, direction, Color.WHITE)
        ctx.addMoves(BitBitsPairToPseudoMoves(x to targetSquares, Piece.whiteBishop).output)
    }

}