package engineTest.v1

import engine.v1.Coords
import engine.v1.King
import engine.v1.Movement
import engine.v1.PieceColor
import kotlin.test.Test
import kotlin.test.assertTrue

class KingTest {
    var king = King(PieceColor.WHITE)

    @Test
    fun testKingMovement() {
        assertTrue(king.canMove(Movement(Coords(4, 4), Coords(5, 5))))
        assertTrue(king.canMove(Movement(Coords(4, 4), Coords(5, 4))))
        assertTrue(king.canMove(Movement(Coords(4, 4), Coords(5, 3))))
        assertTrue(king.canMove(Movement(Coords(4, 4), Coords(4, 5))))
        assertTrue(king.canMove(Movement(Coords(4, 4), Coords(4, 3))))
        assertTrue(king.canMove(Movement(Coords(4, 4), Coords(3, 4))))
        assertTrue(king.canMove(Movement(Coords(4, 4), Coords(3, 3))))
        assertTrue(king.canMove(Movement(Coords(4, 4), Coords(3, 5))))
        assertTrue(king.canMove(Movement(Coords(4, 4), Coords(6, 4))))
        assertTrue(!king.canMove(Movement(Coords(4, 4), Coords(4, 2))))
    }

    @Test
    fun testKingCapture() {
        assertTrue(king.canCapture(Movement(Coords(4, 4), Coords(3, 5))))
        assertTrue(!king.canCapture(Movement(Coords(4, 4), Coords(6, 4))))
    }
}