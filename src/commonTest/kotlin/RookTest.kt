import kotlin.test.Test
import kotlin.test.assertTrue

class RookTest {
    private val rook = Rook(PieceColor.BLACK)

    @Test
    fun testRookMovement() {
        assertTrue(rook.canMove(PotentialMove(Coords(0, 0), Coords(7, 0))))
        assertTrue(rook.canMove(PotentialMove(Coords(0, 0), Coords(0, 7))))
        assertTrue(rook.canMove(PotentialMove(Coords(7, 7), Coords(0, 7))))
        assertTrue(rook.canMove(PotentialMove(Coords(7, 7), Coords(7, 0))))
        assertTrue(!rook.canMove(PotentialMove(Coords(0, 0), Coords(7, 7))))
        assertTrue(!rook.canMove(PotentialMove(Coords(7, 0), Coords(1, 1))))
    }

    @Test
    fun testRookCapture() {
        assertTrue(rook.canCapture(PotentialMove(Coords(0, 0), Coords(0, 7))))
        assertTrue(!rook.canCapture(PotentialMove(Coords(7, 0), Coords(1, 1))))
    }
}