package engine.move.rules

import engine.Color
import engine.Compass
import engine.Direction
import engine.Piece
import engine.Sets
import engine.move.IMoveRule
import engine.move.MoveGenCtx

object MoveRuleBlackPawnPush : IMoveRule {
    override fun shouldRun(ctx: MoveGenCtx): Boolean {
        return ctx.data.turn == Color.BLACK && ctx.data.board.bPawns != 0UL
    }

    override fun run(ctx: MoveGenCtx) {
        val board = ctx.data.board
        val empty = board.empty()
        val singleFroms = Compass.navigate(empty, Direction.N) and board.bPawns
        emitPushes(ctx, singleFroms, 8)
        val emptyRank6 = Compass.navigate(empty and Sets.RANK5, Direction.N) and empty
        val doubleFroms = Compass.navigate(emptyRank6, Direction.N) and board.bPawns
        emitPushes(ctx, doubleFroms, 16)
    }

    private fun emitPushes(ctx: MoveGenCtx, froms: ULong, shift: Int) {
        var w = froms
        while (w != 0UL) {
            val from = w.takeLowestOneBit()
            ctx.addPawnTargets(from, from shr shift, Piece.bPawn)
            w = w xor from
        }
    }
}
