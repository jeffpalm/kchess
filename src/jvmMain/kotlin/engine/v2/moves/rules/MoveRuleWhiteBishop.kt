package engine.v2.moves.rules

import engine.v2.Direction
import engine.v2.PieceColor
import engine.v2.adapters.OneBitWordPairToPseudoMoves
import engine.v2.adapters.WordToOneBitWords
import engine.v2.moves.AbstractMoveRule
import engine.v2.moves.PseudoMoveGenContext

class MoveRuleWhiteBishop(context: PseudoMoveGenContext) : AbstractMoveRule<PseudoMoveGenContext>(context) {
    private val directions: List<Direction> = listOf(
        Direction.NE,
        Direction.NW,
        Direction.SW,
        Direction.SE
    )

    override fun shouldRun(): Boolean {
        return context.gameData.turn == PieceColor.WHITE && context.gameData.board.whiteBishops.countOneBits() > 0
    }

    override suspend fun run() {
        val (board) = context.gameData

        val individualBishops = WordToOneBitWords(board.whiteBishops).output
        for (bishop in individualBishops) {
            for (direction in directions) {
                handleTargetSquares(bishop, direction)
            }
        }
    }

    private fun handleTargetSquares(x: ULong, direction: Direction) {
        val targetSquares = context.gameData.board.rayMoves(x, direction, PieceColor.WHITE)
        context.addMoves(OneBitWordPairToPseudoMoves(x to targetSquares).output)
    }

}