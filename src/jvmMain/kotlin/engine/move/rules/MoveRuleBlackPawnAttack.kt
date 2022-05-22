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

class MoveRuleBlackPawnAttack : IMoveRule {
    override fun shouldRun(ctx: MoveGenCtx): Boolean {
        return ctx.data.turn == Color.BLACK && ctx.data.board.blackPawns.countOneBits() > 0
    }

    override suspend fun run(ctx: MoveGenCtx) {
        val (board) = ctx.data

        val pawns = BitsToListOfBit(board.blackPawns).output

        for(pawn in pawns) {
            val valEastAttacks = bPawnEastAttacks(pawn) and (board.occupied(Color.WHITE) or (board.enPassantTarget ?: 0UL))
            val valWestAttacks = bPawnWestAttacks(pawn) and (board.occupied(Color.WHITE) or (board.enPassantTarget ?: 0UL))

            ctx.addMoves(BitBitsPairToPseudoMoves(pawn to (valEastAttacks and Sets.NOT_A_FILE), Piece.blackPawn).output)
            ctx.addMoves(BitBitsPairToPseudoMoves(pawn to (valWestAttacks and Sets.NOT_H_FILE), Piece.blackPawn).output)
        }
    }

    private fun bPawnEastAttacks(pawns: ULong): ULong {
        return CompassRose.navigate(pawns, Direction.SE) and Sets.NOT_A_FILE
    }

    private fun bPawnWestAttacks(pawns: ULong): ULong {
        return CompassRose.navigate(pawns, Direction.SW) and Sets.NOT_H_FILE
    }
}