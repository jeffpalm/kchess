package engine.move.rules

import engine.Direction
import engine.Piece
import engine.adapter.BitBitsPairToPseudoMoves
import engine.adapter.BitsToListOfBit
import engine.move.IMoveRule
import engine.move.MoveGenCtx

class MoveRuleBishop : IMoveRule {
    override fun shouldRun(ctx: MoveGenCtx): Boolean {
        val (board, turn) = ctx.data
        return board.bishops(turn) != 0UL
    }

    override suspend fun run(ctx: MoveGenCtx) {
        val (board, turn) = ctx.data

        val individualBishops = BitsToListOfBit(board.bishops(turn)).output
        for (bishop in individualBishops) {
            for (direction in Direction.bishops) {
                handleTargetSquares(bishop, direction, ctx)
            }
        }
    }

    private fun handleTargetSquares(x: ULong, direction: Direction, ctx: MoveGenCtx) {
        val targetSquares = ctx.data.board.rayMoves(x, direction, ctx.data.turn)
        ctx.addMoves(BitBitsPairToPseudoMoves(x to targetSquares, Piece.bishop(ctx.data.turn)).output)
    }

}