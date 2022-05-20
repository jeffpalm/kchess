package engineTest.v2

import engine.Game
import engine.v2.Perft
import kotlin.test.Test
import kotlin.test.assertEquals

class PerftTest {
    @Test
    fun `Start Pos - Depth 1`() {
        val game = Game()
        assertEquals(20, Perft.run(1, game))
    }
    @Test
    fun `Start Pos - Depth 2`() {
        val game = Game()
        assertEquals(400, Perft.run(2, game))
    }
    @Test
    fun `Start Pos - Depth 3`() {
        val game = Game()
        assertEquals(8902, Perft.run(3, game))
    }
//    @Test
//    fun `Position 2 - Depth 1`() {
//        val game = Game(Fen("r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1"))
//        assertEquals(48, Perft.run(1, game))
//    }
}
