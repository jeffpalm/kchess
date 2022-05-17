package engineTest.v1

import engine.v1.Bishop
import engine.v1.Coords
import engine.v1.Movement
import engine.v1.PieceColor
import kotlin.test.Test
import kotlin.test.assertTrue


class BishopTest {
    private val bishop = Bishop(PieceColor.BLACK)

    @Test
    fun testBishopMovement() {
        assertTrue(bishop.canMove(Movement(Coords(0, 0), Coords(7, 7))))
        assertTrue(bishop.canMove(Movement(Coords(0, 7), Coords(7, 0))))
        assertTrue(bishop.canMove(Movement(Coords(7, 7), Coords(0, 0))))
        assertTrue(bishop.canMove(Movement(Coords(7, 0), Coords(0, 7))))
        assertTrue(!bishop.canMove(Movement(Coords(0, 0), Coords(7, 0))))
        assertTrue(!bishop.canMove(Movement(Coords(0, 0), Coords(0, 7))))
        assertTrue(!bishop.canMove(Movement(Coords(7, 0), Coords(1, 1))))
    }

    @Test
    fun testBishopCapture() {
        assertTrue(bishop.canCapture(Movement(Coords(0, 0), Coords(7, 7))))
        assertTrue(!bishop.canCapture(Movement(Coords(7, 0), Coords(1, 1))))
    }
}