package engine.move.rules

import engine.Color
import engine.CompassRose
import engine.Direction
import engine.Piece
import engine.Sets
import engine.adapter.BitBitsPairToPseudoMoves
import engine.adapter.BitsToListOfBit
import engine.move.IMoveRule
import engine.move.MoveGenCtx

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
            ctx.addMoves(BitBitsPairToPseudoMoves(pawn to (valEastAttacks and Sets.NOT_A_FILE), Piece.whitePawn).output)
            ctx.addMoves(BitBitsPairToPseudoMoves(pawn to (valWestAttacks and Sets.NOT_H_FILE), Piece.whitePawn).output)
        }

    }

    private fun wPawnEastAttacks(pawns: ULong): ULong {
        return (CompassRose.navigate(pawns, Direction.NE) and Sets.NOT_A_FILE)
    }

    private fun wPawnWestAttacks(pawns: ULong): ULong {
        return (CompassRose.navigate(pawns, Direction.NW) and Sets.NOT_H_FILE)
    }
}