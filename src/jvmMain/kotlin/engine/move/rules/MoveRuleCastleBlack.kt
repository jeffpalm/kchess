package engine.move.rules

import engine.Color
import engine.SquareMap
import engine.Direction
import engine.Piece
import engine.Square
import engine.move.IMoveRule
import engine.move.MoveGenCtx
import engine.move.PseudoMove

private const val kingSideRook = Square.h8
private const val queenSideRook = Square.a8

class MoveRuleCastleBlack : IMoveRule {
    override fun shouldRun(ctx: MoveGenCtx): Boolean {
        val turn = ctx.data.turn
        val castlingAvail = ctx.data.castleAvail
        return turn == Color.BLACK && (castlingAvail.contains('k') || castlingAvail.contains('q'))
    }

    override suspend fun run(ctx: MoveGenCtx) {
        val (board) = ctx.data
        if (board.rayMoves(kingSideRook, Direction.W, Color.BLACK) == 0x6000000000000000UL) {
            ctx.addMove(PseudoMove(SquareMap.e1, SquareMap.g1, Piece.blackKing))
        }
        if (board.rayMoves(queenSideRook, Direction.E, Color.BLACK) == 0xE00000000000000UL) {
            ctx.addMove(PseudoMove(SquareMap.e1, SquareMap.c1, Piece.blackKing))
        }
    }
}