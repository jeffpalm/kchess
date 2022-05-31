package engine

import engine.move.MoveGenCtx
import engine.move.MoveGenerator

object Perft {
    fun run(depth: Int, game: Game): Int {
        val moves = MoveGenerator(MoveGenCtx(game.data)).execute()

//        if (depth == 1) {
//            for (move in moves) {
//                println("      ${move.from.name}${move.to.name}: 1")
//            }
//        }

        if (depth == 1) return moves.size

        var nodes = 0


        for (move in moves) {
            game.makeMove(move)
//            val curNodeVal = nodes
            nodes += run(depth - 1, game.clone())
//            println("${move.from.name}${move.to.name}: ${nodes - curNodeVal}")
            game.undoMove()
        }
//        println(nodes)
        return nodes
    }
}