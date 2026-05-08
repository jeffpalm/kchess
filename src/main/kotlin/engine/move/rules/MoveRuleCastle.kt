package engine.move.rules

import engine.CastleAvail
import engine.Color
import engine.Piece
import engine.Square
import engine.move.IMoveRule
import engine.move.MoveGenCtx
import engine.move.PseudoMove

object MoveRuleCastle : IMoveRule {
    override fun shouldRun(ctx: MoveGenCtx): Boolean {
        val (_, turn, castlingAvail) = ctx.data
        val mask = when (turn) {
            Color.WHITE -> CastleAvail.K or CastleAvail.Q
            Color.BLACK -> CastleAvail.BK or CastleAvail.BQ
        }
        return castlingAvail and mask != 0
    }

    override fun run(ctx: MoveGenCtx) {
        val (board, turn, castlingAvail) = ctx.data
        val enemyAttacks = ctx.enemyAttacks()
        val kingSquare = board.king(turn)
        if (kingSquare.and(enemyAttacks) != 0UL) return
        val occupied = board.occupied()
        when (turn) {
            Color.WHITE -> {
                if (castlingAvail and CastleAvail.K != 0) {
                    val path = 0x60UL
                    if (path and occupied == 0UL && path and enemyAttacks == 0UL) {
                        ctx.addMove(PseudoMove(Square.e1, Square.g1, Piece.wKing))
                    }
                }
                if (castlingAvail and CastleAvail.Q != 0) {
                    val emptySquares = 0xEUL
                    val kingPath = 0xCUL
                    if (emptySquares and occupied == 0UL && kingPath and enemyAttacks == 0UL) {
                        ctx.addMove(PseudoMove(Square.e1, Square.c1, Piece.wKing))
                    }
                }
            }
            Color.BLACK -> {
                if (castlingAvail and CastleAvail.BK != 0) {
                    val path = 0x6000000000000000UL
                    if (path and occupied == 0UL && path and enemyAttacks == 0UL) {
                        ctx.addMove(PseudoMove(Square.e8, Square.g8, Piece.bKing))
                    }
                }
                if (castlingAvail and CastleAvail.BQ != 0) {
                    val emptySquares = 0xE00000000000000UL
                    val kingPath = 0xC00000000000000UL
                    if (emptySquares and occupied == 0UL && kingPath and enemyAttacks == 0UL) {
                        ctx.addMove(PseudoMove(Square.e8, Square.c8, Piece.bKing))
                    }
                }
            }
        }
    }
}
