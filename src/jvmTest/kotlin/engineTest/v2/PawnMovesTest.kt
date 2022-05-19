package engineTest.v2

import engine.Fen
import engine.v2.BitBoard
import engine.v2.GameData
import engine.v2.PieceColor
import engine.v2.adapters.FenToBitBoard
import engine.v2.moves.AbstractMoveGenerator
import engine.v2.moves.MoveGenCtx
import engine.v2.moves.rules.MoveRuleBlackPawnAttack
import engine.v2.moves.rules.MoveRuleBlackPawnPush
import engine.v2.moves.rules.MoveRuleWhitePawnAttack
import engine.v2.moves.rules.MoveRuleWhitePawnPush
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

private class PawnMoveGenerator(context: MoveGenCtx) : AbstractMoveGenerator<MoveGenCtx>(
    context, listOf(
        MoveRuleWhitePawnPush(context),
        MoveRuleWhitePawnAttack(context),
        MoveRuleBlackPawnPush(context),
        MoveRuleBlackPawnAttack(context)
    )
)

internal class PawnMovesTest {
    private val blackToMove = MoveGenCtx(
        GameData(
            BitBoard(),
            PieceColor.BLACK
        )
    )
    private val whiteToMove = MoveGenCtx(
        GameData(
            BitBoard(),
            PieceColor.WHITE
        )
    )

    @Test
    fun shouldRun() {
        assertTrue("shouldRun flips based on turn"){
            MoveRuleWhitePawnPush(whiteToMove).shouldRun()
            !MoveRuleWhitePawnPush(blackToMove).shouldRun()
            MoveRuleWhitePawnAttack(whiteToMove).shouldRun()
            !MoveRuleWhitePawnAttack(blackToMove).shouldRun()
            MoveRuleBlackPawnPush(blackToMove).shouldRun()
            !MoveRuleBlackPawnPush(whiteToMove).shouldRun()
            MoveRuleBlackPawnAttack(blackToMove).shouldRun()
            !MoveRuleBlackPawnAttack(whiteToMove).shouldRun()
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
        MoveTest(PawnMoveGenerator(MoveGenCtx(GameData(
            FenToBitBoard(Fen("8/8/8/8/8/pppppppp/PPPPPPPP/8 w - - 0 1")).output,
            PieceColor.WHITE
        ))), 0xff0000UL)
    }

    @Test
    fun `empty board white pawns rank 2 and rank 3`() {
        MoveTest(PawnMoveGenerator(MoveGenCtx(GameData(
            FenToBitBoard(Fen("8/8/8/8/8/PPPPPPPP/PPPPPPPP/8 w - - 0 1")).output,
            PieceColor.WHITE
        ))), 0xff000000UL)
    }

    @Test
    fun `empty board white pawn cannot attack push piece`() {
        MoveTest(PawnMoveGenerator(MoveGenCtx(GameData(
            FenToBitBoard(Fen("8/8/8/3n4/3P4/8/8/8 w - - 0 1")).output,
            PieceColor.WHITE
        ))), 0UL)
    }

    @Test
    fun `white pawn attack northwest`() {
        MoveTest(PawnMoveGenerator(MoveGenCtx(GameData(
            FenToBitBoard(Fen("8/8/8/2n5/3P4/8/8/8 w - - 0 1")).output,
            PieceColor.WHITE
        ))), 0xc00000000UL)
    }

    @Test
    fun `white pawn attack northeast`() {
        MoveTest(PawnMoveGenerator(MoveGenCtx(GameData(
            FenToBitBoard(Fen("8/8/8/4n3/3P4/8/8/8 w - - 0 1")).output,
            PieceColor.WHITE
        ))), 0x1800000000UL)
    }

    @Test
    fun `white pawn fork`() {
        MoveTest(PawnMoveGenerator(MoveGenCtx(GameData(
            FenToBitBoard(Fen("8/8/8/2n1n3/3P4/8/8/8 w - - 0 1")).output,
            PieceColor.WHITE
        ))), 0x1c00000000UL)
    }

    @Test
    fun `white pawn west edge`() {
        MoveTest(PawnMoveGenerator(MoveGenCtx(GameData(
            FenToBitBoard(Fen("8/8/8/nn6/P7/8/8/8 w - - 0 1")).output,
            PieceColor.WHITE
        ))), 0x200000000UL)
    }
}