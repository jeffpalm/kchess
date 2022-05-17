package engine.v2.moves.rules

import engine.v2.CompassRose
import engine.v2.PieceColor
import engine.v2.adapters.WordPairToKnightPseudoMoves
import engine.v2.moves.AbstractMoveRule
import engine.v2.moves.PseudoMoveGenContext

class MoveRuleWhiteKnight(context: PseudoMoveGenContext) : AbstractMoveRule<PseudoMoveGenContext>(context) {
    override fun shouldRun(): Boolean {
        return context.gameData.turn == PieceColor.WHITE && context.gameData.board.whiteKnights.countOneBits() > 0
    }

    override suspend fun run() {
        val (board) = context.gameData
        val moveTargets = CompassRose.knightMoveTargets(board.whiteKnights)
        val legalMoveTargets = moveTargets and board.occupied(PieceColor.WHITE).inv()

        if (legalMoveTargets.countOneBits() > 0) {
            context.addMoves(WordPairToKnightPseudoMoves(board.whiteKnights to legalMoveTargets).output)
        }
    }
}