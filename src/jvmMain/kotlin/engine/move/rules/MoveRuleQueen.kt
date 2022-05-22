package engine.move.rules

import engine.Direction
import engine.Piece
import engine.adapter.BitBitsPairToPseudoMoves
import engine.adapter.BitsToListOfBit
import engine.move.IMoveRule
import engine.move.MoveGenCtx

class MoveRuleQueen : IMoveRule {
    override fun shouldRun(ctx: MoveGenCtx): Boolean {
        val (board, turn) = ctx.data
        return board.queens(turn) != 0UL
    }

    override suspend fun run(ctx: MoveGenCtx) {
        val (board, turn) = ctx.data

        val individualQueens = BitsToListOfBit(board.queens(turn)).output
        for (queen in individualQueens) {
            for (direction in Direction.sliding) {
                handleTargetSquares(queen, direction, ctx)
            }
        }
    }

    private fun handleTargetSquares(x: ULong, direction: Direction, ctx: MoveGenCtx) {
        val (board, turn) = ctx.data
        val targetSquares = board.rayMoves(x, direction, turn)
        ctx.addMoves(BitBitsPairToPseudoMoves(x to targetSquares, Piece.queen(turn)).output)
    }

}