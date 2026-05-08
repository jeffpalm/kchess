package engine.move.rules

import engine.Direction
import engine.Piece
import engine.move.IMoveRule
import engine.move.MoveGenCtx

object MoveRuleQueen : IMoveRule {
    override fun shouldRun(ctx: MoveGenCtx): Boolean {
        val (board, turn) = ctx.data
        return board.queens(turn) != 0UL
    }

    override fun run(ctx: MoveGenCtx) {
        val (board, turn) = ctx.data
        val piece = Piece.queen(turn)
        var w = board.queens(turn)
        while (w != 0UL) {
            val queen = w.takeLowestOneBit()
            for (direction in Direction.sliding) {
                ctx.addBitMoves(queen, board.rayMoves(queen, direction, turn), piece)
            }
            w = w xor queen
        }
    }
}
