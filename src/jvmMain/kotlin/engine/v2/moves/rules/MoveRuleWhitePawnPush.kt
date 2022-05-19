package engine.v2.moves.rules

import engine.v2.CompassRose
import engine.v2.Direction
import engine.v2.PieceColor
import engine.v2.Sets
import engine.v2.adapters.MultiBitMultiBitPairToPseudoMoves
import engine.v2.moves.AbstractMoveRule
import engine.v2.moves.MoveGenCtx

class MoveRuleWhitePawnPush(context: MoveGenCtx) : AbstractMoveRule<MoveGenCtx>(context) {
    override fun shouldRun(): Boolean {
        return context.gameData.turn == PieceColor.WHITE && context.gameData.board.whitePawns.countOneBits() > 0
    }

    override suspend fun run() {
        val (board) = context.gameData

        val empty = board.empty()
        val pushMovesFrom = wAbleToPush(board.whitePawns, empty)
        val pushMovesTo = CompassRose.navigate(pushMovesFrom, Direction.N)
        val pushTwoMovesFrom = wAbleToPushTwo(board.whitePawns, empty)
        val pushTwoMovesTo = CompassRose.navigate(pushTwoMovesFrom, Direction.N, 2)

        context.addMoves(MultiBitMultiBitPairToPseudoMoves(pushMovesFrom to pushMovesTo).output)
        context.addMoves(MultiBitMultiBitPairToPseudoMoves(pushTwoMovesFrom to pushTwoMovesTo).output)
    }

    private fun wAbleToPush(pawns: ULong, empty: ULong): ULong {
        return CompassRose.navigate(empty, Direction.S).and(pawns)
    }

    private fun wAbleToPushTwo(pawns: ULong, empty: ULong): ULong {
        val emptyRank3 = CompassRose.navigate(empty.and(Sets.RANK4), Direction.S).and(empty)
        return wAbleToPush(pawns, emptyRank3)
    }
}