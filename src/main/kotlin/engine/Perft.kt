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
        val pool = arrayOfNulls<MoveGenCtx>(depth + 1)
        val mateCtx = MoveGenCtx(game.data)
        val acc = Acc()
        walkStats(depth, game, acc, pool, mateCtx)
        return acc.toStats()
    }

    private fun walkStats(
        depth: Int,
        game: Game,
        acc: Acc,
        pool: Array<MoveGenCtx?>,
        mateCtx: MoveGenCtx,
    ) {
        var ctx = pool[depth]
        if (ctx == null) {
            ctx = MoveGenCtx(game.data)
            pool[depth] = ctx
        } else {
            ctx.reset(game.data)
        }
        MoveGenerator.executeOn(ctx)
        val n = ctx.movesSize
        if (depth == 1) {
            var i = 0
            while (i < n) {
                val played = game.makeMove(ctx.moveAt(i))
                classifyInto(played, game, acc, mateCtx)
                game.undoMove()
                i++
            }
            return
        }
        var i = 0
        while (i < n) {
            game.makeMove(ctx.moveAt(i))
            walkStats(depth - 1, game, acc, pool, mateCtx)
            game.undoMove()
            i++
        }
    }

    private fun classifyInto(played: Move, gameAfter: Game, acc: Acc, mateCtx: MoveGenCtx) {
        val mover = gameAfter.data.turn.inv()
        acc.nodes++
        if (played.capture != null) acc.captures++
        if ((played.piece == Piece.wPawn || played.piece == Piece.bPawn) && played.to == played.prevEnPassantTarget) {
            acc.enPassant++
        }
        if ((played.piece == Piece.wKing || played.piece == Piece.bKing) && abs(played.from.ordinal - played.to.ordinal) == 2) {
            acc.castles++
        }
        if (played.promo != null) acc.promotions++

        val enemyKingBit = gameAfter.data.board.king(mover.inv())
        val attackers = gameAfter.data.board.attackersOf(enemyKingBit, mover)
        val numAttackers = attackers.countOneBits()
        if (numAttackers == 0) return
        acc.checks++
        if (numAttackers >= 2) {
            acc.doubleChecks++
        } else if ((attackers and played.to.asBit()) == 0UL) {
            acc.discoveryChecks++
        }
        mateCtx.reset(gameAfter.data)
        MoveGenerator.executeOn(mateCtx)
        if (mateCtx.movesSize == 0) {
            acc.checkmates++
        }
    }

    private class Acc {
        var nodes = 0L
        var captures = 0L
        var enPassant = 0L
        var castles = 0L
        var promotions = 0L
        var checks = 0L
        var discoveryChecks = 0L
        var doubleChecks = 0L
        var checkmates = 0L

        fun toStats() = PerftStats(
            nodes, captures, enPassant, castles, promotions,
            checks, discoveryChecks, doubleChecks, checkmates,
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
