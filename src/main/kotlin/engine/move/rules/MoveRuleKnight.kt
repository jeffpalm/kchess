package engine.move.rules

import engine.Compass
import engine.Piece
import engine.adapter.WordPairToKnightPseudoMoves
import engine.move.IMoveRule
import engine.move.MoveGenCtx

class MoveRuleKnight : IMoveRule {
    override fun shouldRun(ctx: MoveGenCtx): Boolean {
        val (board, turn) = ctx.data
        return board.knights(turn) != 0UL
    }

    override suspend fun run(ctx: MoveGenCtx) {
        val (board, turn) = ctx.data
        val moveTargets = Compass.knightMoveTargets(board.knights(turn))
        val legalMoveTargets = moveTargets and board.occupied(turn).inv()

        if (legalMoveTargets.countOneBits() > 0) {
            ctx.addMoves(WordPairToKnightPseudoMoves(board.knights(turn) to legalMoveTargets, Piece.knight(turn)).output)
        }
    }
}