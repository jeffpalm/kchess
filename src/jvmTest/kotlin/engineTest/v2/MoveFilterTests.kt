package engineTest.v2

import engine.Fen
import engine.Game
import engine.v2.moves.MoveGenCtx
import engine.v2.moves.MoveGenerator
import org.junit.jupiter.api.Test

class MoveFilterTests {
    @Test
    fun `absolute pinned white knight`() {
        val game = Game(Fen("rnb1kbnr/pp1ppppp/8/q1p5/3P4/2N5/PPP1PPPP/R1BQKBNR w KQkq - 2 3"))
        val moves = MoveGenerator(MoveGenCtx(game.data)).execute()
    }
}