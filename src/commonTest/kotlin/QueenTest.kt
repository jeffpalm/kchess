import kotlin.test.Test
import kotlin.test.assertTrue

class QueenTest {
    private val queen = Queen(PieceColor.WHITE)

    @Test
    fun testQueenMovement() {
        assertTrue(queen.canMove(Movement(Coords(0, 0), Coords(7, 7))))
        assertTrue(queen.canMove(Movement(Coords(0, 0), Coords(7, 0))))
        assertTrue(queen.canMove(Movement(Coords(0, 0), Coords(0, 7))))
        assertTrue(queen.canMove(Movement(Coords(0, 7), Coords(7, 0))))
        assertTrue(queen.canMove(Movement(Coords(7, 7), Coords(0, 0))))
        assertTrue(queen.canMove(Movement(Coords(7, 7), Coords(0, 7))))
        assertTrue(queen.canMove(Movement(Coords(7, 7), Coords(7, 0))))
        assertTrue(queen.canMove(Movement(Coords(7, 0), Coords(0, 7))))
        assertTrue(!queen.canMove(Movement(Coords(7, 0), Coords(1, 1))))
    }

    @Test
    fun testQueenCapture() {
        assertTrue(queen.canCapture(Movement(Coords(0, 0), Coords(7, 7))))
        assertTrue(!queen.canCapture(Movement(Coords(7, 0), Coords(1, 1))))
    }
}