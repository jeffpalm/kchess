package engine.move.rules

import engine.Piece
import engine.Square
import engine.move.IMoveRule
import engine.move.Magic
import engine.move.MoveGenCtx

object MoveRuleKing : IMoveRule {
    override fun shouldRun(ctx: MoveGenCtx): Boolean = true

    override fun run(ctx: MoveGenCtx) {
        val (board, turn) = ctx.data
        val king = board.king(turn)
        val attacks = Magic.Attack[Square.fromBit(king), Piece.king(turn)]
        val notOwn = board.occupied(turn).inv()
        val safe = ctx.enemyAttacks().inv()
        val targets = attacks and notOwn and safe
        ctx.addBitMoves(king, targets, Piece.king(turn))
    }
}
