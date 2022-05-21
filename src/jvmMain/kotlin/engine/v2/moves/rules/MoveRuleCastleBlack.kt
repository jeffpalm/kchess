package engine.v2.moves.rules

import engine.v2.Direction
import engine.v2.Color
import engine.v2.Square
import engine.v2.SquareMap
import engine.v2.moves.IMoveRule
import engine.v2.moves.MoveGenCtx
import engine.v2.moves.PseudoMove

private const val kingSideRook = Square.h8
private const val queenSideRook = Square.a8

class MoveRuleCastleBlack : IMoveRule {
    override fun shouldRun(ctx: MoveGenCtx): Boolean {
        val turn = ctx.data.turn
        val castlingAvail = ctx.data.castleAvail
        return turn == Color.BLACK && castlingAvail.contains('k') || castlingAvail.contains('q')
    }

    override suspend fun run(ctx: MoveGenCtx) {
        val (board) = ctx.data
        if (board.rayMoves(kingSideRook, Direction.W, Color.BLACK) == 0x6000000000000000UL) {
            ctx.addMove(PseudoMove(SquareMap.e1, SquareMap.g1))
        }
        if (board.rayMoves(queenSideRook, Direction.E, Color.BLACK) == 0xE00000000000000UL) {
            ctx.addMove(PseudoMove(SquareMap.e1, SquareMap.c1))
        }
    }
}