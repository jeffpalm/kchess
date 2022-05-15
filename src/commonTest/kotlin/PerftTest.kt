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
}