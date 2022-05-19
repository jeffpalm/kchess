package engine.v2.moves.rules

import engine.v2.Direction
import engine.v2.PieceColor
import engine.v2.adapters.BitsToListOfBit
import engine.v2.adapters.BitBitsPairToPseudoMoves
import engine.v2.moves.AbstractMoveRule
import engine.v2.moves.MoveGenCtx

class MoveRuleWhiteQueen(context: MoveGenCtx) : AbstractMoveRule<MoveGenCtx>(context) {
    private val directions: List<Direction> = listOf(
        Direction.N,
        Direction.W,
        Direction.S,
        Direction.E,
        Direction.NE,
        Direction.NW,
        Direction.SW,
        Direction.SE
    )

    override fun shouldRun(): Boolean {
        return context.gameData.turn == PieceColor.WHITE && context.gameData.board.whiteQueens.countOneBits() > 0
    }

    override suspend fun run() {
        val (board) = context.gameData

        val individualQueens = BitsToListOfBit(board.whiteQueens).output
        for (queen in individualQueens) {
            for (direction in directions) {
                handleTargetSquares(queen, direction)
            }
        }
    }

    private fun handleTargetSquares(x: ULong, direction: Direction) {
        val targetSquares = context.gameData.board.rayMoves(x, direction, PieceColor.WHITE)
        context.addMoves(BitBitsPairToPseudoMoves(x to targetSquares).output)
    }

}