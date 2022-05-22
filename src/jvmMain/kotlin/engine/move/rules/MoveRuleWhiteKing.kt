package engine.move.rules

import engine.Color
import engine.CompassRose
import engine.Piece
import engine.adapter.BitBitsPairToPseudoMoves
import engine.move.IMoveRule
import engine.move.MoveGenCtx

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
            ctx.addMoves(BitBitsPairToPseudoMoves(board.whiteKing to validTargetSquares, Piece.whiteKing).output)
        }
    }
}