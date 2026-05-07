package engine.move.rules

import engine.Direction
import engine.Piece
import engine.move.IMoveRule
import engine.move.MoveGenCtx

object MoveRuleRook : IMoveRule {
    override fun shouldRun(ctx: MoveGenCtx): Boolean {
        val (board, turn) = ctx.data
        return board.rooks(turn) != 0UL
    }

    override fun run(ctx: MoveGenCtx) {
        val (board, turn) = ctx.data
        val piece = Piece.rook(turn)
        var w = board.rooks(turn)
        while (w != 0UL) {
            val rook = w.takeLowestOneBit()
            for (direction in Direction.rooks) {
                ctx.addBitMoves(rook, board.rayMoves(rook, direction, turn), piece)
            }
            w = w xor rook
        }
    }
}
