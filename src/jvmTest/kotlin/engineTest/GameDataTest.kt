package engineTest

import engine.BitBoard
import engine.Color
import engine.GameData
import engine.IGameData
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class GameDataTest {

    @Test
    fun `game data destructors`() {
        val data: IGameData = GameData(BitBoard(), Color.WHITE)
        val (board, turn, castlingAvail, ePTarget, halfMoveClock, fullMoveClock) = data

        assertEquals(data.board, board, "board test")
        assertEquals(Color.WHITE, turn, "turn test")
        assertEquals("KQkq", castlingAvail, "castlingAvail test")
        assertEquals("-", ePTarget, "ePTarget test")
        assertEquals(0, halfMoveClock, "halfMoveClock test")
        assertEquals(1, fullMoveClock, "fullMoveClock test")
    }
}