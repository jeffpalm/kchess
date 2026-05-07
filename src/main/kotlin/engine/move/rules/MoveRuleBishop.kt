package engine.move.rules

import engine.Direction
import engine.Piece
import engine.move.IMoveRule
import engine.move.MoveGenCtx

object MoveRuleBishop : IMoveRule {
    override fun shouldRun(ctx: MoveGenCtx): Boolean {
        val (board, turn) = ctx.data
        return board.bishops(turn) != 0UL
    }

    override fun run(ctx: MoveGenCtx) {
        val (board, turn) = ctx.data
        val piece = Piece.bishop(turn)
        var w = board.bishops(turn)
        while (w != 0UL) {
            val bishop = w.takeLowestOneBit()
            for (direction in Direction.bishops) {
                ctx.addBitMoves(bishop, board.rayMoves(bishop, direction, turn), piece)
            }
            w = w xor bishop
        }
    }
}
