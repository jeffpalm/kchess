package engine.move.rules

import engine.Color
import engine.Compass
import engine.Piece
import engine.adapter.BitBitsPairToPseudoMoves
import engine.move.IMoveRule
import engine.move.MoveGenCtx

class MoveRuleBlackKing : IMoveRule {
    override fun shouldRun(ctx: MoveGenCtx): Boolean {
        return ctx.data.turn == Color.BLACK
    }

    override suspend fun run(ctx: MoveGenCtx) {
        val (board) = ctx.data
        val targetSquares = Compass.kingMoveTargets(board.blackKing)
        val enemyPawnAttacks = Compass.pawnAttackTargets(board.whitePawns, Color.WHITE)

        val validTargetSquares = (targetSquares and board.occupied(Color.BLACK).inv()) and enemyPawnAttacks.inv()

        if (validTargetSquares.countOneBits() > 0) {
            ctx.addMoves(BitBitsPairToPseudoMoves(board.blackKing to validTargetSquares, Piece.blackKing).output)
        }
    }
}