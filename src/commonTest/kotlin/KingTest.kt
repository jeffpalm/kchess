import kotlin.test.Test
import kotlin.test.assertTrue

class KingTest {
    var king = King(PieceColor.WHITE)

    @Test
    fun testKingMovement() {
        assertTrue(king.canMove(PotentialMove(Coords(4, 4), Coords(5, 5))))
        assertTrue(king.canMove(PotentialMove(Coords(4, 4), Coords(5, 4))))
        assertTrue(king.canMove(PotentialMove(Coords(4, 4), Coords(5, 3))))
        assertTrue(king.canMove(PotentialMove(Coords(4, 4), Coords(4, 5))))
        assertTrue(king.canMove(PotentialMove(Coords(4, 4), Coords(4, 3))))
        assertTrue(king.canMove(PotentialMove(Coords(4, 4), Coords(3, 4))))
        assertTrue(king.canMove(PotentialMove(Coords(4, 4), Coords(3, 3))))
        assertTrue(king.canMove(PotentialMove(Coords(4, 4), Coords(3, 5))))
        assertTrue(!king.canMove(PotentialMove(Coords(4, 4), Coords(6, 4))))
        assertTrue(!king.canMove(PotentialMove(Coords(4, 4), Coords(4, 2))))
    }

    @Test
    fun testKingCapture() {
        assertTrue(king.canCapture(PotentialMove(Coords(4, 4), Coords(3, 5))))
        assertTrue(!king.canCapture(PotentialMove(Coords(4, 4), Coords(6, 4))))
    }
}