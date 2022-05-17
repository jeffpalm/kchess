package engineTest.v1

import engine.v1.Coords
import engine.v1.Movement
import engine.v1.Pawn
import engine.v1.PieceColor
import kotlin.test.Test
import kotlin.test.assertTrue

class PawnTest {
    private val whitePawn = Pawn(PieceColor.WHITE)
    private val blackPawn = Pawn(PieceColor.BLACK)

    @Test
    fun testWhitePawnMovement() {
        assertTrue(
            whitePawn.canMove(Movement(Coords(4, 6), Coords(4, 5))),
            "can move 1 square forward in correct direction"
        )
        assertTrue(
            whitePawn.canMove(Movement(Coords(4, 6), Coords(4, 4))),
            "can move 2 squares if on starting rank"
        )
        assertTrue(
            !whitePawn.canMove(Movement(Coords(4, 6), Coords(3, 4))),
            "cannot move 2 squares forward 1 square over"
        )
        assertTrue(
            !whitePawn.canMove(Movement(Coords(4, 6), Coords(3, 6))),
            "cannot move horizontal"
        )
        assertTrue(
            !whitePawn.canMove(Movement(Coords(4, 6), Coords(4, 3))),
            "cannot move more than 2 squares"
        )
        assertTrue(
            !whitePawn.canMove(Movement(Coords(4, 6), Coords(4, 7))),
            "cannot move in wrong direction"
        )
        assertTrue(
            !whitePawn.canMove(Movement(Coords(4, 5), Coords(4, 3))),
            "cannot move 2 squares if not on starting rank"
        )
        assertTrue(
            whitePawn.canMove(Movement(Coords(4, 6), Coords(3, 5))),
            "can potentially move diagonally"
        )
        assertTrue(
            !whitePawn.canMove(Movement(Coords(4, 6), Coords(3, 7))),
            "cannot move diagonally in wrong direction"
        )
    }

    @Test
    fun testWhitePawnCapture() {
        assertTrue(
            whitePawn.canCapture(Movement(Coords(4, 6), Coords(3, 5))),
            "can capture diagonally"
        )
        assertTrue(
            !whitePawn.canCapture(Movement(Coords(4, 6), Coords(4, 5))),
            "can ONLY capture diagonally"
        )
        assertTrue(
            !whitePawn.canCapture(Movement(Coords(4, 6), Coords(4, 4))),
            "can ONLY capture diagonally"
        )
        assertTrue(
            !whitePawn.canCapture(Movement(Coords(4, 6), Coords(3, 7))),
            "cannot capture diagonally in wrong direction"
        )
    }

    @Test
    fun testBlackPawnMovement() {
        assertTrue(
            blackPawn.canMove(Movement(Coords(4, 1), Coords(4, 2))),
            "can move 1 square forward in correct direction"
        )
        assertTrue(
            blackPawn.canMove(Movement(Coords(4, 1), Coords(4, 3))),
            "can move 2 squares if on starting rank"
        )
        assertTrue(
            !blackPawn.canMove(Movement(Coords(4, 1), Coords(3, 3))),
            "cannot move 2 squares forward 1 square over"
        )
        assertTrue(
            !blackPawn.canMove(Movement(Coords(4, 1), Coords(4, 4))),
            "cannot move more than 2 squares"
        )
        assertTrue(
            !blackPawn.canMove(Movement(Coords(4, 1), Coords(4, 0))),
            "cannot move in wrong direction"
        )
        assertTrue(
            !blackPawn.canMove(Movement(Coords(4, 2), Coords(4, 4))),
            "cannot move 2 squares if not on starting rank"
        )
        assertTrue(
            blackPawn.canMove(Movement(Coords(4, 1), Coords(5, 2))),
            "can potentially move diagonally"
        )
        assertTrue(
            !blackPawn.canMove(Movement(Coords(4, 1), Coords(5, 0))),
            "cannot move diagonally in wrong direction"
        )
        assertTrue(
            !blackPawn.canMove(Movement(Coords(4, 1), Coords(3, 1))),
            "cannot move horizontal"
        )
    }

    @Test
    fun testBlackPawnCapture() {
        assertTrue(
            blackPawn.canCapture(Movement(Coords(4, 1), Coords(5, 2))),
            "can capture diagonally"
        )
        assertTrue(
            !blackPawn.canCapture(Movement(Coords(4, 1), Coords(4, 2))),
            "can ONLY capture diagonally"
        )
        assertTrue(
            !blackPawn.canCapture(Movement(Coords(4, 1), Coords(4, 3))),
            "can ONLY capture diagonally"
        )
        assertTrue(
            !blackPawn.canCapture(Movement(Coords(4, 1), Coords(5, 0))),
            "cannot capture diagonally in wrong direction"
        )
    }
}