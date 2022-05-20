package engine.v2

import engine.Game
import engine.v2.moves.MoveGenCtx
import engine.v2.moves.MoveGenerator
import engine.v2.moves.PseudoMove

object Perft {
    fun run(depth: Int, game: Game): Int {
        val moves = MoveGenerator(MoveGenCtx(game.gameData.copy())).execute()

        if (depth == 1) return moves.size

        var nodes = 0


        for (move in moves) {
            game.makeMove(PseudoMove(move.first, move.second))
            nodes += run(depth - 1, game)
            game.undoMove()
        }
        return nodes
    }
}