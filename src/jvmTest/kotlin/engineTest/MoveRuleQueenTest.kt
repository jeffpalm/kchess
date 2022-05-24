package engineTest

import engine.*
import engine.adapter.BoardSquaresToBitBoard
import engine.adapter.FenToBitBoard
import engine.adapter.WordToBoardSquares
import engine.move.AbstractMoveGenerator
import engine.move.MoveGenCtx
import engine.move.rules.MoveRuleQueen
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

private class QueenMoveGenerator(context: MoveGenCtx) : AbstractMoveGenerator(
    context, listOf(
        MoveRuleQueen()
    ),
    listOf()
)

internal class MoveRuleQueenTest {
    private val queenOnD4 = MoveGenCtx(
        GameData(
            BoardSquaresToBitBoard(WordToBoardSquares(Sq.d4, 'q').output).output,
            Color.BLACK,
        )
    )
    private val queenOnA1 = MoveGenCtx(
        GameData(
            BoardSquaresToBitBoard(WordToBoardSquares(Sq.a1, 'q').output).output,
            Color.BLACK,
        )
    )
    private val queenOnH1 = MoveGenCtx(
        GameData(
            BoardSquaresToBitBoard(WordToBoardSquares(Sq.h1, 'q').output).output,
            Color.BLACK,
        )
    )
    private val queenOnH8 = MoveGenCtx(
        GameData(
            BoardSquaresToBitBoard(WordToBoardSquares(Sq.h8, 'q').output).output,
            Color.BLACK,
        )
    )
    private val queenOnA8 = MoveGenCtx(
        GameData(
            BoardSquaresToBitBoard(WordToBoardSquares(Sq.a8, 'q').output).output,
            Color.BLACK,
        )
    )

    @Test
    fun shouldRun() {
        val boardWithoutQueen = BitBoard()
        boardWithoutQueen.whiteQueens = 0UL
        assertTrue("shouldRun") {
            MoveRuleQueen().shouldRun(MoveGenCtx(Game().data))
            !MoveRuleQueen().shouldRun(MoveGenCtx(GameData(boardWithoutQueen, Color.WHITE)))
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
        var moves = QueenMoveGenerator(
            MoveGenCtx(
                GameData(
                    FenToBitBoard(Fen("8/8/8/2ppp3/2pqp3/2ppp3/8/8 b - - 0 1")).output,
                    Color.BLACK
                )
            )
        ).execute()
        assertEquals(0, moves.count())
        moves = QueenMoveGenerator(
            MoveGenCtx(
                GameData(
                    FenToBitBoard(Fen("8/8/8/2PPP3/2PQP3/2PPP3/8/8 b - - 0 1")).output,
                    Color.WHITE
                )
            )
        ).execute()
        assertEquals(0, moves.count())
    }

    @Test
    fun `queen on d4 surrounded by enemy pawns`() {
        MoveTest(
            QueenMoveGenerator(
                MoveGenCtx(
                    GameData(
                        FenToBitBoard(Fen("8/8/8/2PPP3/2PqP3/2PPP3/8/8 b - - 0 1")).output,
                        Color.BLACK
                    )
                )
            ), 0x1C141C0000UL
        )
        MoveTest(
            QueenMoveGenerator(
                MoveGenCtx(
                    GameData(
                        FenToBitBoard(Fen("8/8/8/2ppp3/2pQp3/2ppp3/8/8 b - - 0 1")).output,
                        Color.WHITE
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
                        Color.BLACK
                    )
                )
            ), 0x1021C141C0000UL
        )
        MoveTest(
            QueenMoveGenerator(
                MoveGenCtx(
                    GameData(
                        FenToBitBoard(Fen("8/8/8/3pp3/2pQp3/2ppp3/8/8 b - - 0 1")).output,
                        Color.WHITE
                    )
                )
            ), 0x1021C141C0000UL
        )
    }

    @Test
    fun `perft Position 4 scenario`() {
        MoveTest(
            QueenMoveGenerator(
                MoveGenCtx(
                    GameData(
                        FenToBitBoard(Fen("r3k2r/Pppp1ppp/1b3nbN/nPP5/BB2P3/q4N2/Pp1P2PP/R2Q1RK1 b kq - 0 1")).output,
                        Color.BLACK
                    )
                )
            ),
            0x33e0100UL
        )
    }
}


