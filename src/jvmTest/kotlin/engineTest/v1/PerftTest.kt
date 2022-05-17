package engineTest.v1

import engine.v1.Game
import engine.v1.Perft
import kotlin.test.Test
import kotlin.test.assertEquals

class PerftTest {
    @Test
    fun testDepth1() {
        assertEquals(20, Perft.run(1, Game()))
    }

    @Test
    fun testDepth2() {
        assertEquals(400, Perft.run(2, Game()))
    }

    @Test
    fun testDepth3() {
        assertEquals(8902, Perft.run(3, Game()))
    }

    @Test
    fun testDepth4() {
        assertEquals(197281, Perft.run(4, Game()))
    }

    @Test
    fun testDepth5() {
        assertEquals(4865609, Perft.run(5, Game()))
    }
//
//    @Test
//    fun testDepth6() {
//        assertEquals(119060324, Perft.run(6, Game()))
//    }

    @Test
    fun testPos4Depth1() {
        assertEquals(6, Perft.run(1, Game("r3k2r/Pppp1ppp/1b3nbN/nP6/BBP1P3/q4N2/Pp1P2PP/R2Q1RK1 w kq - 0 1")))
    }

    @Test
    fun testPos4Depth2() {
        assertEquals(264, Perft.run(2, Game("r3k2r/Pppp1ppp/1b3nbN/nP6/BBP1P3/q4N2/Pp1P2PP/R2Q1RK1 w kq - 0 1")))
    }

    @Test
    fun testPos4Depth3() {
        assertEquals(9467, Perft.run(3, Game("r3k2r/Pppp1ppp/1b3nbN/nP6/BBP1P3/q4N2/Pp1P2PP/R2Q1RK1 w kq - 0 1")))
    }

    @Test
    fun testAltPositionDepth1() {
        assertEquals(48, Perft.run(1, Game("r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1")))
    }

    @Test
    fun testEndGameDepth1() {
        assertEquals(14, Perft.run(1, Game("8/2p5/3p4/KP5r/1R3p1k/8/4P1P1/8 w - - 0 1")))
    }
}