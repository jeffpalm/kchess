package engine.move.rules

import engine.Color
import engine.Piece
import engine.Square
import engine.adapter.BitBitsPairToPseudoMoves
import engine.adapter.BitsToListOfBit
import engine.move.IMoveRule
import engine.move.Magic
import engine.move.MoveGenCtx

class MoveRuleBlackPawnAttack : IMoveRule {
    override fun shouldRun(ctx: MoveGenCtx): Boolean {
        return ctx.data.turn == Color.BLACK && ctx.data.board.bPawns.countOneBits() > 0
    }

    override suspend fun run(ctx: MoveGenCtx) {
        val (board) = ctx.data

        val pawns = BitsToListOfBit(board.bPawns).output

        for (pawn in pawns) {
            val potentialAttackSquares = Magic.Attack.BlackPawn[Square[pawn]]
            val validAttackSquares = board.occupied(Color.WHITE).or(board.enPassantTarget ?: 0UL)

            val validAttacks = potentialAttackSquares and validAttackSquares
            ctx.addMoves(BitBitsPairToPseudoMoves(pawn to validAttacks, Piece.bPawn).output)
        }
    }
//
//    private fun bPawnEastAttacks(pawns: ULong): ULong {
//        return Compass.navigate(pawns, Direction.SE) and Sets.NOT_A_FILE
//    }
//
//    private fun bPawnWestAttacks(pawns: ULong): ULong {
//        return Compass.navigate(pawns, Direction.SW) and Sets.NOT_H_FILE
//    }
}