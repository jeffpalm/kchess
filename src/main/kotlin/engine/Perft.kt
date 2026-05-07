package engine

import engine.move.MoveGenCtx
import engine.move.MoveGenerator
import kotlin.math.abs

object Perft {
    /**
     * Walks the game tree to [depth] and returns aggregated leaf
     * classifications. Quiet (no stdout output).
     */
    fun runStats(depth: Int, game: Game): PerftStats {
        if (depth == 0) return PerftStats(nodes = 1)
        val moves = MoveGenerator(MoveGenCtx(game.data)).execute()
        var s = PerftStats.ZERO
        if (depth == 1) {
            for (move in moves) {
                val played = game.makeMove(move)
                s += classify(played, game)
                game.undoMove()
            }
            return s
        }
        for (move in moves) {
            game.makeMove(move)
            s += runStats(depth - 1, game)
            game.undoMove()
        }
        return s
    }

    private fun classify(played: Move, gameAfter: Game): PerftStats {
        val mover = gameAfter.data.turn.inv()
        val isCapture = played.capture != null
        val isEnPassant = (played.piece == Piece.wPawn || played.piece == Piece.bPawn)
            && played.to == played.prevEnPassantTarget
        val isCastle = (played.piece == Piece.wKing || played.piece == Piece.bKing)
            && abs(played.from.ordinal - played.to.ordinal) == 2
        val isPromotion = played.promo != null

        val enemyKingBit = gameAfter.data.board.king(mover.inv())
        val attackers = gameAfter.data.board.attackersOf(enemyKingBit, mover)
        val numAttackers = attackers.countOneBits()
        val isCheck = numAttackers > 0
        val isDoubleCheck = numAttackers >= 2
        val isDiscoveryCheck = numAttackers == 1 && (attackers and played.to.asBit()) == 0UL
        val isCheckmate = isCheck && MoveGenerator(MoveGenCtx(gameAfter.data)).execute().isEmpty()

        return PerftStats(
            nodes = 1,
            captures = if (isCapture) 1L else 0L,
            enPassant = if (isEnPassant) 1L else 0L,
            castles = if (isCastle) 1L else 0L,
            promotions = if (isPromotion) 1L else 0L,
            checks = if (isCheck) 1L else 0L,
            discoveryChecks = if (isDiscoveryCheck) 1L else 0L,
            doubleChecks = if (isDoubleCheck) 1L else 0L,
            checkmates = if (isCheckmate) 1L else 0L,
        )
    }

    fun run(depth: Int, game: Game, startDepth: Int = depth): Int {
        val moves = MoveGenerator(MoveGenCtx(game.data)).execute()

        if (depth == 1 && startDepth == 1) {
            for (move in moves) {
                println("${move.from.name}${move.to.name}${move.promo ?: ""}: 1")
            }
            println(moves.size)
        }

        if (depth == 1) return moves.size

        var nodes = 0


        for (move in moves) {
            game.makeMove(move)
            val curNodeVal = nodes
            nodes += run(depth - 1, game, startDepth)
            game.undoMove()
            if (depth == startDepth) {
                println("${move.from.name}${move.to.name}${move.promo ?: ""}: ${nodes - curNodeVal}")
            }
        }
        if (depth == startDepth) {
            println(nodes)
        }
        return nodes
    }
}