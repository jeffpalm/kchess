package engineTest.v1

import engine.v1.Coords
import kotlin.test.Test
import kotlin.test.assertFailsWith

class SquareCoordinatesTest {
    @Test
    fun testOnlyAcceptsInts0to7() {
        assertFailsWith<IllegalArgumentException> {
            Coords(8, 8)
        }
    }
}