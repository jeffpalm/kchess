package engineTest

import engine.Fen
import engine.Game
import engine.Piece
import engine.Square
import engine.move.PseudoMove
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import kotlin.test.assertEquals
import kotlin.test.assertNull

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class GameTest {
    private val defaultGame = Game()

    @BeforeAll
    fun setup() {
        defaultGame.makeMove(PseudoMove(Square.d2, Square.d4, Piece.wPawn))
    }

    @Test
    fun getMoves() {
        val game = Game()
        game.makeMove(PseudoMove(Square.d2, Square.d4, Piece.wPawn))
        assertEquals(Piece.wPawn, game.moves[0].piece)
        assertEquals(Square.d4, game.moves[0].to)
        assertEquals(Square.d2, game.moves[0].from)
        assertNull(game.moves[0].capture)
    }

    @Test
    fun getGameData() {
    }

    @Test
    fun `makeMove updates bitboard correctly - pawn push`() {
        val result = defaultGame.data.board.wPawns
        assertEquals(0x800f700UL, result, "Expected: 0x800f700UL\nReceived: 0x${result.toString(16)}UL")
    }

    @Test
    fun `makeMove updates bitboard correctly - capture`() {
        val game = Game(Fen("rnb1kbnr/pp1ppppp/8/q1p5/3P4/2N5/PPP1PPPP/R1BQKBNR w KQkq - 2 3"))
        game.makeMove(PseudoMove(Square.d4, Square.c5, Piece.wPawn))
        val result = game.data.board.bPawns
        assertEquals(0xfb000000000000UL, result, "Expected: 0xf7000000000000UL\nReceived: 0x${result.toString(16)}UL")
    }

    @Test
    fun `makeMove updates enPassantTarget correctly`() {
        assertEquals(Square.d3, defaultGame.data.enPassantTarget)
    }

    @Test
    fun `increments game clock correctly`() {
        assertEquals(1, defaultGame.data.halfMoveClock)
        assertEquals(1, defaultGame.data.fullMoveClock)
    }

    @Test
    fun undoMove() {
        val game = Game()
        game.makeMove(PseudoMove(Square.d2, Square.d4, Piece.wPawn))
        game.undoMove()
        assertEquals(null, game.data.enPassantTarget)
        assertEquals("KQkq", game.data.castleAvail)
        assertEquals(0, game.data.halfMoveClock)
        assertEquals(1, game.data.fullMoveClock)
    }
}