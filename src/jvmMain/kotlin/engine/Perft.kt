package engine

import engine.move.MoveGenCtx
import engine.move.MoveGenerator

object Perft {
    fun run(depth: Int, game: Game): Int {
        val moves = MoveGenerator(MoveGenCtx(game.data.clone())).execute()

//        if (depth == 1) {
//            for (move in moves) {
//                println("      ${move.from.name}${move.to.name} - ${move.piece}")
//            }
//        }

        if (depth == 1) return moves.size

        var nodes = 0


        for (move in moves) {
            val validMove = game.makeMove(move)
//            println("$depth - ${move.from.name}${if(validMove.capture != null) "x" else ""}${move.to.name} - ${move.piece}")
            nodes += run(depth - 1, game.clone())
            game.undoMove()
        }
        return nodes
    }
}