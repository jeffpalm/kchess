package engine.move.rules

import engine.Color
import engine.Piece
import engine.Square
import engine.move.IMoveRule
import engine.move.Magic
import engine.move.MoveGenCtx

object MoveRuleBlackPawnAttack : IMoveRule {
    override fun shouldRun(ctx: MoveGenCtx): Boolean {
        return ctx.data.turn == Color.BLACK && ctx.data.board.bPawns != 0UL
    }

    override fun run(ctx: MoveGenCtx) {
        val board = ctx.data.board
        val captureMask = board.occupied(Color.WHITE) or (board.enPassantTarget ?: 0UL)
        var w = board.bPawns
        while (w != 0UL) {
            val pawn = w.takeLowestOneBit()
            val targets = Magic.Attack.BlackPawn[Square.fromBit(pawn)] and captureMask
            ctx.addPawnTargets(pawn, targets, Piece.bPawn)
            w = w xor pawn
        }
    }
}
