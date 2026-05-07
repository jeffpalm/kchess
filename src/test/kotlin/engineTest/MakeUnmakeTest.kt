package engineTest

import engine.Fen
import engine.Game
import engine.adapter.GameToFen
import engine.move.MoveGenCtx
import engine.move.MoveGenerator
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Round-trip: every legal move from a position, played and then immediately
 * undone, must restore the FEN of the original position bit-for-bit. Covers
 * captures, en passant, castling (both sides, both colors), promotion,
 * promotion-with-capture, and rook captures (which lose castling rights).
 */
internal class MakeUnmakeTest {
    private fun assertRoundTripsFromAllMoves(fen: String) {
        val original = Game(Fen(fen))
        val originalFen = GameToFen(original).output.string
        val moves = MoveGenerator(MoveGenCtx(original.data)).execute()
        for (move in moves) {
            val game = Game(Fen(fen))
            game.makeMove(move)
            game.undoMove()
            val after = GameToFen(game).output.string
            assertEquals(
                originalFen, after,
                "make/undo of ${move.from.name}${move.to.name}${move.promo ?: ""} " +
                    "did not restore FEN. expected:\n  $originalFen\nactual:\n  $after",
            )
        }
    }

    @Test
    fun `start position`() {
        assertRoundTripsFromAllMoves("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1")
    }

    @Test
    fun `kiwipete - captures, castling rights, lots of options`() {
        assertRoundTripsFromAllMoves("r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1")
    }

    @Test
    fun `position 3 - en passant captures available`() {
        // After white plays b5xc6 e.p. or similar; this position has EP target c6
        assertRoundTripsFromAllMoves("8/8/3p4/1Pp4r/KR3p1k/8/4P1P1/8 w - c6 0 2")
    }

    @Test
    fun `position 4 - lots of promotions and rook captures`() {
        assertRoundTripsFromAllMoves("r3k2r/Pppp1ppp/1b3nbN/nP6/BBP1P3/q4N2/Pp1P2PP/R2Q1RK1 w kq - 0 1")
    }

    @Test
    fun `position 5 - white pawn one step from promotion, knight giving check`() {
        assertRoundTripsFromAllMoves("rnbq1k1r/pp1Pbppp/2p5/8/2B5/8/PPP1NnPP/RNBQK2R w KQ - 1 8")
    }

    @Test
    fun `position with both castles available black to move`() {
        assertRoundTripsFromAllMoves("r3k2r/pppppppp/8/8/8/8/PPPPPPPP/R3K2R b KQkq - 0 1")
    }
}
