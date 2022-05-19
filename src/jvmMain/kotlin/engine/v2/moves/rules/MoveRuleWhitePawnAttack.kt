package engine.v2.moves.rules

import engine.v2.CompassRose
import engine.v2.Direction
import engine.v2.PieceColor
import engine.v2.Sets
import engine.v2.adapters.MultiBitToListOfOneBits
import engine.v2.adapters.OneBitMultiBitPairToPseudoMoves
import engine.v2.moves.AbstractMoveRule
import engine.v2.moves.MoveGenCtx

class MoveRuleWhitePawnAttack(context: MoveGenCtx) : AbstractMoveRule<MoveGenCtx>(context) {
    override fun shouldRun(): Boolean {
        return context.gameData.turn == PieceColor.WHITE && context.gameData.board.whitePawns.countOneBits() > 0
    }

    override suspend fun run() {
        val (board) = context.gameData

        val pawns = MultiBitToListOfOneBits(board.whitePawns).output

        for (pawn in pawns) {
            val valEastAttacks = wPawnEastAttacks(pawn) and board.occupied(PieceColor.BLACK)
            val valWestAttacks = wPawnWestAttacks(pawn) and board.occupied(PieceColor.BLACK)
            context.addMoves(OneBitMultiBitPairToPseudoMoves(pawn to valEastAttacks).output)
            context.addMoves(OneBitMultiBitPairToPseudoMoves(pawn to valWestAttacks).output)
        }

    }

    private fun wPawnEastAttacks(pawns: ULong): ULong {
        return CompassRose.navigate(pawns, Direction.NE) and Sets.NOT_A_FILE
    }

    private fun wPawnWestAttacks(pawns: ULong): ULong {
        return CompassRose.navigate(pawns, Direction.NW) and Sets.NOT_H_FILE
    }
}