package engineTest.v1

import engine.Fen
import engine.v1.BoardToFenAdapter
import engine.v1.FenToBoardAdapter
import engine.v1.Game
import engine.v1.GameToFenAdapter
import kotlin.test.Test
import kotlin.test.asserter

class FenTest {
    @Test
    fun testFenParsesCorrectly() {
        val fen = Fen()
        asserter.assertEquals(
            "boardRepresentation",
            "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR",
            fen.boardRepresentation
        )
        asserter.assertEquals("sideToMove", "w", fen.sideToMove)
        asserter.assertEquals("castlingAvailability", "KQkq", fen.castlingAvailability)
        asserter.assertEquals("enPassantTarget", "-", fen.enPassantTarget)
        asserter.assertEquals("halfMoveClock", 0, fen.halfMoveClock)
        asserter.assertEquals("fullMoveNumber", 1, fen.fullMoveClock)
    }

    @Test
    fun testStartingFenToBoardRepresentation() {
        val fen = Fen()
        val board = FenToBoardAdapter(fen).board
        asserter.assertEquals("a1 piece", 'R', board.getValue(7).getValue(0).piece?.symbol)
        asserter.assertEquals("b1 piece", 'N', board.getValue(7).getValue(1).piece?.symbol)
        asserter.assertEquals("c1 piece", 'B', board.getValue(7).getValue(2).piece?.symbol)
        asserter.assertEquals("d1 piece", 'Q', board.getValue(7).getValue(3).piece?.symbol)
        asserter.assertEquals("e1 piece", 'K', board.getValue(7).getValue(4).piece?.symbol)
        asserter.assertEquals("f1 piece", 'B', board.getValue(7).getValue(5).piece?.symbol)
        asserter.assertEquals("g1 piece", 'N', board.getValue(7).getValue(6).piece?.symbol)
        asserter.assertEquals("h1 piece", 'R', board.getValue(7).getValue(7).piece?.symbol)
        asserter.assertEquals("a2 piece", 'P', board.getValue(6).getValue(0).piece?.symbol)
        asserter.assertEquals("b2 piece", 'P', board.getValue(6).getValue(1).piece?.symbol)
        asserter.assertEquals("c2 piece", 'P', board.getValue(6).getValue(2).piece?.symbol)
        asserter.assertEquals("d2 piece", 'P', board.getValue(6).getValue(3).piece?.symbol)
        asserter.assertEquals("e2 piece", 'P', board.getValue(6).getValue(4).piece?.symbol)
        asserter.assertEquals("f2 piece", 'P', board.getValue(6).getValue(5).piece?.symbol)
        asserter.assertEquals("g2 piece", 'P', board.getValue(6).getValue(6).piece?.symbol)
        asserter.assertEquals("h2 piece", 'P', board.getValue(6).getValue(7).piece?.symbol)
        asserter.assertEquals("a3 piece", null, board.getValue(5).getValue(0).piece)
        asserter.assertEquals("b3 piece", null, board.getValue(5).getValue(1).piece)
        asserter.assertEquals("c3 piece", null, board.getValue(5).getValue(2).piece)
        asserter.assertEquals("d3 piece", null, board.getValue(5).getValue(3).piece)
        asserter.assertEquals("e3 piece", null, board.getValue(5).getValue(4).piece)
        asserter.assertEquals("f3 piece", null, board.getValue(5).getValue(5).piece)
        asserter.assertEquals("g3 piece", null, board.getValue(5).getValue(6).piece)
        asserter.assertEquals("h3 piece", null, board.getValue(5).getValue(7).piece)
        asserter.assertEquals("a4 piece", null, board.getValue(4).getValue(0).piece)
        asserter.assertEquals("b4 piece", null, board.getValue(4).getValue(1).piece)
        asserter.assertEquals("c4 piece", null, board.getValue(4).getValue(2).piece)
        asserter.assertEquals("d4 piece", null, board.getValue(4).getValue(3).piece)
        asserter.assertEquals("e4 piece", null, board.getValue(4).getValue(4).piece)
        asserter.assertEquals("f4 piece", null, board.getValue(4).getValue(5).piece)
        asserter.assertEquals("g4 piece", null, board.getValue(4).getValue(6).piece)
        asserter.assertEquals("h4 piece", null, board.getValue(4).getValue(7).piece)
        asserter.assertEquals("a5 piece", null, board.getValue(3).getValue(0).piece)
        asserter.assertEquals("b5 piece", null, board.getValue(3).getValue(1).piece)
        asserter.assertEquals("c5 piece", null, board.getValue(3).getValue(2).piece)
        asserter.assertEquals("d5 piece", null, board.getValue(3).getValue(3).piece)
        asserter.assertEquals("e5 piece", null, board.getValue(3).getValue(4).piece)
        asserter.assertEquals("f5 piece", null, board.getValue(3).getValue(5).piece)
        asserter.assertEquals("g5 piece", null, board.getValue(3).getValue(6).piece)
        asserter.assertEquals("h5 piece", null, board.getValue(3).getValue(7).piece)
        asserter.assertEquals("a6 piece", null, board.getValue(2).getValue(0).piece)
        asserter.assertEquals("b6 piece", null, board.getValue(2).getValue(1).piece)
        asserter.assertEquals("c6 piece", null, board.getValue(2).getValue(2).piece)
        asserter.assertEquals("d6 piece", null, board.getValue(2).getValue(3).piece)
        asserter.assertEquals("e6 piece", null, board.getValue(2).getValue(4).piece)
        asserter.assertEquals("f6 piece", null, board.getValue(2).getValue(5).piece)
        asserter.assertEquals("g6 piece", null, board.getValue(2).getValue(6).piece)
        asserter.assertEquals("h6 piece", null, board.getValue(2).getValue(7).piece)
        asserter.assertEquals("a7 piece", 'p', board.getValue(1).getValue(0).piece?.symbol)
        asserter.assertEquals("b7 piece", 'p', board.getValue(1).getValue(1).piece?.symbol)
        asserter.assertEquals("c7 piece", 'p', board.getValue(1).getValue(2).piece?.symbol)
        asserter.assertEquals("d7 piece", 'p', board.getValue(1).getValue(3).piece?.symbol)
        asserter.assertEquals("e7 piece", 'p', board.getValue(1).getValue(4).piece?.symbol)
        asserter.assertEquals("f7 piece", 'p', board.getValue(1).getValue(5).piece?.symbol)
        asserter.assertEquals("g7 piece", 'p', board.getValue(1).getValue(6).piece?.symbol)
        asserter.assertEquals("h7 piece", 'p', board.getValue(1).getValue(7).piece?.symbol)
        asserter.assertEquals("a8 piece", 'r', board.getValue(0).getValue(0).piece?.symbol)
        asserter.assertEquals("b8 piece", 'n', board.getValue(0).getValue(1).piece?.symbol)
        asserter.assertEquals("c8 piece", 'b', board.getValue(0).getValue(2).piece?.symbol)
        asserter.assertEquals("d8 piece", 'q', board.getValue(0).getValue(3).piece?.symbol)
        asserter.assertEquals("e8 piece", 'k', board.getValue(0).getValue(4).piece?.symbol)
        asserter.assertEquals("f8 piece", 'b', board.getValue(0).getValue(5).piece?.symbol)
        asserter.assertEquals("g8 piece", 'n', board.getValue(0).getValue(6).piece?.symbol)
        asserter.assertEquals("h8 piece", 'r', board.getValue(0).getValue(7).piece?.symbol)
    }

    @Test
    fun testFenToBoardRepresentation() {
        val fen = Fen("r3k2r/Pppp1ppp/1b3nbN/nP6/BBP1P3/q4N2/Pp1P2PP/R2Q1RK1 w kq - 0 1")
        val board = FenToBoardAdapter(fen).board
        asserter.assertEquals("a1 piece", 'R', board.getValue(7).getValue(0).piece?.symbol)
        asserter.assertEquals("b1 piece", null, board.getValue(7).getValue(1).piece)
        asserter.assertEquals("c1 piece", null, board.getValue(7).getValue(2).piece)
        asserter.assertEquals("d1 piece", 'Q', board.getValue(7).getValue(3).piece?.symbol)
        asserter.assertEquals("e1 piece", null, board.getValue(7).getValue(4).piece)
        asserter.assertEquals("f1 piece", 'R', board.getValue(7).getValue(5).piece?.symbol)
        asserter.assertEquals("g1 piece", 'K', board.getValue(7).getValue(6).piece?.symbol)
        asserter.assertEquals("h1 piece", null, board.getValue(7).getValue(7).piece)
        asserter.assertEquals("a2 piece", 'P', board.getValue(6).getValue(0).piece?.symbol)
        asserter.assertEquals("b2 piece", 'p', board.getValue(6).getValue(1).piece?.symbol)
        asserter.assertEquals("c2 piece", null, board.getValue(6).getValue(2).piece)
        asserter.assertEquals("d2 piece", 'P', board.getValue(6).getValue(3).piece?.symbol)
        asserter.assertEquals("e2 piece", null, board.getValue(6).getValue(4).piece)
        asserter.assertEquals("f2 piece", null, board.getValue(6).getValue(5).piece)
        asserter.assertEquals("g2 piece", 'P', board.getValue(6).getValue(6).piece?.symbol)
        asserter.assertEquals("h2 piece", 'P', board.getValue(6).getValue(7).piece?.symbol)
        asserter.assertEquals("a3 piece", 'q', board.getValue(5).getValue(0).piece?.symbol)
        asserter.assertEquals("b3 piece", null, board.getValue(5).getValue(1).piece)
        asserter.assertEquals("c3 piece", null, board.getValue(5).getValue(2).piece)
        asserter.assertEquals("d3 piece", null, board.getValue(5).getValue(3).piece)
        asserter.assertEquals("e3 piece", null, board.getValue(5).getValue(4).piece)
        asserter.assertEquals("f3 piece", 'N', board.getValue(5).getValue(5).piece?.symbol)
        asserter.assertEquals("g3 piece", null, board.getValue(5).getValue(6).piece)
        asserter.assertEquals("h3 piece", null, board.getValue(5).getValue(7).piece)
        asserter.assertEquals("a4 piece", 'B', board.getValue(4).getValue(0).piece?.symbol)
        asserter.assertEquals("b4 piece", 'B', board.getValue(4).getValue(1).piece?.symbol)
        asserter.assertEquals("c4 piece", 'P', board.getValue(4).getValue(2).piece?.symbol)
        asserter.assertEquals("d4 piece", null, board.getValue(4).getValue(3).piece)
        asserter.assertEquals("e4 piece", 'P', board.getValue(4).getValue(4).piece?.symbol)
        asserter.assertEquals("f4 piece", null, board.getValue(4).getValue(5).piece)
        asserter.assertEquals("g4 piece", null, board.getValue(4).getValue(6).piece)
        asserter.assertEquals("h4 piece", null, board.getValue(4).getValue(7).piece)
        asserter.assertEquals("a5 piece", 'n', board.getValue(3).getValue(0).piece?.symbol)
        asserter.assertEquals("b5 piece", 'P', board.getValue(3).getValue(1).piece?.symbol)
        asserter.assertEquals("c5 piece", null, board.getValue(3).getValue(2).piece)
        asserter.assertEquals("d5 piece", null, board.getValue(3).getValue(3).piece)
        asserter.assertEquals("e5 piece", null, board.getValue(3).getValue(4).piece)
        asserter.assertEquals("f5 piece", null, board.getValue(3).getValue(5).piece)
        asserter.assertEquals("g5 piece", null, board.getValue(3).getValue(6).piece)
        asserter.assertEquals("h5 piece", null, board.getValue(3).getValue(7).piece)
        asserter.assertEquals("a6 piece", null, board.getValue(2).getValue(0).piece)
        asserter.assertEquals("b6 piece", 'b', board.getValue(2).getValue(1).piece?.symbol)
        asserter.assertEquals("c6 piece", null, board.getValue(2).getValue(2).piece)
        asserter.assertEquals("d6 piece", null, board.getValue(2).getValue(3).piece)
        asserter.assertEquals("e6 piece", null, board.getValue(2).getValue(4).piece)
        asserter.assertEquals("f6 piece", 'n', board.getValue(2).getValue(5).piece?.symbol)
        asserter.assertEquals("g6 piece", 'b', board.getValue(2).getValue(6).piece?.symbol)
        asserter.assertEquals("h6 piece", 'N', board.getValue(2).getValue(7).piece?.symbol)
        asserter.assertEquals("a7 piece", 'P', board.getValue(1).getValue(0).piece?.symbol)
        asserter.assertEquals("b7 piece", 'p', board.getValue(1).getValue(1).piece?.symbol)
        asserter.assertEquals("c7 piece", 'p', board.getValue(1).getValue(2).piece?.symbol)
        asserter.assertEquals("d7 piece", 'p', board.getValue(1).getValue(3).piece?.symbol)
        asserter.assertEquals("e7 piece", null, board.getValue(1).getValue(4).piece)
        asserter.assertEquals("f7 piece", 'p', board.getValue(1).getValue(5).piece?.symbol)
        asserter.assertEquals("g7 piece", 'p', board.getValue(1).getValue(6).piece?.symbol)
        asserter.assertEquals("h7 piece", 'p', board.getValue(1).getValue(7).piece?.symbol)
        asserter.assertEquals("a8 piece", 'r', board.getValue(0).getValue(0).piece?.symbol)
        asserter.assertEquals("b8 piece", null, board.getValue(0).getValue(1).piece)
        asserter.assertEquals("c8 piece", null, board.getValue(0).getValue(2).piece)
        asserter.assertEquals("d8 piece", null, board.getValue(0).getValue(3).piece)
        asserter.assertEquals("e8 piece", 'k', board.getValue(0).getValue(4).piece?.symbol)
        asserter.assertEquals("f8 piece", null, board.getValue(0).getValue(5).piece)
        asserter.assertEquals("g8 piece", null, board.getValue(0).getValue(6).piece)
        asserter.assertEquals("h8 piece", 'r', board.getValue(0).getValue(7).piece?.symbol)
    }

    @Test
    fun testBoardToFenAdapter() {
        val fen = Fen("r3k2r/Pppp1ppp/1b3nbN/nP6/BBP1P3/q4N2/Pp1P2PP/R2Q1RK1 w kq - 0 1")
        val board = FenToBoardAdapter(fen).board
        val fenBoard = BoardToFenAdapter(board).fenBoardRepresentation

        asserter.assertEquals("board to fen", "r3k2r/Pppp1ppp/1b3nbN/nP6/BBP1P3/q4N2/Pp1P2PP/R2Q1RK1", fenBoard)
    }

    @Test
    fun testGameToFenAdapter() {
        val game = Game("r3k2r/Pppp1ppp/1b3nbN/nP6/BBP1P3/q4N2/Pp1P2PP/R2Q1RK1 w kq - 0 1")

        val fen = GameToFenAdapter(game).fen

        asserter.assertEquals("game to fen", "r3k2r/Pppp1ppp/1b3nbN/nP6/BBP1P3/q4N2/Pp1P2PP/R2Q1RK1 w kq - 0 1", fen)
    }
}