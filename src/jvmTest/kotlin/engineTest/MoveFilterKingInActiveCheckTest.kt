package engineTest

import engine.Fen
import engine.Game
import engine.move.MoveGenCtx
import engine.move.MoveGenerator
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class MoveFilterKingInActiveCheckTest {

    @Test
    fun shouldRun() {

    }

    @Test
    fun `black king active check no escape squares`() {
        val game = Game(Fen("rnbqkbnr/ppp1pppp/8/1B1p4/4P3/8/PPPP1PPP/RNBQK1NR b KQkq - 1 2"))
        val moves = MoveGenerator(MoveGenCtx(game.data.clone())).execute()
        assertEquals(5, moves.size)
    }

    @Test
    fun `white king in active check no escape squares`() {
        val game = Game(Fen("rnbqk1nr/pppp1ppp/8/4P3/1b6/8/PPP1PPPP/RNBQKBNR w KQkq - 1 3"))
        val moves = MoveGenerator(MoveGenCtx(game.data.clone())).execute()
        assertEquals(5, moves.size)
    }

    @Test
    fun `white king active check with escape squares`() {
        val game = Game(Fen("rnbqk1nr/ppp2ppp/8/3pp3/1b1PP3/5P2/PPP3PP/RNBQKBNR w KQkq - 1 4"))
        val moves = MoveGenerator(MoveGenCtx(game.data.clone())).execute()
        assertEquals(7, moves.size)
    }

    @Test
    fun `black king active check with escape squares`() {
        val game = Game(Fen("rnbqkbnr/ppp3pp/5p2/1B1pp3/3PP3/5P2/PPP3PP/RNBQK1NR b KQkq - 1 4"))
        val moves = MoveGenerator(MoveGenCtx(game.data.clone())).execute()
        assertEquals(7, moves.size)
    }

    @Test
    fun `black king active check every possible escape`() {
        val game = Game(Fen("rnb1k1nr/ppp3pp/8/1B1pppq1/3PPPP1/b4N2/PPP4P/RNBQK2R b KQkq - 4 6"))
        val moves = MoveGenerator(MoveGenCtx(game.data.clone())).execute()
        val kingMoves = moves.filter { it.piece == 'k' }
        assertEquals(4, kingMoves.size)
    }

    @Test
    fun `black king chase`() {
        val game = Game(Fen("rnb3nr/ppp1k1pp/8/1B1pp1q1/P2PPP2/B4p2/P1P4P/RN1QK2R b KQ - 1 9"))
        val moves = MoveGenerator(MoveGenCtx(game.data.clone())).execute()
        val kingMoves = moves.filter { it.piece == 'k' }
        assertEquals(4, kingMoves.size)
    }
}