package engine.v2.moves.rules

import engine.Color
import engine.SquareMap
import engine.v2.Direction
import engine.v2.Piece
import engine.v2.Square
import engine.v2.moves.IMoveRule
import engine.v2.moves.MoveGenCtx
import engine.v2.moves.PseudoMove

private const val kingSideRook = Square.h1
private const val queenSideRook = Square.a1

class MoveRuleCastleWhite : IMoveRule {
    override fun shouldRun(ctx: MoveGenCtx): Boolean {
        val turn = ctx.data.turn
        val castlingAvail = ctx.data.castleAvail
        return turn == Color.WHITE && (castlingAvail.contains('K') || castlingAvail.contains('Q'))
    }

    override suspend fun run(ctx: MoveGenCtx) {
        val (board) = ctx.data
        if (board.rayMoves(kingSideRook, Direction.W, Color.WHITE) == 0x60UL) {
            ctx.addMove(PseudoMove(SquareMap.e1, SquareMap.g1, Piece.whiteKing))
        }
        if (board.rayMoves(queenSideRook, Direction.E, Color.WHITE) == 0xEUL) {
            ctx.addMove(PseudoMove(SquareMap.e1, SquareMap.c1, Piece.whiteKing))
        }
    }
}