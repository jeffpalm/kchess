package engineTest

import engine.Fen
import engine.Game
import engine.move.AbstractMoveGenerator
import engine.move.MoveGenCtx
import engine.move.rules.MoveRuleKing
import org.junit.jupiter.api.Test

private class KingMoveGenerator(context: MoveGenCtx) : AbstractMoveGenerator(
    context,
    listOf(
        MoveRuleKing()
    ),
    listOf()
)

class MoveRuleKingTest {
    @Test
    fun `king movement`(){
        val game = Game(Fen("r3k2r/Pppp1ppp/1b3nbN/nPP5/BB2P3/q4N2/Pp1P2PP/R2Q1RK1 b kq - 0 1"))

        MoveTest(
           KingMoveGenerator(
               MoveGenCtx(game.data)
           ),
            0x2810000000000000UL
        )
    }
}