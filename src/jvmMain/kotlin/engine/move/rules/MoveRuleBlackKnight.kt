package engine.move.rules

import engine.Color
import engine.Compass
import engine.Piece
import engine.adapter.WordPairToKnightPseudoMoves
import engine.move.IMoveRule
import engine.move.MoveGenCtx

class MoveRuleBlackKnight : IMoveRule {
    override fun shouldRun(ctx: MoveGenCtx): Boolean {
        return ctx.data.turn == Color.BLACK && ctx.data.board.blackKnights.countOneBits() > 0
    }

    override suspend fun run(ctx: MoveGenCtx) {
        val (board) = ctx.data
        val moveTargets = Compass.knightMoveTargets(board.blackKnights)
        val legalMoveTargets = moveTargets and board.occupied(Color.BLACK).inv()

        if (legalMoveTargets.countOneBits() > 0) {
            ctx.addMoves(WordPairToKnightPseudoMoves(board.blackKnights to legalMoveTargets, Piece.blackKnight).output)
        }
    }
}