package engine.move.rules

import engine.Color
import engine.Direction
import engine.Piece
import engine.adapter.BitBitsPairToPseudoMoves
import engine.adapter.BitsToListOfBit
import engine.move.IMoveRule
import engine.move.MoveGenCtx

class MoveRuleBlackRook : IMoveRule {
    private val directions: List<Direction> = listOf(
        Direction.N,
        Direction.W,
        Direction.S,
        Direction.E
    )

    override fun shouldRun(ctx: MoveGenCtx): Boolean {
        return ctx.data.turn == Color.BLACK && ctx.data.board.blackRooks.countOneBits() > 0
    }

    override suspend fun run(ctx: MoveGenCtx) {
        val (board) = ctx.data

        val individualRooks = BitsToListOfBit(board.blackRooks).output
        for (rook in individualRooks) {
            for (direction in directions) {
                handleTargetSquares(rook, direction, ctx)
            }
        }
    }

    private fun handleTargetSquares(x: ULong, direction: Direction, ctx: MoveGenCtx) {
        val targetSquares = ctx.data.board.rayMoves(x, direction, Color.BLACK)
        ctx.addMoves(BitBitsPairToPseudoMoves(x to targetSquares, Piece.blackRook).output)
    }

}