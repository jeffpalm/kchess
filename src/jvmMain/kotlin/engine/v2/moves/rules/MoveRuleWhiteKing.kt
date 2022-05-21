package engine.v2.moves.rules

import engine.v2.Color
import engine.v2.CompassRose
import engine.v2.adapters.BitBitsPairToPseudoMoves
import engine.v2.moves.IMoveRule
import engine.v2.moves.MoveGenCtx

class MoveRuleWhiteKing : IMoveRule {
    override fun shouldRun(ctx: MoveGenCtx): Boolean {
        return ctx.data.turn == Color.WHITE
    }

    override suspend fun run(ctx: MoveGenCtx) {
        val (board) = ctx.data
        val targetSquares = CompassRose.kingMoveTargets(board.whiteKing)
        val enemyPawnAttacks = CompassRose.pawnAttackTargets(board.blackPawns, Color.BLACK)

        val validTargetSquares = (targetSquares and board.occupied(Color.WHITE).inv()) and enemyPawnAttacks.inv()

        if (validTargetSquares.countOneBits() > 0) {
            ctx.addMoves(BitBitsPairToPseudoMoves(board.whiteKing to validTargetSquares).output)
        }
    }
}