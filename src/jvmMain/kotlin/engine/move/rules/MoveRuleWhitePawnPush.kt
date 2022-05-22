package engine.move.rules

import engine.Color
import engine.CompassRose
import engine.Direction
import engine.Sets
import engine.adapter.PawnPushPairToPseudoMoves
import engine.move.IMoveRule
import engine.move.MoveGenCtx

class MoveRuleWhitePawnPush : IMoveRule {
    override fun shouldRun(ctx: MoveGenCtx): Boolean {
        return ctx.data.turn == Color.WHITE && ctx.data.board.whitePawns.countOneBits() > 0
    }

    override suspend fun run(ctx: MoveGenCtx) {
        val (board) = ctx.data

        val empty = board.empty()
        val pushMovesFrom = wAbleToPush(board.whitePawns, empty)
        val pushMovesTo = CompassRose.navigate(pushMovesFrom, Direction.N)
        val pushTwoMovesFrom = wAbleToPushTwo(board.whitePawns, empty)
        val pushTwoMovesTo = CompassRose.navigate(pushTwoMovesFrom, Direction.N, 2)

        ctx.addMoves(PawnPushPairToPseudoMoves(pushMovesFrom to pushMovesTo, 'P').output)
        ctx.addMoves(PawnPushPairToPseudoMoves(pushTwoMovesFrom to pushTwoMovesTo, 'P').output)
    }

    private fun wAbleToPush(pawns: ULong, empty: ULong): ULong {
        return CompassRose.navigate(empty, Direction.S).and(pawns)
    }

    private fun wAbleToPushTwo(pawns: ULong, empty: ULong): ULong {
        val emptyRank3 = CompassRose.navigate(empty.and(Sets.RANK4), Direction.S).and(empty)
        return wAbleToPush(pawns, emptyRank3)
    }

    private fun handlePromotion(targetSquares: ULong, ctx: MoveGenCtx) {
        val promotionTargets = Sets.RANK8 and targetSquares
        if (promotionTargets == 0UL) return


    }
}