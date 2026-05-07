package engine.move.rules

import engine.Color
import engine.Compass
import engine.Direction
import engine.Piece
import engine.Sets
import engine.move.IMoveRule
import engine.move.MoveGenCtx

object MoveRuleWhitePawnPush : IMoveRule {
    override fun shouldRun(ctx: MoveGenCtx): Boolean {
        return ctx.data.turn == Color.WHITE && ctx.data.board.wPawns != 0UL
    }

    override fun run(ctx: MoveGenCtx) {
        val board = ctx.data.board
        val empty = board.empty()
        val singleFroms = Compass.navigate(empty, Direction.S) and board.wPawns
        emitPushes(ctx, singleFroms, 8)
        val emptyRank3 = Compass.navigate(empty and Sets.RANK4, Direction.S) and empty
        val doubleFroms = Compass.navigate(emptyRank3, Direction.S) and board.wPawns
        emitPushes(ctx, doubleFroms, 16)
    }

    private fun emitPushes(ctx: MoveGenCtx, froms: ULong, shift: Int) {
        var w = froms
        while (w != 0UL) {
            val from = w.takeLowestOneBit()
            ctx.addPawnTargets(from, from shl shift, Piece.wPawn)
            w = w xor from
        }
    }
}
