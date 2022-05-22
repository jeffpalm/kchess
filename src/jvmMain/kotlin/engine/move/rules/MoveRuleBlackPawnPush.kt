package engine.move.rules

import engine.Color
import engine.CompassRose
import engine.Direction
import engine.Piece
import engine.Sets
import engine.adapter.PawnPushPairToPseudoMoves
import engine.move.IMoveRule
import engine.move.MoveGenCtx

class MoveRuleBlackPawnPush : IMoveRule {
    override fun shouldRun(ctx: MoveGenCtx): Boolean {
        return ctx.data.turn == Color.BLACK && ctx.data.board.blackPawns.countOneBits() > 0
    }

    override suspend fun run(ctx: MoveGenCtx) {
        val (board) = ctx.data

        val empty = board.empty()
        val pushMovesFrom = bAbleToPush(board.blackPawns, empty)
        val pushMovesTo = CompassRose.navigate(pushMovesFrom, Direction.S)
        val pushTwoMovesFrom = bAbleToPushTwo(board.blackPawns, empty)
        val pushTwoMovesTo = CompassRose.navigate(pushTwoMovesFrom, Direction.S, 2)

        ctx.addMoves(PawnPushPairToPseudoMoves(pushMovesFrom to pushMovesTo, Piece.blackPawn).output)
        ctx.addMoves(PawnPushPairToPseudoMoves(pushTwoMovesFrom to pushTwoMovesTo, Piece.blackPawn).output)
    }

    private fun bAbleToPush(pawns: ULong, empty: ULong): ULong {
        return CompassRose.navigate(empty, Direction.N).and(pawns)
    }

    private fun bAbleToPushTwo(pawns: ULong, empty: ULong): ULong {
        val emptyRank5 = CompassRose.navigate(empty.and(Sets.RANK5), Direction.N).and(empty)
        return bAbleToPush(pawns, emptyRank5)
    }
}