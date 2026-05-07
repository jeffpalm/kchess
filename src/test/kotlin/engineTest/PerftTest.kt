package engineTest

import engine.Fen
import engine.Game
import engine.Perft
import engine.PerftStats
import org.junit.jupiter.api.Disabled
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Positions and results from http://www.chessprogramming.org/Perft_Results
 *
 * Start Pos depth/classification table:
 * https://www.chessprogramming.org/Perft_Results#Initial_Position
 */
class PerftTest {
    @Test
    fun `Start Pos - Depth 0`() {
        assertEquals(
            PerftStats(nodes = 1),
            Perft.runStats(0, Game()),
        )
    }
    @Test
    fun `Start Pos - Depth 1`() {
        assertEquals(
            PerftStats(nodes = 20),
            Perft.runStats(1, Game()),
        )
    }
    @Test
    fun `Start Pos - Depth 2`() {
        assertEquals(
            PerftStats(nodes = 400),
            Perft.runStats(2, Game()),
        )
    }
    @Test
    fun `Start Pos - Depth 3`() {
        assertEquals(
            PerftStats(nodes = 8_902, captures = 34, checks = 12),
            Perft.runStats(3, Game()),
        )
    }
    @Test
    fun `Start Pos - Depth 4`() {
        assertEquals(
            PerftStats(nodes = 197_281, captures = 1_576, checks = 469, checkmates = 8),
            Perft.runStats(4, Game()),
        )
    }
    @Test
    fun `Start Pos - Depth 5`() {
        assertEquals(
            PerftStats(
                nodes = 4_865_609,
                captures = 82_719,
                enPassant = 258,
                checks = 27_351,
                discoveryChecks = 6,
                checkmates = 347,
            ),
            Perft.runStats(5, Game()),
        )
    }
    @Test @Disabled("~2 min; flip on for deep validation")
    fun `Start Pos - Depth 6`() {
        assertEquals(
            PerftStats(
                nodes = 119_060_324,
                captures = 2_812_008,
                enPassant = 5_248,
                checks = 809_099,
                discoveryChecks = 329,
                doubleChecks = 46,
                checkmates = 10_828,
            ),
            Perft.runStats(6, Game()),
        )
    }
    @Test @Disabled("~hours; enable manually for deep validation")
    fun `Start Pos - Depth 7`() {
        assertEquals(
            PerftStats(
                nodes = 3_195_901_860,
                captures = 108_329_926,
                enPassant = 319_617,
                castles = 883_453,
                checks = 33_103_848,
                discoveryChecks = 18_026,
                doubleChecks = 1_628,
                checkmates = 435_767,
            ),
            Perft.runStats(7, Game()),
        )
    }
    @Test @Disabled("Computationally infeasible without bulk-counting + multithreading")
    fun `Start Pos - Depth 8`() {
        assertEquals(
            PerftStats(
                nodes = 84_998_978_956,
                captures = 3_523_740_106,
                enPassant = 7_187_977,
                castles = 23_605_205,
                checks = 968_981_593,
                discoveryChecks = 847_039,
                doubleChecks = 147_215,
                checkmates = 9_852_036,
            ),
            Perft.runStats(8, Game()),
        )
    }
    @Test @Disabled("Computationally infeasible without bulk-counting + multithreading")
    fun `Start Pos - Depth 9`() {
        assertEquals(
            PerftStats(
                nodes = 2_439_530_234_167,
                captures = 125_208_536_153,
                enPassant = 319_496_827,
                castles = 1_784_356_000,
                promotions = 17_334_376,
                checks = 36_095_901_903,
                discoveryChecks = 37_101_713,
                doubleChecks = 5_547_231,
                checkmates = 400_191_963,
            ),
            Perft.runStats(9, Game()),
        )
    }
    @Test @Disabled("Computationally infeasible without bulk-counting + multithreading")
    fun `Start Pos - Depth 10`() {
        assertEquals(69_352_859_712_417L, Perft.runStats(10, Game()).nodes)
    }
    @Test
    fun `Position 2 - Depth 1`() {
        val game = Game(Fen("r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1"))
        assertEquals(48, Perft.run(1, game))
    }
    @Test
    fun `Position 2 - Depth 2`() {
        val game = Game(Fen("r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1"))
        assertEquals(2039, Perft.run(2, game))
    }
    @Test
    fun `Position 2 - Depth 3`() {
        val game = Game(Fen("r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1"))
        assertEquals(97862, Perft.run(3, game))
    }
    @Test
    fun `Position 3 - Depth 1`() {
        val game = Game(Fen("8/2p5/3p4/KP5r/1R3p1k/8/4P1P1/8 w - - 0 1"))
        assertEquals(14, Perft.run(1, game))
    }
    @Test
    fun `Position 3 - Depth 2`() {
        val game = Game(Fen("8/2p5/3p4/KP5r/1R3p1k/8/4P1P1/8 w - - 0 1"))
        assertEquals(191, Perft.run(2, game))
    }
    @Test
    fun `Position 3 - Depth 3`() {
        val game = Game(Fen("8/2p5/3p4/KP5r/1R3p1k/8/4P1P1/8 w - - 0 1"))
        assertEquals(2812, Perft.run(3, game))
    }
    @Test
    fun `Position 3 - Depth 4`() {
        val game = Game(Fen("8/2p5/3p4/KP5r/1R3p1k/8/4P1P1/8 w - - 0 1"))
        assertEquals(43238, Perft.run(4, game))
    }
    @Test
    fun `Position 3 - Depth 5`() {
        val game = Game(Fen("8/2p5/3p4/KP5r/1R3p1k/8/4P1P1/8 w - - 0 1"))
        assertEquals(674624, Perft.run(5, game))
    }
    @Test
    fun `Position 4 - Depth 1`() {
        val game = Game(Fen("r3k2r/Pppp1ppp/1b3nbN/nP6/BBP1P3/q4N2/Pp1P2PP/R2Q1RK1 w kq - 0 1"))
        assertEquals(6, Perft.run(1, game))
    }
    @Test
    fun `Position 4 - Depth 2`() {
        val game = Game(Fen("r3k2r/Pppp1ppp/1b3nbN/nP6/BBP1P3/q4N2/Pp1P2PP/R2Q1RK1 w kq - 0 1"))
        assertEquals(264, Perft.run(2, game))
    }
    @Test
    fun `Position 4 - Depth 3`() {
        val game = Game(Fen("r3k2r/Pppp1ppp/1b3nbN/nP6/BBP1P3/q4N2/Pp1P2PP/R2Q1RK1 w kq - 0 1"))
        assertEquals(9467, Perft.run(3, game))
    }
    @Test
    fun `Position 4 - Depth 4`() {
        val game = Game(Fen("r3k2r/Pppp1ppp/1b3nbN/nP6/BBP1P3/q4N2/Pp1P2PP/R2Q1RK1 w kq - 0 1"))
        assertEquals(422333, Perft.run(4, game))
    }
    @Test
    fun `Position 4 mirrored - Depth 1`() {
        val game = Game(Fen("r2q1rk1/pP1p2pp/Q4n2/bbp1p3/Np6/1B3NBn/pPPP1PPP/R3K2R b KQ - 0 1"))
        assertEquals(6, Perft.run(1, game))
    }
    @Test
    fun `Position 4 mirrored - Depth 3`() {
        val game = Game(Fen("r2q1rk1/pP1p2pp/Q4n2/bbp1p3/Np6/1B3NBn/pPPP1PPP/R3K2R b KQ - 0 1"))
        assertEquals(9467, Perft.run(3, game))
    }
    @Test
    fun `Position 5 - Depth 1`() {
        val game = Game(Fen("rnbq1k1r/pp1Pbppp/2p5/8/2B5/8/PPP1NnPP/RNBQK2R w KQ - 1 8  "))
        assertEquals(44, Perft.run(1, game))
    }
    @Test
    fun `Position 5 - Depth 2`() {
        val game = Game(Fen("rnbq1k1r/pp1Pbppp/2p5/8/2B5/8/PPP1NnPP/RNBQK2R w KQ - 1 8  "))
        assertEquals(1486, Perft.run(2, game))
    }
    @Test
    fun `Position 5 - Depth 3`() {
        val game = Game(Fen("rnbq1k1r/pp1Pbppp/2p5/8/2B5/8/PPP1NnPP/RNBQK2R w KQ - 1 8  "))
        assertEquals(62379, Perft.run(3, game))
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
    @Test
    fun `Position 6 - Depth 3`() {
        val game = Game(Fen("r4rk1/1pp1qppp/p1np1n2/2b1p1B1/2B1P1b1/P1NP1N2/1PP1QPPP/R4RK1 w - - 0 10"))
        assertEquals(89890, Perft.run(3, game))
    }
}
