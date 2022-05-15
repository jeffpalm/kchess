object Perft {
    fun run(depth: Int, game: Game): Int {
        val moves = game.generateMoves()
        var nodes = 0

        if (depth == 1) return moves.size

        for (move in moves) {
            game.makeMove(move)
            nodes += run(depth - 1, game)
            game.undoMove()
        }

        return nodes
    }
}