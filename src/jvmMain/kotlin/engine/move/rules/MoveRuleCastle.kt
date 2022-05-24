package engine.move.rules

import engine.*
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
                if (board.rayMoves(Sq.h1, Direction.W, Color.WHITE) == 0x60UL) {
                    ctx.addMove(PseudoMove(Square.e1, Square.g1, Piece.whiteKing))
                }
                if (board.rayMoves(Sq.a1, Direction.E, Color.WHITE) == 0xEUL) {
                    ctx.addMove(PseudoMove(Square.e1, Square.c1, Piece.whiteKing))
                }
            }
            Color.BLACK -> {
                if (board.rayMoves(Sq.h8, Direction.W, Color.BLACK) == 0x6000000000000000UL) {
                    ctx.addMove(PseudoMove(Square.e8, Square.g8, Piece.blackKing))
                }
                if (board.rayMoves(Sq.a8, Direction.E, Color.BLACK) == 0xE00000000000000UL) {
                    ctx.addMove(PseudoMove(Square.e8, Square.c8, Piece.blackKing))
                }
            }
        }
    }

}