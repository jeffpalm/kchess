import kotlin.test.Test
import kotlin.test.assertEquals


class GameTest {
    @Test
    fun testGenerateBasePositionMoves() {
        val game = Game("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1")
        val moves = game.generateMoves()
        assertEquals(20, moves.size)
    }

}