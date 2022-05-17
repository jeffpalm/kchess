package engine.v2.moves.pawn

import engine.v2.CompassRose
import engine.v2.Direction
import engine.v2.PieceColor
import engine.v2.Sets
import engine.v2.adapters.WordPairToPseudoMoves
import engine.v2.moves.AbstractRule
import engine.v2.moves.PseudoMoveGenContext

class RuleAttackMoves(context: PseudoMoveGenContext) : AbstractRule<PseudoMoveGenContext>(context) {
    override fun shouldRun(): Boolean {
        return when (context.gameData.turn) {
            PieceColor.WHITE -> context.gameData.board.whitePawns.countOneBits() > 0
            PieceColor.BLACK -> context.gameData.board.blackPawns.countOneBits() > 0
        }
    }

    override fun run(): PseudoMoveGenContext {
        return when (context.gameData.turn) {
            PieceColor.WHITE -> runWhitePawns()
            PieceColor.BLACK -> runBlackPawns()
        }
    }

    private fun wPawnEastAttacks(pawns: ULong): ULong {
        return CompassRose.navigate(pawns, Direction.NORTHEAST) and Sets.NOT_A_FILE
    }

    private fun wPawnWestAttacks(pawns: ULong): ULong {
        return CompassRose.navigate(pawns, Direction.NORTHWEST) and Sets.NOT_H_FILE
    }

    private fun bPawnEastAttacks(pawns: ULong): ULong {
        return CompassRose.navigate(pawns, Direction.SOUTHEAST) and Sets.NOT_A_FILE
    }

    private fun bPawnWestAttacks(pawns: ULong): ULong {
        return CompassRose.navigate(pawns, Direction.SOUTHWEST) and Sets.NOT_H_FILE
    }

    private fun runWhitePawns(): PseudoMoveGenContext {
        val (board) = context.gameData
        val valEastAttacks = wPawnEastAttacks(board.whitePawns) and board.occupied(PieceColor.BLACK)
        val valWestAttacks = wPawnWestAttacks(board.whitePawns) and board.occupied(PieceColor.BLACK)
        val valEastPawns = board.whitePawns and Sets.NOT_A_FILE
        val valWestPawns = board.whitePawns and Sets.NOT_H_FILE

        context.addMoves(WordPairToPseudoMoves(valEastPawns to valEastAttacks).output)
        context.addMoves(WordPairToPseudoMoves(valWestPawns to valWestAttacks).output)

        return context
    }

    private fun runBlackPawns(): PseudoMoveGenContext {
        val (board) = context.gameData
        val valEastAttacks = bPawnEastAttacks(board.blackPawns) and board.occupied(PieceColor.WHITE)
        val valWestAttacks = bPawnWestAttacks(board.blackPawns) and board.occupied(PieceColor.WHITE)
        val valEastPawns = board.blackPawns and Sets.NOT_A_FILE
        val valWestPawns = board.blackPawns and Sets.NOT_H_FILE

        context.addMoves(WordPairToPseudoMoves(valEastPawns to valEastAttacks).output)
        context.addMoves(WordPairToPseudoMoves(valWestPawns to valWestAttacks).output)

        return context
    }
}