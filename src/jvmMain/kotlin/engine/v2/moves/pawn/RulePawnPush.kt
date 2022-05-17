package engine.v2.moves.pawn

import engine.v2.BitBoard
import engine.v2.CompassRose
import engine.v2.Direction
import engine.v2.PieceColor
import engine.v2.moves.IRule

class RulePawnPush(context: PawnContext) : IRule<PawnContext>(context) {
    override fun shouldRun(): Boolean {
        val (board, turn) = context.gameData
        return when (turn) {
            PieceColor.WHITE -> shouldWhitePawnsRun(board)
            PieceColor.BLACK -> shouldBlackPawnsRun(board)
        }
    }

    override fun run(): PawnContext {
        val (board, turn) = context.gameData
        context.moveWords.add(
            when (turn) {
                PieceColor.WHITE -> whitePawnsAbleToPush(board._P, board.empty())
                PieceColor.BLACK -> blackPawnsAbleToPush(board._p, board.empty())
            }
        )
        return context
    }

    private fun shouldWhitePawnsRun(board: BitBoard): Boolean {
        return board._P.countOneBits() > 0
    }

    private fun shouldBlackPawnsRun(board: BitBoard): Boolean {
        return board._p.countOneBits() > 0
    }

    private fun whitePawnsAbleToPush(pawns: ULong, empty: ULong): ULong {
        return CompassRose.navigate(empty, Direction.SOUTH).and(pawns)
    }

    private fun blackPawnsAbleToPush(pawns: ULong, empty: ULong): ULong {
        return CompassRose.navigate(empty, Direction.NORTH).and(pawns)
    }
}