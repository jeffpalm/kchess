package engineTest.v1

import engine.v1.Coords
import engine.v1.Movement
import engine.v1.PieceColor
import engine.v1.Rook
import kotlin.test.Test
import kotlin.test.assertTrue

class RookTest {
    private val rook = Rook(PieceColor.BLACK)

    @Test
    fun testRookMovement() {
        assertTrue(rook.canMove(Movement(Coords(0, 0), Coords(7, 0))))
        assertTrue(rook.canMove(Movement(Coords(0, 0), Coords(0, 7))))
        assertTrue(rook.canMove(Movement(Coords(7, 7), Coords(0, 7))))
        assertTrue(rook.canMove(Movement(Coords(7, 7), Coords(7, 0))))
        assertTrue(!rook.canMove(Movement(Coords(0, 0), Coords(7, 7))))
        assertTrue(!rook.canMove(Movement(Coords(7, 0), Coords(1, 1))))
    }

    @Test
    fun testRookCapture() {
        assertTrue(rook.canCapture(Movement(Coords(0, 0), Coords(0, 7))))
        assertTrue(!rook.canCapture(Movement(Coords(7, 0), Coords(1, 1))))
    }
}