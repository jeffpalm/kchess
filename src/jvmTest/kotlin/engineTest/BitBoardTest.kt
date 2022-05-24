package engineTest

import engine.*
import engine.adapter.FenToBitBoard
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class BitBoardTest {
    @Test
    fun `rayMoves all sliding directions from d4`(){
        val board = BitBoard()
        var wResult: ULong = 0UL
        var bResult: ULong = 0UL

        for(direction in Direction.sliding) {
            wResult = wResult or board.rayMoves(Sq.d4, direction, Color.WHITE)
            bResult = bResult or board.rayMoves(Sq.d4, direction, Color.BLACK)
        }

        assertEquals(0x492a1cf71c0000UL, wResult, "Expected 0x492a1cf71c0000UL\nReceived: 0x${wResult.toString(16)}UL")
        assertEquals(0x2a1cf71c2a00UL, bResult, "Expected 0x2a1cf71c2a00UL\nReceived: 0x${bResult.toString(16)}UL")
    }

    @Test
    fun `rayMoves all sliding directions from h4`(){
        val board = BitBoard()
        var wResult: ULong = 0UL
        var bResult: ULong = 0UL

        for(direction in Direction.sliding) {
            wResult = wResult or board.rayMoves(Sq.h4, direction, Color.WHITE)
            bResult = bResult or board.rayMoves(Sq.h4, direction, Color.BLACK)
        }

        assertEquals(0x90a0c07fc00000UL, wResult, "Expected 0x90a0c07fc00000UL\nReceived: 0x${wResult.toString(16)}UL")
        assertEquals(0xa0c07fc0a000UL, bResult, "Expected 0xa0c07fc0a000UL\nReceived: 0x${bResult.toString(16)}UL")
    }

    @Test
    fun `rayMoves all sliding directions from a4`(){
        val board = BitBoard()
        var wResult: ULong = 0UL
        var bResult: ULong = 0UL

        for(direction in Direction.sliding) {
            wResult = wResult or board.rayMoves(Sq.a4, direction, Color.WHITE)
            bResult = bResult or board.rayMoves(Sq.a4, direction, Color.BLACK)
        }

        assertEquals(0x90503fe030000UL, wResult, "Expected 0x90503fe030000UL\nReceived: 0x${wResult.toString(16)}UL")
        assertEquals(0x503fe030500UL, bResult, "Expected 0x503fe030500UL\nReceived: 0x${bResult.toString(16)}UL")
    }

    @Test
    fun `rayMoves Perft Position 4`() {
        val board = FenToBitBoard(Fen("r3k2r/Pppp1ppp/1b3nbN/nPP5/BB2P3/q4N2/Pp1P2PP/R2Q1RK1 b kq - 0 1")).output

        val result = board.rayMoves(Sq.a3, Direction.N, Color.BLACK)

        assertEquals(0x1000000UL, result)
    }
    @Test
    fun `rayAttack all sliding directions from d4`() {
        val board = BitBoard()
        var wResult: ULong = 0UL
        var bResult: ULong = 0UL

        for (direction in Direction.sliding) {
            wResult = wResult or board.rayAttack(Sq.d4, direction, Color.WHITE)
            bResult = bResult or board.rayAttack(Sq.d4, direction, Color.BLACK)
        }
        assertEquals(0x49000000000000UL, wResult, "Expected 0x49000000000000UL\nReceived: 0x${wResult.toString(16)}UL")
        assertEquals(0x2a00UL, bResult, "Expected 0x2a00UL\nReceived: 0x${bResult.toString(16)}UL")
    }
    @Test
    fun `rayAttack fake queen position friendly pieces`() {
        val board = FenToBitBoard(Fen("8/1PPPPP2/1P3P2/1P1Q1P2/1P3P2/1PPPPP2/8/8 w - - 0 1")).output
        var wResult: ULong = 0UL
        var bResult: ULong = 0UL

        for (direction in Direction.sliding) {
            wResult = wResult or board.rayAttack(Sq.d5, direction, Color.WHITE)
            bResult = bResult or board.rayAttack(Sq.d5, direction, Color.BLACK)
        }
        assertEquals(0UL, wResult, "Expected 0UL\nReceived: 0x${wResult.toString(16)}UL")
        assertEquals(0x2a0022002a0000UL, bResult, "Expected 0x2a0022002a0000UL\nReceived: 0x${bResult.toString(16)}UL")
    }

    @Test
    fun `pieceList test`() {
        val board = BitBoard()
        val pieces = board.pieceList()

        for ((piece, bitBoard) in pieces) {
            when (piece) {
                Piece.wPawn -> assertEquals(BitBoard.Companion.StartPosition.P, bitBoard)
                Piece.bPawn -> assertEquals(BitBoard.Companion.StartPosition.p, bitBoard)
                Piece.wKnight -> assertEquals(BitBoard.Companion.StartPosition.N, bitBoard)
                Piece.bKnight -> assertEquals(BitBoard.Companion.StartPosition.n, bitBoard)
                Piece.wBishop -> assertEquals(BitBoard.Companion.StartPosition.B, bitBoard)
                Piece.bBishop -> assertEquals(BitBoard.Companion.StartPosition.b, bitBoard)
                Piece.wRook -> assertEquals(BitBoard.Companion.StartPosition.R, bitBoard)
                Piece.bRook -> assertEquals(BitBoard.Companion.StartPosition.r, bitBoard)
                Piece.wQueen -> assertEquals(BitBoard.Companion.StartPosition.Q, bitBoard)
                Piece.bQueen -> assertEquals(BitBoard.Companion.StartPosition.q, bitBoard)
                Piece.wKing -> assertEquals(BitBoard.Companion.StartPosition.K, bitBoard)
                Piece.bKing -> assertEquals(BitBoard.Companion.StartPosition.k, bitBoard)
            }
        }
    }

    @Test
    fun `test makeMove`() {
        val board = BitBoard()

        board.makeMove(Sq.e2 to Sq.e4, Piece.wPawn)

        assertEquals(0x1000ef00UL, board.wPawns)

        board.makeMove(Sq.e7 to Sq.e5, Piece.bPawn)

        assertEquals(0xef001000000000UL, board.bPawns)

        board.makeMove(Sq.f1 to Sq.c4, Piece.wBishop)

        assertEquals(0x4000004UL, board.wBishops)

        board.makeMove(Sq.f8 to Sq.c5, Piece.bBishop)

        assertEquals(0x400000400000000UL, board.bBishops)

        board.makeMove(Sq.g8 to Sq.f6, Piece.bKnight)

        assertEquals(0x200200000000000UL, board.bKnights)

        board.makeMove(Sq.h8 to Sq.f8, Piece.bRook)

        assertEquals(0x2100000000000000UL, board.bRooks)
    }
}