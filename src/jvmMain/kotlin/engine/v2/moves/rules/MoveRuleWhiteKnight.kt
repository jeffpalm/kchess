package engine.v2.moves.rules

import engine.Color
import engine.v2.CompassRose
import engine.v2.Piece
import engine.v2.adapters.WordPairToKnightPseudoMoves
import engine.v2.moves.IMoveRule
import engine.v2.moves.MoveGenCtx

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