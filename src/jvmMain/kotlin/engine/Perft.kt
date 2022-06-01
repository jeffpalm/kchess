package engine

import engine.move.MoveGenCtx
import engine.move.MoveGenerator

object Perft {
    fun run(depth: Int, game: Game, startDepth: Int = depth): Int {
        val moves = MoveGenerator(MoveGenCtx(game.data)).execute()

        if (depth == 1 && startDepth == 1) {
            for (move in moves) {
                println("${move.from.name}${move.to.name}: 1")
            }
            println(moves.size)
        }

        if (depth == 1) return moves.size

        var nodes = 0


        for (move in moves) {
            val cloned = game.clone()
            cloned.makeMove(move)
            val curNodeVal = nodes
            nodes += run(depth - 1, cloned, startDepth)
            if (depth == startDepth) {
                println("${move.from.name}${move.to.name}: ${nodes - curNodeVal}")
            }
//            game.undoMove()
        }
        if (depth == startDepth) {
            println(nodes)
        }
        return nodes
    }
}