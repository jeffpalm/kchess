package engine.v2.moves.rules

import engine.v2.CompassRose
import engine.v2.Direction
import engine.v2.Color
import engine.v2.Sets
import engine.v2.adapters.BitBitsPairToPseudoMoves
import engine.v2.adapters.BitsToListOfBit
import engine.v2.moves.IMoveRule
import engine.v2.moves.MoveGenCtx

class MoveRuleWhitePawnAttack : IMoveRule {
    override fun shouldRun(ctx: MoveGenCtx): Boolean {
        return ctx.data.turn == Color.WHITE && ctx.data.board.whitePawns.countOneBits() > 0
    }

    override suspend fun run(ctx: MoveGenCtx) {
        val (board) = ctx.data

        val pawns = BitsToListOfBit(board.whitePawns).output

        for (pawn in pawns) {
            val valEastAttacks = wPawnEastAttacks(pawn) and (board.occupied(Color.BLACK) or (board.enPassantTarget ?: 0UL))
            val valWestAttacks = wPawnWestAttacks(pawn) and (board.occupied(Color.BLACK) or (board.enPassantTarget ?: 0UL))
            ctx.addMoves(BitBitsPairToPseudoMoves(pawn to valEastAttacks).output)
            ctx.addMoves(BitBitsPairToPseudoMoves(pawn to valWestAttacks).output)
        }

    }

    private fun wPawnEastAttacks(pawns: ULong): ULong {
        return (CompassRose.navigate(pawns, Direction.NE) and Sets.NOT_A_FILE)
    }

    private fun wPawnWestAttacks(pawns: ULong): ULong {
        return (CompassRose.navigate(pawns, Direction.NW) and Sets.NOT_H_FILE)
    }
}