package engine.v2.moves.rules

import engine.v2.CompassRose
import engine.v2.Direction
import engine.v2.PieceColor
import engine.v2.Sets
import engine.v2.adapters.BitsBitsPairToPseudoMoves
import engine.v2.moves.AbstractMoveRule
import engine.v2.moves.MoveGenCtx

class MoveRuleBlackPawnPush(context: MoveGenCtx) : AbstractMoveRule<MoveGenCtx>(context) {
    override fun shouldRun(): Boolean {
        return context.gameData.turn == PieceColor.BLACK && context.gameData.board.blackPawns.countOneBits() > 0
    }

    override suspend fun run() {
        val (board) = context.gameData

        val empty = board.empty()
        val pushMovesFrom = bAbleToPush(board.blackPawns, empty)
        val pushMovesTo = CompassRose.navigate(pushMovesFrom, Direction.S)
        val pushTwoMovesFrom = bAbleToPushTwo(board.blackPawns, empty)
        val pushTwoMovesTo = CompassRose.navigate(pushTwoMovesFrom, Direction.S, 2)

        context.addMoves(BitsBitsPairToPseudoMoves(pushMovesFrom to pushMovesTo).output)
        context.addMoves(BitsBitsPairToPseudoMoves(pushTwoMovesFrom to pushTwoMovesTo).output)
    }

    private fun bAbleToPush(pawns: ULong, empty: ULong): ULong {
        return CompassRose.navigate(empty, Direction.N).and(pawns)
    }

    private fun bAbleToPushTwo(pawns: ULong, empty: ULong): ULong {
        val emptyRank5 = CompassRose.navigate(empty.and(Sets.RANK5), Direction.N).and(empty)
        return bAbleToPush(pawns, emptyRank5)
    }
}