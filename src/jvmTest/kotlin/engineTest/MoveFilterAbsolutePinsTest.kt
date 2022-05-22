package engineTest

import engine.Fen
import engine.Game
import engine.SquareMap
import engine.move.AbstractMoveGenerator
import engine.move.MoveGenCtx
import engine.move.filters.MoveFilterAbsolutePins
import engine.move.rules.MoveRuleWhiteKnight
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

private class MoveFilterGenerator(context: MoveGenCtx) : AbstractMoveGenerator(
    context,
    listOf(
        MoveRuleWhiteKnight()
    ),
    listOf(
        MoveFilterAbsolutePins()
    )
)

internal class MoveFilterAbsolutePinsTest {
    @Test
    fun shouldRun() {

    }

    @Test
    fun `pinned white knight`() {
        val game = Game(Fen("rnb1kbnr/pp1ppppp/8/q1p5/3P4/2N5/PPP1PPPP/R1BQKBNR w KQkq - 2 3"))
        val moves = MoveFilterGenerator(MoveGenCtx(game.data)).execute()
        for(move in moves) {
            assertTrue("${move.from.name} cannot equal c3") {
                move.from != SquareMap.c3
            }
        }
    }
}