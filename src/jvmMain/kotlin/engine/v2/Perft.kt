package engine.v2

import engine.Game
import engine.v2.moves.MoveGenCtx
import engine.v2.moves.MoveGenerator

object Perft {
    fun run(depth: Int, game: Game): Int {
        val moves = MoveGenerator(MoveGenCtx(game.gameData)).execute()

        if (depth == 1) return moves.size

        var nodes = 0


        for (move in moves) {
            game.makeMove(move)
            nodes += MoveGenerator(MoveGenCtx(game.gameData)).execute().size
            game.undoMove()
        }
        return nodes
    }
}