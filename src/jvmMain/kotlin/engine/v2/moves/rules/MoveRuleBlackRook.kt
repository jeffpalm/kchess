package engine.v2.moves.rules

import engine.v2.Direction
import engine.v2.PieceColor
import engine.v2.adapters.MultiBitToListOfOneBits
import engine.v2.adapters.OneBitMultiBitPairToPseudoMoves
import engine.v2.moves.AbstractMoveRule
import engine.v2.moves.MoveGenCtx

class MoveRuleBlackRook(context: MoveGenCtx) : AbstractMoveRule<MoveGenCtx>(context) {
    private val directions: List<Direction> = listOf(
        Direction.N,
        Direction.W,
        Direction.S,
        Direction.E
    )

    override fun shouldRun(): Boolean {
        return context.gameData.turn == PieceColor.BLACK && context.gameData.board.blackRooks.countOneBits() > 0
    }

    override suspend fun run() {
        val (board) = context.gameData

        val individualRooks = MultiBitToListOfOneBits(board.blackRooks).output
        for (rook in individualRooks) {
            for (direction in directions) {
                handleTargetSquares(rook, direction)
            }
        }
    }

    private fun handleTargetSquares(x: ULong, direction: Direction) {
        val targetSquares = context.gameData.board.rayMoves(x, direction, PieceColor.BLACK)
        context.addMoves(OneBitMultiBitPairToPseudoMoves(x to targetSquares).output)
    }

}