package engine.move.rules

import engine.Compass
import engine.Piece
import engine.move.IMoveRule
import engine.move.MoveGenCtx

object MoveRuleKnight : IMoveRule {
    override fun shouldRun(ctx: MoveGenCtx): Boolean {
        val (board, turn) = ctx.data
        return board.knights(turn) != 0UL
    }

    override fun run(ctx: MoveGenCtx) {
        val (board, turn) = ctx.data
        val piece = Piece.knight(turn)
        val notOwn = board.occupied(turn).inv()
        var w = board.knights(turn)
        while (w != 0UL) {
            val knight = w.takeLowestOneBit()
            ctx.addBitMoves(knight, Compass.knightMoveTargets(knight) and notOwn, piece)
            w = w xor knight
        }
    }
}
