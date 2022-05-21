package engineTest.v2

import engine.Fen
import engine.Game
import engine.v2.moves.MoveGenCtx
import engine.v2.moves.MoveGenerator
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class MoveFilterKingInActiveCheckTest {

    @Test
    fun shouldRun() {

    }

    @Test
    fun `king in active check`() {
        val game = Game(Fen("rnbqkbnr/ppp1pppp/8/1B1p4/4P3/8/PPPP1PPP/RNBQK1NR b KQkq - 1 2"))
        val moves = MoveGenerator(MoveGenCtx(game.data.copy())).execute()
        assertEquals(5, moves.size)
    }
}