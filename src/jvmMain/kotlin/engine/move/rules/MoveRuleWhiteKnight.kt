package engine.move.rules

import engine.Color
import engine.CompassRose
import engine.Piece
import engine.adapter.WordPairToKnightPseudoMoves
import engine.move.IMoveRule
import engine.move.MoveGenCtx

class MoveRuleWhiteKnight : IMoveRule {
    override fun shouldRun(ctx: MoveGenCtx): Boolean {
        return ctx.data.turn == Color.WHITE && ctx.data.board.whiteKnights.countOneBits() > 0
    }

    override suspend fun run(ctx: MoveGenCtx) {
        val (board) = ctx.data
        val moveTargets = CompassRose.knightMoveTargets(board.whiteKnights)
        val legalMoveTargets = moveTargets and board.occupied(Color.WHITE).inv()

        if (legalMoveTargets.countOneBits() > 0) {
            ctx.addMoves(WordPairToKnightPseudoMoves(board.whiteKnights to legalMoveTargets, Piece.whiteKnight).output)
        }
    }
}