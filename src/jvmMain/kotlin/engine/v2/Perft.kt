package engine.v2

import engine.Game
import engine.v2.moves.MoveGenerator
import engine.v2.moves.MoveGenCtx

object Perft {
    fun run(depth: Int, game: Game): Int {
        val moves = MoveGenerator(MoveGenCtx(game.gameData)).execute().getMoves()

        if (depth == 1) return moves.size

        var nodes = 0


        for (move in moves) {
            game.makeMove(move)
            nodes += MoveGenerator(MoveGenCtx(game.gameData)).execute().getMoves().size
            game.undoMove()
        }
        return nodes
    }
}