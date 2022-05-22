package engineTest

import engine.Color
import engine.Fen
import engine.BitBoard
import engine.GameData
import engine.adapter.FenToBitBoard
import engine.move.AbstractMoveGenerator
import engine.move.MoveGenCtx
import engine.move.rules.MoveRuleBlackPawnAttack
import engine.move.rules.MoveRuleBlackPawnPush
import engine.move.rules.MoveRuleWhitePawnAttack
import engine.move.rules.MoveRuleWhitePawnPush
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

private class PawnMoveGenerator(context: MoveGenCtx) : AbstractMoveGenerator(
    context, listOf(
        MoveRuleWhitePawnPush(),
        MoveRuleWhitePawnAttack(),
        MoveRuleBlackPawnPush(),
        MoveRuleBlackPawnAttack()
    ),
    listOf()
)

internal class PawnMovesTest {
    private val blackToMove = MoveGenCtx(
        GameData(
            BitBoard(),
            Color.BLACK
        )
    )
    private val whiteToMove = MoveGenCtx(
        GameData(
            BitBoard(),
            Color.WHITE
        )
    )

    @Test
    fun shouldRun() {
        assertTrue("shouldRun flips based on turn"){
            MoveRuleWhitePawnPush().shouldRun(whiteToMove)
            !MoveRuleWhitePawnPush().shouldRun(blackToMove)
            MoveRuleWhitePawnAttack().shouldRun(whiteToMove)
            !MoveRuleWhitePawnAttack().shouldRun(blackToMove)
            MoveRuleBlackPawnPush().shouldRun(blackToMove)
            !MoveRuleBlackPawnPush().shouldRun(whiteToMove)
            MoveRuleBlackPawnAttack().shouldRun(blackToMove)
            !MoveRuleBlackPawnAttack().shouldRun(whiteToMove)
        }
    }

    @Test
    fun `white pawns starting position`() {
        MoveTest(PawnMoveGenerator(whiteToMove), 0xFFFF0000UL)
    }

    @Test
    fun `black pawns starting position`() {
        MoveTest(PawnMoveGenerator(blackToMove), 0xFFFF00000000UL)
    }

    @Test
    fun `empty board white pawns rank 2 black pawns rank 3`() {
        MoveTest(
            PawnMoveGenerator(
            MoveGenCtx(
            GameData(
            FenToBitBoard(Fen("8/8/8/8/8/pppppppp/PPPPPPPP/8 w - - 0 1")).output,
            Color.WHITE
        )
        )
        ), 0xff0000UL)
    }

    @Test
    fun `empty board white pawns rank 2 and rank 3`() {
        MoveTest(
            PawnMoveGenerator(
            MoveGenCtx(
            GameData(
            FenToBitBoard(Fen("8/8/8/8/8/PPPPPPPP/PPPPPPPP/8 w - - 0 1")).output,
            Color.WHITE
        )
        )
        ), 0xff000000UL)
    }

    @Test
    fun `empty board white pawn cannot attack push piece`() {
        MoveTest(
            PawnMoveGenerator(
            MoveGenCtx(
            GameData(
            FenToBitBoard(Fen("8/8/8/3n4/3P4/8/8/8 w - - 0 1")).output,
            Color.WHITE
        )
        )
        ), 0UL)
    }

    @Test
    fun `white pawn attack northwest`() {
        MoveTest(
            PawnMoveGenerator(
            MoveGenCtx(
            GameData(
            FenToBitBoard(Fen("8/8/8/2n5/3P4/8/8/8 w - - 0 1")).output,
            Color.WHITE
        )
        )
        ), 0xc00000000UL)
    }

    @Test
    fun `white pawn attack northeast`() {
        MoveTest(
            PawnMoveGenerator(
            MoveGenCtx(
            GameData(
            FenToBitBoard(Fen("8/8/8/4n3/3P4/8/8/8 w - - 0 1")).output,
            Color.WHITE
        )
        )
        ), 0x1800000000UL)
    }

    @Test
    fun `white pawn fork`() {
        MoveTest(
            PawnMoveGenerator(
            MoveGenCtx(
            GameData(
            FenToBitBoard(Fen("8/8/8/2n1n3/3P4/8/8/8 w - - 0 1")).output,
            Color.WHITE
        )
        )
        ), 0x1c00000000UL)
    }

    @Test
    fun `white pawn west edge`() {
        MoveTest(
            PawnMoveGenerator(
            MoveGenCtx(
            GameData(
            FenToBitBoard(Fen("8/8/8/nn6/P7/8/8/8 w - - 0 1")).output,
            Color.WHITE
        )
        )
        ), 0x200000000UL)
    }

    @Test
    fun `white pawns offset captures`() {
        MoveTest(
            PawnMoveGenerator(
            MoveGenCtx(
            GameData(
            FenToBitBoard(Fen("8/8/8/pppppppp/P1P1P1P1/8/8/8 w - - 0 1")).output,
            Color.WHITE
        )
        )
        ), 0xAA00000000UL)
    }

    @Test
    fun `black pawns offset captures`() {
        MoveTest(
            PawnMoveGenerator(
            MoveGenCtx(
            GameData(
            FenToBitBoard(Fen("8/8/8/1p1p1p1p/PPPPPPPP/8/8/8 w - - 0 1")).output,
            Color.BLACK
        )
        )
        ), 0x55000000UL)
    }

    @Test
    fun `black pawns two move`() {
        MoveTest(
            PawnMoveGenerator(
            MoveGenCtx(
            GameData(
            FenToBitBoard(Fen("8/p1pp1ppp/1p2p3/1P2P2P/8/8/8/8 b - - 0 1")).output,
            Color.BLACK
        )
        )
        ), 0xed6d00000000UL)
    }

    @Test
    fun `white pawn en passant`() {
        MoveTest(
            PawnMoveGenerator(
            MoveGenCtx(
            GameData(
            FenToBitBoard(Fen("rnbqkbnr/pp1p1ppp/8/2pPp3/8/8/PPP1PPPP/RNBQKBNR w KQkq c6 0 3")).output,
            Color.WHITE
        )
        )
        ), 0xc00f7f70000UL)
    }


}