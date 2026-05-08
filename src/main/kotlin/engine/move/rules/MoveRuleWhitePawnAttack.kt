package engine.move.rules

import engine.Color
import engine.Piece
import engine.Square
import engine.move.IMoveRule
import engine.move.Magic
import engine.move.MoveGenCtx

object MoveRuleWhitePawnAttack : IMoveRule {
    override fun shouldRun(ctx: MoveGenCtx): Boolean {
        return ctx.data.turn == Color.WHITE && ctx.data.board.wPawns != 0UL
    }

    override fun run(ctx: MoveGenCtx) {
        val board = ctx.data.board
        val captureMask = board.occupied(Color.BLACK) or (board.enPassantTarget ?: 0UL)
        var w = board.wPawns
        while (w != 0UL) {
            val pawn = w.takeLowestOneBit()
            val targets = Magic.Attack.WhitePawn[Square.fromBit(pawn)] and captureMask
            ctx.addPawnTargets(pawn, targets, Piece.wPawn)
            w = w xor pawn
        }
    }
}
