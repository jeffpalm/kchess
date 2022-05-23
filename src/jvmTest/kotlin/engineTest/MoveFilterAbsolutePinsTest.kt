package engineTest

import engine.Fen
import engine.Game
import engine.Piece
import engine.SquareMap
import engine.move.AbstractMoveGenerator
import engine.move.MoveGenCtx
import engine.move.filters.MoveFilterAbsolutePins
import engine.move.rules.MoveRuleKnight
import engine.move.rules.MoveRuleWhitePawnAttack
import engine.move.rules.MoveRuleWhitePawnPush
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

private class MoveFilterGenerator(context: MoveGenCtx) : AbstractMoveGenerator(
    context, listOf(
        MoveRuleKnight(), MoveRuleWhitePawnAttack(), MoveRuleWhitePawnPush()
    ), listOf(
        MoveFilterAbsolutePins()
    )
)

internal class MoveFilterAbsolutePinsTest {
    @Test
    fun `pinned white knight`() {
        val game = Game(Fen("rnb1kbnr/pp1ppppp/8/q1p5/3P4/2N5/PPP1PPPP/R1BQKBNR w KQkq - 2 3"))
        val moves = MoveFilterGenerator(MoveGenCtx(game.data)).execute()
        for (move in moves) {
            assertTrue("${move.from.name} cannot equal c3") {
                move.from != SquareMap.c3
            }
        }
    }

    @Test
    fun `pinned pawn by rook`() {
        val game = Game(Fen("1nbqkbnr/1pppp1pp/4r3/p7/P4p2/R3PN1P/1PPP1PP1/1NBQKB1R w Kk - 0 6"))
        val moves = MoveFilterGenerator(MoveGenCtx(game.data)).execute()
        val e3PawnMoves = moves.filter { it.from == SquareMap.e3 }
        for (move in e3PawnMoves) {
            assertTrue { move.piece == Piece.whitePawn }
            assertTrue { move.to == SquareMap.e4 }
        }
    }

    @Test
    fun `double pin`() {
        val game = Game(Fen("1nbqk1nr/1ppp2pp/4p3/p3r3/Pb3p1N/R3P1PP/1PPP1PB1/1NBQK2R w Kk - 2 9"))
        val moves = MoveFilterGenerator(MoveGenCtx(game.data)).execute()
        for (move in moves) {
            assertTrue { move.from != SquareMap.d2 }
            if (move.from == SquareMap.e3) {
                assertTrue { move.to == SquareMap.e4 }
            }
        }
    }

}