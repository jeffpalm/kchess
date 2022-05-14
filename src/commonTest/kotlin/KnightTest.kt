import kotlin.test.Test
import kotlin.test.assertTrue

class KnightTest {
    private val knight = Knight(PieceColor.WHITE)

    @Test
    fun testKnightMovement() {
        assertTrue(knight.canMove(PotentialMove(Coords(4, 4), Coords(2, 3))))
        assertTrue(knight.canMove(PotentialMove(Coords(4, 4), Coords(2, 5))))
        assertTrue(knight.canMove(PotentialMove(Coords(4, 4), Coords(3, 2))))
        assertTrue(knight.canMove(PotentialMove(Coords(4, 4), Coords(3, 6))))
        assertTrue(knight.canMove(PotentialMove(Coords(4, 4), Coords(6, 3))))
        assertTrue(knight.canMove(PotentialMove(Coords(4, 4), Coords(6, 5))))
        assertTrue(knight.canMove(PotentialMove(Coords(4, 4), Coords(5, 2))))
        assertTrue(knight.canMove(PotentialMove(Coords(4, 4), Coords(5, 6))))
        assertTrue(!knight.canMove(PotentialMove(Coords(4, 4), Coords(5, 5))))
    }

    @Test
    fun testKnightCapture() {
        assertTrue(knight.canCapture(PotentialMove(Coords(4, 4), Coords(5, 6))))
        assertTrue(!knight.canCapture(PotentialMove(Coords(4, 4), Coords(5, 5))))
    }
}