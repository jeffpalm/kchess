package engine.v2.moves.rules

import engine.v2.CompassRose
import engine.v2.PieceColor
import engine.v2.adapters.MultiBitMultiBitPairToPseudoMoves
import engine.v2.moves.AbstractMoveRule
import engine.v2.moves.MoveGenCtx

class MoveRuleBlackKing(context: MoveGenCtx) : AbstractMoveRule<MoveGenCtx>(context) {
    override fun shouldRun(): Boolean {
        return context.gameData.turn == PieceColor.BLACK
    }

    override suspend fun run() {
        val (board) = context.gameData
        val targetSquares = CompassRose.kingMoveTargets(board.blackKing)
        val validTargetSquares = targetSquares and board.occupied(PieceColor.BLACK).inv()

        if (validTargetSquares.countOneBits() > 0) {
            context.addMoves(MultiBitMultiBitPairToPseudoMoves(board.blackKing to validTargetSquares).output)
        }
    }
}