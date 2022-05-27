package engine.move.rules

import engine.Piece
import engine.Square
import engine.adapter.BitBitsPairToPseudoMoves
import engine.move.IMoveRule
import engine.move.Magic
import engine.move.MoveGenCtx

class MoveRuleKing : IMoveRule {
    override fun shouldRun(ctx: MoveGenCtx): Boolean {
        return true
    }

    override suspend fun run(ctx: MoveGenCtx) {
        val (board, turn) = ctx.data
        val targetSquares = Magic.Attack[Square[board.king(turn)], Piece.king(turn)]

        val validTargetSquares = (targetSquares and board.occupied(turn).inv()) and board.allAttackTargets(turn.inv()).inv()

        if (validTargetSquares.countOneBits() > 0) {
            ctx.addMoves(BitBitsPairToPseudoMoves(board.king(turn) to validTargetSquares, Piece.king(turn)).output)
        }
    }
}