package engineTest.v1

import engine.v1.Coords
import engine.v1.Knight
import engine.v1.Movement
import engine.v1.PieceColor
import kotlin.test.Test
import kotlin.test.assertTrue

class KnightTest {
    private val knight = Knight(PieceColor.WHITE)

    @Test
    fun testKnightMovement() {
        assertTrue(knight.canMove(Movement(Coords(4, 4), Coords(2, 3))))
        assertTrue(knight.canMove(Movement(Coords(4, 4), Coords(2, 5))))
        assertTrue(knight.canMove(Movement(Coords(4, 4), Coords(3, 2))))
        assertTrue(knight.canMove(Movement(Coords(4, 4), Coords(3, 6))))
        assertTrue(knight.canMove(Movement(Coords(4, 4), Coords(6, 3))))
        assertTrue(knight.canMove(Movement(Coords(4, 4), Coords(6, 5))))
        assertTrue(knight.canMove(Movement(Coords(4, 4), Coords(5, 2))))
        assertTrue(knight.canMove(Movement(Coords(4, 4), Coords(5, 6))))
        assertTrue(!knight.canMove(Movement(Coords(4, 4), Coords(5, 5))))
    }

    @Test
    fun testKnightCapture() {
        assertTrue(knight.canCapture(Movement(Coords(4, 4), Coords(5, 6))))
        assertTrue(!knight.canCapture(Movement(Coords(4, 4), Coords(5, 5))))
    }
}