import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue


class BoardTest {
    private val board = Board(Fen())

    @Test
    fun testGetsCorrectPiece() {
        assertTrue(board.getPiece(Coords(4, 0)) is King)
    }

    @Test
    fun testSetPiece() {
        board.setPiece(Coords(4, 0), null)
        assertTrue(board.getPiece(Coords(4, 0)) == null)
        board.setPiece(Coords(4, 0), King(PieceColor.BLACK))
        assertTrue(board.getPiece(Coords(4, 0)) is King)
    }

    @Test
    fun testGetKing() {
        val whiteKingSquare = board.getKing(PieceColor.WHITE)
        val blackKingSquare = board.getKing(PieceColor.BLACK)
        assertEquals(4, whiteKingSquare.x)
        assertEquals(7, whiteKingSquare.y)
        assertEquals(4, blackKingSquare.x)
        assertEquals(0, blackKingSquare.y)
        assertTrue(whiteKingSquare.piece is King)
        assertTrue(blackKingSquare.piece is King)
        assertEquals((whiteKingSquare.piece as King).color, PieceColor.WHITE)
        assertEquals((blackKingSquare.piece as King).color, PieceColor.BLACK)
    }

    @Test
    fun testGetSquaresByPieceColor() {
        val squares = board.getSquaresByPieceColor(PieceColor.BLACK)
        assertEquals(16, squares.size)
        val whiteSquares = board.getSquaresByPieceColor(PieceColor.WHITE)
        assertEquals(16, whiteSquares.size)
    }

    @Test
    fun testIsPathClear() {
        assertTrue(board.isPathClear(PotentialMove(Coords(4, 6), Coords(4, 4))))

        assertTrue(!board.isPathClear(PotentialMove(Coords(0, 0), Coords(0, 7))))
        assertTrue(!board.isPathClear(PotentialMove(Coords(0, 0), Coords(7, 7))))
        assertTrue(!board.isPathClear(PotentialMove(Coords(0, 0), Coords(7, 0))))
        assertTrue(!board.isPathClear(PotentialMove(Coords(0, 7), Coords(0, 0))))
        assertTrue(!board.isPathClear(PotentialMove(Coords(7, 7), Coords(0, 7))))
        assertTrue(!board.isPathClear(PotentialMove(Coords(7, 7), Coords(7, 0))))
        assertTrue(!board.isPathClear(PotentialMove(Coords(7, 0), Coords(7, 7))))

        assertTrue(board.isPathClear(PotentialMove(Coords(4, 4), Coords(4, 5))), "handles single square s")
        assertTrue(board.isPathClear(PotentialMove(Coords(4, 4), Coords(4, 3))), "handles single square n")
        assertTrue(board.isPathClear(PotentialMove(Coords(4, 4), Coords(3, 4))), "handles single square w")
        assertTrue(board.isPathClear(PotentialMove(Coords(4, 4), Coords(5, 4))), "handles single square e")
        assertTrue(board.isPathClear(PotentialMove(Coords(4, 4), Coords(5, 5))), "handles single square ne")
        assertTrue(board.isPathClear(PotentialMove(Coords(4, 4), Coords(3, 3))), "handles single square nw")
        assertTrue(board.isPathClear(PotentialMove(Coords(4, 4), Coords(3, 5))), "handles single square sw")
        assertTrue(board.isPathClear(PotentialMove(Coords(4, 4), Coords(5, 3))), "handles single square e")

        assertTrue(board.isPathClear(PotentialMove(Coords(0, 4), Coords(7, 4))), "handles horizontal")
        assertTrue(board.isPathClear(PotentialMove(Coords(7, 4), Coords(0, 4))), "handles horizontal")
        assertTrue(board.isPathClear(PotentialMove(Coords(0, 1), Coords(0, 6))), "handles vertical")
        assertTrue(board.isPathClear(PotentialMove(Coords(0, 6), Coords(0, 1))), "handles vertical")
        assertTrue(board.isPathClear(PotentialMove(Coords(1, 6), Coords(6, 1))), "handles diagonal")
        assertTrue(board.isPathClear(PotentialMove(Coords(6, 1), Coords(1, 6))), "handles diagonal")
        assertTrue(board.isPathClear(PotentialMove(Coords(1, 1), Coords(6, 6))), "handles diagonal")
        assertTrue(board.isPathClear(PotentialMove(Coords(6, 6), Coords(1, 1))), "handles diagonal")

        assertTrue(!board.isPathClear(PotentialMove(Coords(0, 1), Coords(3, 6))), "handles invalid move")
    }

}