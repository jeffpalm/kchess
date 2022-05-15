import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue


class GameTest {
    private fun getTestMove(move: String, game: Game) {

    }


    @Test
    fun testGenerateBasePositionMoves() {
        val game = Game()
        val moves = game.generateMoves()
        assertEquals(20, moves.size)
    }

    @Test
    fun testMakeMoveBehavior() {
        val game = Game()

        game.makeMove(
            Move(
                game,
                Movement(Sq("e2").coords, Sq("e4").coords),
                game.board.getPiece(Sq("e2").coords)!!,
                null
            )
        )

        assertEquals(1, game.halfMoves, "Increments half moves correctly")
        assertEquals(1, game.fullMoves, "Increments full moves correctly")
        assertEquals(1, game.moves.size, "Adds move to moves list")
        assertEquals("e3", game.enPassant, "Sets en passant target correctly")
        assertEquals(PieceColor.BLACK, game.turn, "Switches turn correctly")

        game.makeMove(
            Move(
                game,
                Movement(Sq("e7").coords, Sq("e5").coords),
                game.board.getPiece(Sq("e7").coords)!!,
                null
            )
        )

        assertEquals(0, game.halfMoves, "Resets half moves correctly")
        assertEquals(2, game.fullMoves, "Increments full moves correctly")
        assertEquals(2, game.moves.size, "Adds move to moves list")
        assertEquals("e6", game.enPassant, "Sets en passant target correctly")
        assertEquals(PieceColor.WHITE, game.turn, "Switches turn correctly")

        game.makeMove(
            Move(
                game,
                Movement(Sq("d2").coords, Sq("d4").coords),
                game.board.getPiece(Sq("d2").coords)!!,
                null
            )
        )

        assertEquals(1, game.halfMoves, "Increments half moves correctly")
        assertEquals(2, game.fullMoves, "Increments full moves correctly")
        assertEquals(3, game.moves.size, "Adds move to moves list")
        assertEquals("d3", game.enPassant, "Sets en passant target correctly")
        assertEquals(PieceColor.BLACK, game.turn, "Switches turn correctly")

        game.makeMove(
            Move(
                game,
                Movement(Sq("d7").coords, Sq("d5").coords),
                game.board.getPiece(Sq("d7").coords)!!,
                null
            )
        )

        assertEquals(0, game.halfMoves, "Resets half moves correctly")
        assertEquals(3, game.fullMoves, "Increments full moves correctly")
        assertEquals(4, game.moves.size, "Adds move to moves list")
        assertEquals("d6", game.enPassant, "Sets en passant target correctly")
        assertEquals(PieceColor.WHITE, game.turn, "Switches turn correctly")
    }

    @Test
    fun testCanMakeSuccessfulCastle() {
        val game = Game("rnbqk1nr/pp1p1ppp/2pb4/4p3/2B1P3/5N2/PPPP1PPP/RNBQK2R w KQkq - 2 4")

        game.makeMove(
            Move(
                game,
                Movement(Sq("e1").coords, Sq("g1").coords),
                game.board.getPiece(Sq("e1").coords)!!,
                null
            )
        )

        assertNull(game.board.getPiece(Sq("h1").coords), "Rook is removed from original square")
        assertNull(game.board.getPiece(Sq("e1").coords), "King is removed from original square")
        assertEquals(PieceType.ROOK, game.board.getPiece(Sq("f1").coords)!!.type, "Moved rook to correct square")
        assertEquals(PieceType.KING, game.board.getPiece(Sq("g1").coords)!!.type, "Moved king to correct square")
        assertEquals("O-O", game.moves[game.moves.size - 1].castling?.notation, "Handles correct notation")
        assertTrue(!game.castling.whiteQueenSide, "Removes white queen side castling")
        assertTrue(!game.castling.whiteKingSide, "Removes white king side castling")
        assertTrue(game.castling.blackKingSide, "Does not remove black king side castling")
        assertTrue(game.castling.blackQueenSide, "Does not remove black queen side castling")

        game.undoMove()

        assertNull(game.board.getPiece(Sq("f1").coords), "Rook is removed from previous square")
        assertNull(game.board.getPiece(Sq("g1").coords), "King is removed from previous square")
        assertEquals(PieceType.ROOK, game.board.getPiece(Sq("h1").coords)!!.type, "Moved rook to correct square")
        assertEquals(PieceType.KING, game.board.getPiece(Sq("e1").coords)!!.type, "Moved king to correct square")
        assertTrue(game.castling.whiteQueenSide, "Does not remove white queen side castling")
        assertTrue(game.castling.whiteKingSide, "Does not remove white king side castling")
        assertTrue(game.castling.blackKingSide, "Does not change black king side castling")
        assertTrue(game.castling.blackQueenSide, "Does not change black queen side castling")
    }
}