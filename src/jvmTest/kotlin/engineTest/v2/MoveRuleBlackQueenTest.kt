package engineTest.v2

import engine.Fen
import engine.v2.Constants
import engine.v2.GameData
import engine.v2.PieceColor
import engine.v2.adapters.BoardSquaresToBitBoard
import engine.v2.adapters.FenToBitBoard
import engine.v2.adapters.WordToBoardSquares
import engine.v2.moves.AbstractMoveGenerator
import engine.v2.moves.MoveGenCtx
import engine.v2.moves.rules.MoveRuleBlackQueen
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

private class QueenMoveGenerator(context: MoveGenCtx) : AbstractMoveGenerator<MoveGenCtx>(
    context, listOf(
        MoveRuleBlackQueen(context)
    )
)

internal class MoveRuleBlackQueenTest {
    private val queenOnD4 = MoveGenCtx(
        GameData(
            BoardSquaresToBitBoard(WordToBoardSquares(Constants.SquareWords.d4, 'q').output).output,
            PieceColor.BLACK,
        )
    )
    private val queenOnA1 = MoveGenCtx(
        GameData(
            BoardSquaresToBitBoard(WordToBoardSquares(Constants.SquareWords.a1, 'q').output).output,
            PieceColor.BLACK,
        )
    )
    private val queenOnH1 = MoveGenCtx(
        GameData(
            BoardSquaresToBitBoard(WordToBoardSquares(Constants.SquareWords.h1, 'q').output).output,
            PieceColor.BLACK,
        )
    )
    private val queenOnH8 = MoveGenCtx(
        GameData(
            BoardSquaresToBitBoard(WordToBoardSquares(Constants.SquareWords.h8, 'q').output).output,
            PieceColor.BLACK,
        )
    )
    private val queenOnA8 = MoveGenCtx(
        GameData(
            BoardSquaresToBitBoard(WordToBoardSquares(Constants.SquareWords.a8, 'q').output).output,
            PieceColor.BLACK,
        )
    )
    private val whiteToMove = MoveGenCtx(
        GameData(
            BoardSquaresToBitBoard(WordToBoardSquares(Constants.SquareWords.d4, 'q').output).output,
            PieceColor.WHITE,
        )
    )
    private val validTestMove = MoveRuleBlackQueen(queenOnD4)
    private val invalidTestMove = MoveRuleBlackQueen(whiteToMove)

    @Test
    fun shouldRun() {
        assertTrue("shouldRun") {
            validTestMove.shouldRun()
            !invalidTestMove.shouldRun()
        }
    }

    @Test
    fun `queen empty board on d4`() {
        MoveTest(QueenMoveGenerator(queenOnD4), 0x88492A1CF71C2A49UL)
    }

    @Test
    fun `queen empty board on a1`() {
        MoveTest(QueenMoveGenerator(queenOnA1), 0x81412111090503FEUL)
    }

    @Test
    fun `queen empty board on h1`() {
        MoveTest(QueenMoveGenerator(queenOnH1), 0x8182848890A0C07FUL)
    }

    @Test
    fun `queen empty board on h8`() {
        MoveTest(QueenMoveGenerator(queenOnH8), 0x7FC0A09088848281UL)
    }

    @Test
    fun `queen empty board on a8`() {
        MoveTest(QueenMoveGenerator(queenOnA8), 0xFE03050911214181UL)
    }

    @Test
    fun `queen on d4 surrounded by friendly pawns`() {
        val moves = QueenMoveGenerator(
            MoveGenCtx(
                GameData(
                    FenToBitBoard(Fen("8/8/8/2ppp3/2pqp3/2ppp3/8/8 b - - 0 1")).output,
                    PieceColor.BLACK
                )
            )
        ).execute().getMoves()
        assertEquals(0, moves.count())
    }

    @Test
    fun `queen on d4 surrounded by enemy pawns`() {
        MoveTest(
            QueenMoveGenerator(
                MoveGenCtx(
                    GameData(
                        FenToBitBoard(Fen("8/8/8/2PPP3/2PqP3/2PPP3/8/8 b - - 0 1")).output,
                        PieceColor.BLACK
                    )
                )
            ), 0x1C141C0000UL
        )
    }

    @Test
    fun `queen on d4 surrounded by enemy pawns with hole NE`() {
        MoveTest(
            QueenMoveGenerator(
                MoveGenCtx(
                    GameData(
                        FenToBitBoard(Fen("8/8/8/3PP3/2PqP3/2PPP3/8/8 b - - 0 1")).output,
                        PieceColor.BLACK
                    )
                )
            ), 0x1021C141C0000UL
        )
    }
}


