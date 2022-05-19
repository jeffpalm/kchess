package engine.v2.moves.rules

import engine.v2.Direction
import engine.v2.PieceColor
import engine.v2.adapters.BitsToListOfBit
import engine.v2.adapters.BitBitsPairToPseudoMoves
import engine.v2.moves.AbstractMoveRule
import engine.v2.moves.MoveGenCtx

class MoveRuleBlackQueen(context: MoveGenCtx) : AbstractMoveRule<MoveGenCtx>(context) {
    private val directions: List<Direction> = listOf(
        Direction.N,
        Direction.W,
        Direction.S,
        Direction.E,
        Direction.NE,
        Direction.NW,
        Direction.SE,
        Direction.SW
    )

    override fun shouldRun(): Boolean {
        return context.gameData.turn == PieceColor.BLACK && context.gameData.board.blackQueens.countOneBits() > 0
    }

    override suspend fun run() {
        val (board) = context.gameData

        val individualQueens = BitsToListOfBit(board.blackQueens).output
        for (queen in individualQueens) {
            for (direction in directions) {
                handleTargetSquares(queen, direction)
            }
        }
    }

    private fun handleTargetSquares(x: ULong, direction: Direction) {
        val targetSquares = context.gameData.board.rayMoves(x, direction, PieceColor.BLACK)
        context.addMoves(BitBitsPairToPseudoMoves(x to targetSquares).output)
    }

}