package engine.move.rules

import engine.*
import engine.adapter.PawnPushPairToPseudoMoves
import engine.move.IMoveRule
import engine.move.MoveGenCtx

class MoveRuleBlackPawnPush : IMoveRule {
    override fun shouldRun(ctx: MoveGenCtx): Boolean {
        return ctx.data.turn == Color.BLACK && ctx.data.board.bPawns.countOneBits() > 0
    }

    override suspend fun run(ctx: MoveGenCtx) {
        val (board) = ctx.data

        val empty = board.empty()
        val pushMovesFrom = bAbleToPush(board.bPawns, empty)
        val pushMovesTo = Compass.navigate(pushMovesFrom, Direction.S)
        val pushTwoMovesFrom = bAbleToPushTwo(board.bPawns, empty)
        val pushTwoMovesTo = Compass.navigate(pushTwoMovesFrom, Direction.S, 2)

        ctx.addMoves(PawnPushPairToPseudoMoves(pushMovesFrom to pushMovesTo, Piece.bPawn).output)
        ctx.addMoves(PawnPushPairToPseudoMoves(pushTwoMovesFrom to pushTwoMovesTo, Piece.bPawn).output)
    }

    private fun bAbleToPush(pawns: ULong, empty: ULong): ULong {
        return Compass.navigate(empty, Direction.N).and(pawns)
    }

    private fun bAbleToPushTwo(pawns: ULong, empty: ULong): ULong {
        val emptyRank5 = Compass.navigate(empty.and(Sets.RANK5), Direction.N).and(empty)
        return bAbleToPush(pawns, emptyRank5)
    }
}