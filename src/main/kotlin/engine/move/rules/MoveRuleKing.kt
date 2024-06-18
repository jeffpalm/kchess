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

        val occupiedInv = board.occupied(turn).inv()
//        Board(occupiedInv).log("occupiedInv")
        val allAttackTargets = board.allAttackTargets(turn.inv())
//        Board(allAttackTargets).log("allAttackTargets")
//        Board(allAttackTargets.inv()).log("allAttackTargetsInv")

        val validTargetSquares = targetSquares.and(occupiedInv) and allAttackTargets.inv()
//        Board(validTargetSquares).log("validTargetSquares")

        if (validTargetSquares.countOneBits() > 0) {
            ctx.addMoves(BitBitsPairToPseudoMoves(board.king(turn) to validTargetSquares, Piece.king(turn)).output)
        }
    }
}