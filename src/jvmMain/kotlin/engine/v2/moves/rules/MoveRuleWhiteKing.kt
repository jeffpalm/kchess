package engine.v2.moves.rules

import engine.v2.CompassRose
import engine.v2.PieceColor
import engine.v2.adapters.WordPairToPseudoMoves
import engine.v2.moves.AbstractMoveRule
import engine.v2.moves.PseudoMoveGenContext

class MoveRuleWhiteKing(context: PseudoMoveGenContext) : AbstractMoveRule<PseudoMoveGenContext>(context) {
    override fun shouldRun(): Boolean {
        return context.gameData.turn == PieceColor.WHITE
    }

    override suspend fun run() {
        val (board) = context.gameData
        val targetSquares = CompassRose.kingMoveTargets(board.whiteKing)
        val validTargetSquares = targetSquares and board.occupied(PieceColor.WHITE).inv()

        if (validTargetSquares.countOneBits() > 0) {
            context.addMoves(WordPairToPseudoMoves(targetSquares to validTargetSquares).output)
        }
    }
}