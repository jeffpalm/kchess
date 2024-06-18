package engine.move.rules

import engine.*
import engine.adapter.BitBitsPairToPseudoMoves
import engine.adapter.BitsToListOfBit
import engine.move.IMoveRule
import engine.move.Magic
import engine.move.MoveGenCtx

class MoveRuleWhitePawnAttack : IMoveRule {
    override fun shouldRun(ctx: MoveGenCtx): Boolean {
        return ctx.data.turn == Color.WHITE && ctx.data.board.wPawns.countOneBits() > 0
    }

    override suspend fun run(ctx: MoveGenCtx) {
        val (board) = ctx.data

        val pawns = BitsToListOfBit(board.wPawns).output

        for (pawn in pawns) {
            val potentialAttackSquares = Magic.Attack.WhitePawn[Square[pawn]]
            val validAttackSquares = board.occupied(Color.BLACK).or(board.enPassantTarget ?: 0UL)

            val validAttacks = potentialAttackSquares and validAttackSquares
            ctx.addMoves(BitBitsPairToPseudoMoves(pawn to validAttacks, Piece.wPawn).output)
        }

    }

    private fun wPawnEastAttacks(pawns: ULong): ULong {
        return (Compass.navigate(pawns, Direction.NE) and Sets.NOT_A_FILE)
    }

    private fun wPawnWestAttacks(pawns: ULong): ULong {
        return (Compass.navigate(pawns, Direction.NW) and Sets.NOT_H_FILE)
    }
}