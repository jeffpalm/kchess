package engineTest.v2

import engine.Fen
import engine.Game
import engine.v2.SquareMap
import engine.v2.moves.PseudoMove
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class GameTest {
    private val defaultGame = Game()

    @BeforeAll
    fun setup() {
        defaultGame.makeMove(PseudoMove(SquareMap.d2, SquareMap.d4))
    }

    @Test
    fun getMoves() {
    }

    @Test
    fun getGameData() {
    }

    @Test
    fun `makeMove updates bitboard correctly - pawn push`() {
        val result = defaultGame.data.board.whitePawns
        assertEquals(0x800f700UL, result, "Expected: 0x800f700UL\nReceived: 0x${result.toString(16)}UL")
    }

    @Test
    fun `makeMove updates bitboard correctly - capture`() {
        val game = Game(Fen("rnb1kbnr/pp1ppppp/8/q1p5/3P4/2N5/PPP1PPPP/R1BQKBNR w KQkq - 2 3"))
        game.makeMove(PseudoMove(SquareMap.d4, SquareMap.c5))
        val result = game.data.board.blackPawns
        assertEquals(0xfb000000000000UL, result, "Expected: 0xf7000000000000UL\nReceived: 0x${result.toString(16)}UL")
    }

    @Test
    fun `makeMove updates enPassantTarget correctly`() {
        assertEquals("d3", defaultGame.data.enPassantTarget)
    }

    @Test
    fun `increments game clock correctly`() {
        assertEquals(1, defaultGame.data.halfMoveClock)
        assertEquals(1, defaultGame.data.fullMoveClock)
    }

    @Test
    fun `makeMove occupied all`() {

    }

    @Test
    fun undoMove() {
    }
}