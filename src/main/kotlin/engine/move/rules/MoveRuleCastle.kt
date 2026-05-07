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

    override fun run(ctx: MoveGenCtx) {
        val (board, turn, castlingAvail) = ctx.data
        val enemyAttacks = board.allAttackTargets(turn.inv())
        val kingSquare = board.king(turn)
        if (kingSquare.and(enemyAttacks) != 0UL) return
        val occupied = board.occupied()
        when (turn) {
            Color.WHITE -> {
                if (castlingAvail.contains('K')) {
                    val pathSquares = 0x60UL
                    val kingPath = 0x60UL
                    if (pathSquares.and(occupied) == 0UL && kingPath.and(enemyAttacks) == 0UL) {
                        ctx.addMove(PseudoMove(Square.e1, Square.g1, Piece.wKing))
                    }
                }
                if (castlingAvail.contains('Q')) {
                    val pathSquares = 0xEUL
                    val kingPath = 0xCUL
                    if (pathSquares.and(occupied) == 0UL && kingPath.and(enemyAttacks) == 0UL) {
                        ctx.addMove(PseudoMove(Square.e1, Square.c1, Piece.wKing))
                    }
                }
            }
            Color.BLACK -> {
                if (castlingAvail.contains('k')) {
                    val pathSquares = 0x6000000000000000UL
                    val kingPath = 0x6000000000000000UL
                    if (pathSquares.and(occupied) == 0UL && kingPath.and(enemyAttacks) == 0UL) {
                        ctx.addMove(PseudoMove(Square.e8, Square.g8, Piece.bKing))
                    }
                }
                if (castlingAvail.contains('q')) {
                    val pathSquares = 0xE00000000000000UL
                    val kingPath = 0xC00000000000000UL
                    if (pathSquares.and(occupied) == 0UL && kingPath.and(enemyAttacks) == 0UL) {
                        ctx.addMove(PseudoMove(Square.e8, Square.c8, Piece.bKing))
                    }
                }
            }
        }
    }

}