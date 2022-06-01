package engine.move.filters

import engine.Direction
import engine.Piece
import engine.move.IMoveFilter
import engine.move.MoveGenCtx
import engine.move.PseudoMove

// Handles the edge case when an enemy pawn's two move jump
// unpins a pawn from the king but En Passant capture is illegal because
// it would put friendly king in Check
// See Perft Position 2 - Depth 2
class MoveFilterEnPassantCaptureEdgeCase : IMoveFilter {
    override suspend fun run(ctx: MoveGenCtx): MoveGenCtx {
        if (ctx.data.board.enPassantTarget == null) return ctx

        ctx.filterMoves { handleEdgeCase(it, ctx) }

        return ctx
    }

    private fun handleEdgeCase(mv: PseudoMove, ctx: MoveGenCtx): Boolean {
        val (board, turn) = ctx.data
        if (mv.toBit == board.enPassantTarget) {
            return when (mv.piece) {
                Piece.pawn(turn) -> isLegalEPCapture(mv, ctx)
                else -> true
            }
        }
        return true
    }

    private fun isLegalEPCapture(mv: PseudoMove, ctx: MoveGenCtx): Boolean {
        val (board, turn) = ctx.data
        if (mv.piece != Piece.pawn(turn)) throw IllegalStateException("Expected pawn")

        val clonedBoard = board.clone()
        clonedBoard.makeMove(mv.fromBit to mv.toBit, mv.piece, Piece.pawn(turn.inv()))

        for (direction in Direction.sliding) {
            val rayAttack = clonedBoard.rayAttack(board.king(turn), direction, turn)
            val enemyThreats = when (direction) {
                in Direction.bishops -> rayAttack.and(clonedBoard.bishops(turn.inv()).or(clonedBoard.queens(turn.inv())))
                in Direction.rooks -> rayAttack.and(clonedBoard.rooks(turn.inv()).or(clonedBoard.queens(turn.inv())))
                else -> 0UL
            }

            if (enemyThreats != 0UL) return false
        }
        return true
    }

}