package engine.v2.moves.rules

import engine.Color
import engine.v2.Direction
import engine.v2.Piece
import engine.v2.adapters.BitBitsPairToPseudoMoves
import engine.v2.adapters.BitsToListOfBit
import engine.v2.moves.IMoveRule
import engine.v2.moves.MoveGenCtx

class MoveRuleWhiteQueen : IMoveRule {
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

    override fun shouldRun(ctx: MoveGenCtx): Boolean {
        return ctx.data.turn == Color.WHITE && ctx.data.board.whiteQueens.countOneBits() > 0
    }

    override suspend fun run(ctx: MoveGenCtx) {
        val (board) = ctx.data

        val individualQueens = BitsToListOfBit(board.whiteQueens).output
        for (queen in individualQueens) {
            for (direction in directions) {
                handleTargetSquares(queen, direction, ctx)
            }
        }
    }

    private fun handleTargetSquares(x: ULong, direction: Direction, ctx: MoveGenCtx) {
        val targetSquares = ctx.data.board.rayMoves(x, direction, Color.WHITE)
        ctx.addMoves(BitBitsPairToPseudoMoves(x to targetSquares, Piece.whiteQueen).output)
    }

}