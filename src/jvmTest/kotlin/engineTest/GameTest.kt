package engineTest

import engine.Game
import engine.v2.SquareMap
import engine.v2.moves.PseudoMove
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class GameTest {
    private val game = Game()
    @BeforeAll
    fun beforeAll() {
        game.makeMove(PseudoMove(SquareMap.d2, SquareMap.d4))
    }

    @Test
    fun getMoves() {
    }

    @Test
    fun getGameData() {
    }

    @Test
    fun `makeMove updates bitboard correctly`() {
        val result = game.gameData.board.whitePawns
        assertEquals(0x800f700UL, result, "Expected: 0x800f700UL\nReceived: 0x${result.toString(16)}UL")
    }

    @Test
    fun `makeMove occupied all`() {

    }

    @Test
    fun undoMove() {
    }
}