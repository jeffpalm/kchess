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
        val enemyAttacks = board.allAttackTargets(turn.inv())
        when (turn) {
            Color.WHITE -> {
                val kingSidePath = board.rayMoves(Sq.h1, Direction.W, Color.WHITE)
                val queenSidePath = board.rayMoves(Sq.b1, Direction.E, Color.WHITE)
                if (kingSidePath == 0x60UL && kingSidePath.and(enemyAttacks) == 0UL) {
                    ctx.addMove(PseudoMove(Square.e1, Square.g1, Piece.wKing))
                }
                if (queenSidePath == 0xCUL && queenSidePath.and(enemyAttacks) == 0UL) {
                    ctx.addMove(PseudoMove(Square.e1, Square.c1, Piece.wKing))
                }
            }
            Color.BLACK -> {
                val kingSidePath = board.rayMoves(Sq.h8, Direction.W, Color.BLACK)
                val queenSidePath = board.rayMoves(Sq.b8, Direction.E, Color.BLACK)
                if (kingSidePath == 0x6000000000000000UL && kingSidePath.and(enemyAttacks) == 0UL) {
                    ctx.addMove(PseudoMove(Square.e8, Square.g8, Piece.bKing))
                }
                if (queenSidePath == 0xC00000000000000UL && queenSidePath.and(enemyAttacks) == 0UL) {
                    ctx.addMove(PseudoMove(Square.e8, Square.c8, Piece.bKing))
                }
            }
        }
    }

}