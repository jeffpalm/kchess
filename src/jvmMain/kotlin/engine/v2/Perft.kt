package engine.v2

import engine.Game
import engine.v2.moves.MoveGenCtx
import engine.v2.moves.MoveGenerator

object Perft {
    fun run(depth: Int, game: Game): Int {
        val moves = MoveGenerator(MoveGenCtx(game.data.copy())).execute()

        if (depth == 1) return moves.size

        var nodes = 0


        for (move in moves) {
            val validMove = game.makeMove(move)
//            println("${move.first.name}${if(validMove.capture != null) "x" else ""}${move.second.name}")
            nodes += run(depth - 1, game)
            game.undoMove()
        }
        return nodes
    }
}