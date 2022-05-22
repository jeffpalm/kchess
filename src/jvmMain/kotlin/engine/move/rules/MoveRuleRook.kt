package engine.move.rules

import engine.Direction
import engine.Piece
import engine.adapter.BitBitsPairToPseudoMoves
import engine.adapter.BitsToListOfBit
import engine.move.IMoveRule
import engine.move.MoveGenCtx

class MoveRuleRook : IMoveRule {

    override fun shouldRun(ctx: MoveGenCtx): Boolean {
        val (board, turn) = ctx.data
        return board.rooks(turn) != 0UL
    }

    override suspend fun run(ctx: MoveGenCtx) {
        val (board, turn) = ctx.data

        val individualRooks = BitsToListOfBit(board.rooks(turn)).output
        for (rook in individualRooks) {
            for (direction in Direction.rooks) {
                handleTargetSquares(rook, direction, ctx)
            }
        }
    }

    private fun handleTargetSquares(x: ULong, direction: Direction, ctx: MoveGenCtx) {
        val (board, turn) = ctx.data
        val targetSquares = board.rayMoves(x, direction, turn)
        ctx.addMoves(BitBitsPairToPseudoMoves(x to targetSquares, Piece.rook(turn)).output)
    }

}