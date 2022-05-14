import kotlin.test.Test
import kotlin.test.assertFailsWith

class SquareCoordinatesTest {
    @Test
    fun testOnlyAcceptsBytes0to7() {
        assertFailsWith<IllegalArgumentException> {
            Coords(8, 8)
        }
    }
}