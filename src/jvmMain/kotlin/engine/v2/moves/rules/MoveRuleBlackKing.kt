package engine.v2.moves.rules

import engine.v2.Color
import engine.v2.CompassRose
import engine.v2.adapters.BitBitsPairToPseudoMoves
import engine.v2.moves.IMoveRule
import engine.v2.moves.MoveGenCtx

class MoveRuleBlackKing : IMoveRule {
    override fun shouldRun(ctx: MoveGenCtx): Boolean {
        return ctx.data.turn == Color.BLACK
    }

    override suspend fun run(ctx: MoveGenCtx) {
        val (board) = ctx.data
        val targetSquares = CompassRose.kingMoveTargets(board.blackKing)
        val enemyPawnAttacks = CompassRose.pawnAttackTargets(board.whitePawns, Color.WHITE)

        val validTargetSquares = (targetSquares and board.occupied(Color.BLACK).inv()) and enemyPawnAttacks.inv()

        if (validTargetSquares.countOneBits() > 0) {
            ctx.addMoves(BitBitsPairToPseudoMoves(board.blackKing to validTargetSquares).output)
        }
    }
}