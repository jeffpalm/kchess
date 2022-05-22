package engine.move.rules

import engine.Compass
import engine.Piece
import engine.adapter.BitBitsPairToPseudoMoves
import engine.move.IMoveRule
import engine.move.MoveGenCtx

class MoveRuleKing : IMoveRule {
    override fun shouldRun(ctx: MoveGenCtx): Boolean {
        return true
    }

    override suspend fun run(ctx: MoveGenCtx) {
        val (board, turn) = ctx.data
        val targetSquares = Compass.kingMoveTargets(board.king(turn))
//        val enemyPawnAttacks = Compass.pawnAttackTargets(board.pawns(turn.inv()), turn.inv())

        val validTargetSquares = (targetSquares and board.occupied(turn).inv()) and board.allAttackTargets(turn.inv()).inv()

        if (validTargetSquares.countOneBits() > 0) {
            ctx.addMoves(BitBitsPairToPseudoMoves(board.king(turn) to validTargetSquares, Piece.king(turn)).output)
        }
    }
}