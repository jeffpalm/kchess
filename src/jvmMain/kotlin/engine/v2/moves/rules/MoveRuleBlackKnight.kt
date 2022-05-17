package engine.v2.moves.rules

import engine.v2.CompassRose
import engine.v2.PieceColor
import engine.v2.adapters.WordPairToKnightPseudoMoves
import engine.v2.moves.AbstractMoveRule
import engine.v2.moves.PseudoMoveGenContext

class MoveRuleBlackKnight(context: PseudoMoveGenContext) : AbstractMoveRule<PseudoMoveGenContext>(context) {
    override fun shouldRun(): Boolean {
        return context.gameData.turn == PieceColor.BLACK && context.gameData.board.blackKnights.countOneBits() > 0
    }

    override suspend fun run() {
        val (board) = context.gameData
        val moveTargets = CompassRose.knightMoveTargets(board.blackKnights)
        val legalMoveTargets = moveTargets and board.occupied(PieceColor.BLACK).inv()

        if (legalMoveTargets.countOneBits() > 0) {
            context.addMoves(WordPairToKnightPseudoMoves(board.blackKnights to legalMoveTargets).output)
        }
    }
}