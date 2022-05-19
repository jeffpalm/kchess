package engineTest.v2

import engine.Game
import engine.v2.Perft
import kotlin.test.Test
import kotlin.test.assertEquals

class PerftTest {
    @Test
    fun testPerftDepth1() {
        val game = Game()
        assertEquals(20, Perft.run(1, game))
    }
    @Test
    fun testPerftDepth2() {
        val game = Game()
        assertEquals(400, Perft.run(2, game))
    }
}
