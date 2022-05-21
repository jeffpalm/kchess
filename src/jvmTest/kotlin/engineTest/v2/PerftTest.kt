package engineTest.v2

import engine.Fen
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
    @Test
    fun `Position 2 - Depth 1`() {
        val game = Game(Fen("r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1"))
        assertEquals(48, Perft.run(1, game))
    }
    @Test
    fun `Position 3 - Depth 1`() {
        val game = Game(Fen("8/2p5/3p4/KP5r/1R3p1k/8/4P1P1/8 w - - 0 1"))
        assertEquals(14, Perft.run(1, game))
    }
    @Test
    fun `Position 4 - Depth 1`() {
        val game = Game(Fen("r3k2r/Pppp1ppp/1b3nbN/nP6/BBP1P3/q4N2/Pp1P2PP/R2Q1RK1 w kq - 0 1"))
        assertEquals(6, Perft.run(1, game))
    }
    @Test
    fun `Position 5 - Depth 1`() {
        val game = Game(Fen("rnbq1k1r/pp1Pbppp/2p5/8/2B5/8/PPP1NnPP/RNBQK2R w KQ - 1 8  "))
        assertEquals(44, Perft.run(1, game))
    }
    @Test
    fun `Position 6 - Depth 1`() {
        val game = Game(Fen("r4rk1/1pp1qppp/p1np1n2/2b1p1B1/2B1P1b1/P1NP1N2/1PP1QPPP/R4RK1 w - - 0 10"))
        assertEquals(46, Perft.run(1, game))
    }
    @Test
    fun `Position 6 - Depth 2`() {
        val game = Game(Fen("r4rk1/1pp1qppp/p1np1n2/2b1p1B1/2B1P1b1/P1NP1N2/1PP1QPPP/R4RK1 w - - 0 10"))
        assertEquals(2079, Perft.run(2, game))
    }
}
