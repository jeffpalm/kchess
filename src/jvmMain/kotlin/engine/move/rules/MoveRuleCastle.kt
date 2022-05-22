package engine.move.rules

import engine.Color
import engine.SquareMap
import engine.Direction
import engine.Piece
import engine.Square
import engine.move.IMoveRule
import engine.move.MoveGenCtx
import engine.move.PseudoMove

class MoveRuleCastle : IMoveRule {
    override fun shouldRun(ctx: MoveGenCtx): Boolean {
        val (board, turn, castlingAvail) = ctx.data
        return (castlingAvail.contains(Piece.king(turn)) || castlingAvail.contains(Piece.queen(turn)))
    }

    override suspend fun run(ctx: MoveGenCtx) {
        val (board, turn) = ctx.data
        when (turn) {
            Color.WHITE -> {
                if (board.rayMoves(Square.h1, Direction.W, Color.WHITE) == 0x60UL) {
                    ctx.addMove(PseudoMove(SquareMap.e1, SquareMap.g1, Piece.whiteKing))
                }
                if (board.rayMoves(Square.a1, Direction.E, Color.WHITE) == 0xEUL) {
                    ctx.addMove(PseudoMove(SquareMap.e1, SquareMap.c1, Piece.whiteKing))
                }
            }
            Color.BLACK -> {
                if (board.rayMoves(Square.h8, Direction.W, Color.BLACK) == 0x6000000000000000UL) {
                    ctx.addMove(PseudoMove(SquareMap.e1, SquareMap.g1, Piece.blackKing))
                }
                if (board.rayMoves(Square.a8, Direction.E, Color.BLACK) == 0xE00000000000000UL) {
                    ctx.addMove(PseudoMove(SquareMap.e1, SquareMap.c1, Piece.blackKing))
                }
            }
        }
    }

}