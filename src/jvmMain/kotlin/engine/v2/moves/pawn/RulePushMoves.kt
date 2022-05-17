package engine.v2.moves.pawn

import engine.v2.*
import engine.v2.adapters.WordPairToPseudoMoves
import engine.v2.moves.AbstractRule
import engine.v2.moves.PseudoMoveGenContext

class RulePushMoves(context: PseudoMoveGenContext) : AbstractRule<PseudoMoveGenContext>(context) {
    override fun shouldRun(): Boolean {
        val (board, turn) = context.gameData
        return when (turn) {
            PieceColor.WHITE -> shouldWhitePawnsRun(board)
            PieceColor.BLACK -> shouldBlackPawnsRun(board)
        }
    }

    override fun run(): PseudoMoveGenContext {
        when (context.gameData.turn) {
            PieceColor.WHITE -> runWhitePawns()
            PieceColor.BLACK -> runBlackPawns()
        }
        return context
    }

    private fun runWhitePawns() {
        val (board) = context.gameData

        val empty = board.empty()
        val pushMovesFrom = wAbleToPush(board.whitePawns, empty)
        val pushMovesTo = CompassRose.navigate(pushMovesFrom, Direction.NORTH)
        val pushTwoMovesFrom = wAbleToPushTwo(board.whitePawns, empty)
        val pushTwoMovesTo = CompassRose.navigate(pushTwoMovesFrom, Direction.NORTH, 2)

        context.addMoves(WordPairToPseudoMoves(pushMovesFrom to pushMovesTo).output)
        context.addMoves(WordPairToPseudoMoves(pushTwoMovesFrom to pushTwoMovesTo).output)
    }

    private fun runBlackPawns() {
        val (board) = context.gameData

        val empty = board.empty()
        val pushMovesFrom = bAbleToPush(board.blackPawns, empty)
        val pushMovesTo = CompassRose.navigate(pushMovesFrom, Direction.SOUTH)
        val pushTwoMovesFrom = bAbleToPushTwo(board.blackPawns, empty)
        val pushTwoMovesTo = CompassRose.navigate(pushTwoMovesFrom, Direction.SOUTH, 2)

        context.addMoves(WordPairToPseudoMoves(pushMovesFrom to pushMovesTo).output)
        context.addMoves(WordPairToPseudoMoves(pushTwoMovesFrom to pushTwoMovesTo).output)
    }

    private fun shouldWhitePawnsRun(board: BitBoard): Boolean {
        return board.whitePawns.countOneBits() > 0
    }

    private fun shouldBlackPawnsRun(board: BitBoard): Boolean {
        return board.blackPawns.countOneBits() > 0
    }

    private fun wAbleToPush(pawns: ULong, empty: ULong): ULong {
        return CompassRose.navigate(empty, Direction.SOUTH).and(pawns)
    }

    private fun wAbleToPushTwo(pawns: ULong, empty: ULong): ULong {
        val emptyRank3 = CompassRose.navigate(empty.and(Sets.RANK_4), Direction.SOUTH).and(empty)
        return wAbleToPush(pawns, emptyRank3)
    }

    private fun bAbleToPush(pawns: ULong, empty: ULong): ULong {
        return CompassRose.navigate(empty, Direction.NORTH).and(pawns)
    }

    private fun bAbleToPushTwo(pawns: ULong, empty: ULong): ULong {
        val emptyRank5 = CompassRose.navigate(empty.and(Sets.RANK_6), Direction.NORTH).and(empty)
        return bAbleToPush(pawns, emptyRank5)
    }
}