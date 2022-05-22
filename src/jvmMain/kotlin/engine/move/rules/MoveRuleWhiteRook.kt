package engine.move.rules

import engine.Color
import engine.Direction
import engine.Piece
import engine.move.IMoveRule
import engine.move.MoveGenCtx
import engine.adapter.BitBitsPairToPseudoMoves
import engine.adapter.BitsToListOfBit

class MoveRuleWhiteRook : IMoveRule {
    private val directions: List<Direction> = listOf(
        Direction.N,
        Direction.W,
        Direction.S,
        Direction.E,
    )

    override fun shouldRun(ctx: MoveGenCtx): Boolean {
        return ctx.data.turn == Color.WHITE && ctx.data.board.whiteRooks.countOneBits() > 0
    }

    override suspend fun run(ctx: MoveGenCtx) {
        val (board) = ctx.data

        val individualRooks = BitsToListOfBit(board.whiteRooks).output
        for (rook in individualRooks) {
            for (direction in directions) {
                handleTargetSquares(rook, direction, ctx)
            }
        }
    }

    private fun handleTargetSquares(x: ULong, direction: Direction, ctx: MoveGenCtx) {
        val targetSquares = ctx.data.board.rayMoves(x, direction, Color.WHITE)
        ctx.addMoves(BitBitsPairToPseudoMoves(x to targetSquares, Piece.whiteRook).output)
    }

}