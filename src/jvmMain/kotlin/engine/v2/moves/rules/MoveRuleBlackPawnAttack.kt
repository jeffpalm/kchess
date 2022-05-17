package engine.v2.moves.rules

import engine.v2.CompassRose
import engine.v2.Direction
import engine.v2.PieceColor
import engine.v2.Sets
import engine.v2.adapters.WordPairToPseudoMoves
import engine.v2.moves.AbstractMoveRule
import engine.v2.moves.PseudoMoveGenContext

class MoveRuleBlackPawnAttack(context: PseudoMoveGenContext) : AbstractMoveRule<PseudoMoveGenContext>(context) {
    override fun shouldRun(): Boolean {
        return context.gameData.turn == PieceColor.BLACK && context.gameData.board.blackPawns.countOneBits() > 0
    }

    override suspend fun run() {
        val (board) = context.gameData
        val valEastAttacks = bPawnEastAttacks(board.blackPawns) and board.occupied(PieceColor.WHITE)
        val valWestAttacks = bPawnWestAttacks(board.blackPawns) and board.occupied(PieceColor.WHITE)
        val valEastPawns = board.blackPawns and Sets.NOT_A_FILE
        val valWestPawns = board.blackPawns and Sets.NOT_H_FILE

        context.addMoves(WordPairToPseudoMoves(valEastPawns to valEastAttacks).output)
        context.addMoves(WordPairToPseudoMoves(valWestPawns to valWestAttacks).output)
    }

    private fun bPawnEastAttacks(pawns: ULong): ULong {
        return CompassRose.navigate(pawns, Direction.SE) and Sets.NOT_A_FILE
    }

    private fun bPawnWestAttacks(pawns: ULong): ULong {
        return CompassRose.navigate(pawns, Direction.SW) and Sets.NOT_H_FILE
    }
}