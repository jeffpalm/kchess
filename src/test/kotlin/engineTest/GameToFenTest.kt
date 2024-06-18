package engineTest

import engine.Fen
import engine.Game
import engine.adapter.GameToFen
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class GameToFenTest {

    @Test
    fun adapt() {
        val fenToTest = Fen("r3k2r/Pppp1ppp/1b3nbN/nP6/BBP1P3/q4N2/Pp1P2PP/R2Q1RK1 w kq - 0 1")
        val game = Game(fenToTest)

        val fen = GameToFen(game).output
        assertEquals("r3k2r/Pppp1ppp/1b3nbN/nP6/BBP1P3/q4N2/Pp1P2PP/R2Q1RK1", fen.boardRepresentation)
        assertEquals("w", fen.sideToMove)
        assertEquals("kq", fen.castlingAvailability)
        assertEquals("-", fen.enPassantTarget)
        assertEquals(0, fen.halfMoveClock)
        assertEquals(1, fen.fullMoveClock)
    }
}