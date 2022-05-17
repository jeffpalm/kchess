package engineTest.v1

import engine.v1.*
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
                Movement(Sq("e2").xy, Sq("e4").xy),
                game.board.getPiece(Sq("e2").xy)!!,
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
                Movement(Sq("e7").xy, Sq("e5").xy),
                game.board.getPiece(Sq("e7").xy)!!,
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
                Movement(Sq("d2").xy, Sq("d4").xy),
                game.board.getPiece(Sq("d2").xy)!!,
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
                Movement(Sq("d7").xy, Sq("d5").xy),
                game.board.getPiece(Sq("d7").xy)!!,
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
                Movement(Sq("e1").xy, Sq("g1").xy),
                game.board.getPiece(Sq("e1").xy)!!,
                null
            )
        )

        assertNull(game.board.getPiece(Sq("h1").xy), "Rook is removed from original square")
        assertNull(game.board.getPiece(Sq("e1").xy), "King is removed from original square")
        assertEquals(PieceType.ROOK, game.board.getPiece(Sq("f1").xy)!!.type, "Moved rook to correct square")
        assertEquals(PieceType.KING, game.board.getPiece(Sq("g1").xy)!!.type, "Moved king to correct square")
        assertEquals("O-O", game.moves[game.moves.size - 1].castling?.notation, "Handles correct notation")
        assertTrue(!game.castling.whiteQueenSide, "Removes white queen side castling")
        assertTrue(!game.castling.whiteKingSide, "Removes white king side castling")
        assertTrue(game.castling.blackKingSide, "Does not remove black king side castling")
        assertTrue(game.castling.blackQueenSide, "Does not remove black queen side castling")

        game.undoMove()

        assertNull(game.board.getPiece(Sq("f1").xy), "Rook is removed from previous square")
        assertNull(game.board.getPiece(Sq("g1").xy), "King is removed from previous square")
        assertEquals(PieceType.ROOK, game.board.getPiece(Sq("h1").xy)!!.type, "Moved rook to correct square")
        assertEquals(PieceType.KING, game.board.getPiece(Sq("e1").xy)!!.type, "Moved king to correct square")
        assertTrue(game.castling.whiteQueenSide, "Does not remove white queen side castling")
        assertTrue(game.castling.whiteKingSide, "Does not remove white king side castling")
        assertTrue(game.castling.blackKingSide, "Does not change black king side castling")
        assertTrue(game.castling.blackQueenSide, "Does not change black queen side castling")
    }

    @Test
    fun testIsMoveCheck() {
        val game = Game("rnbqkbnr/ppp1pppp/8/3p4/2P5/8/PP1PPPPP/RNBQKBNR w KQkq d6 0 2")
        val piece = game.board.getPiece(Sq("d1").xy)!!
        val move = Move(game, Movement(Sq("d1").xy, Sq("a4").xy), piece, null)

        assertTrue(game.isMoveCheck(move), "Returns true for check")
    }

    @Test
    fun testWillPutKingInCheck() {
        val game = Game("r1bqkbnr/ppp1pppp/2n5/3p4/Q1P5/5N2/PP1PPPPP/RNB1KB1R b KQkq - 3 3")
        val piece = game.board.getPiece(Sq("c6").xy)!!
        val move = Move(game, Movement(Sq("c6").xy, Sq("b4").xy), piece, null)

        assertTrue(game.willMovePutKingInCheck(move))
    }

    @Test
    fun testWillRemoveKingFromCheck() {
        val game = Game("r3k2r/Pppp1ppp/1b3nbN/nP6/BBP1P3/q4N2/Pp1P2PP/R2Q1RK1 w kq - 0 1")
        val bishop = game.board.getPiece(Sq("b4").xy)!!
        val bishopMove = Move(game, Movement(Sq("b4").xy, Sq("c5").xy), bishop, null)

        assertTrue(game.willRemoveKingFromCheck(bishopMove))

        val pawn = game.board.getPiece(Sq("d2").xy)!!
        val pawnMove = Move(game, Movement(Sq("d2").xy, Sq("d4").xy), pawn, null)

        assertTrue(game.willRemoveKingFromCheck(pawnMove))
    }
}