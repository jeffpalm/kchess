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
            wResult = wResult or board.rayMoves(Square.d4, direction, Color.WHITE)
            bResult = bResult or board.rayMoves(Square.d4, direction, Color.BLACK)
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
            wResult = wResult or board.rayMoves(Square.h4, direction, Color.WHITE)
            bResult = bResult or board.rayMoves(Square.h4, direction, Color.BLACK)
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
            wResult = wResult or board.rayMoves(Square.a4, direction, Color.WHITE)
            bResult = bResult or board.rayMoves(Square.a4, direction, Color.BLACK)
        }

        assertEquals(0x90503fe030000UL, wResult, "Expected 0x90503fe030000UL\nReceived: 0x${wResult.toString(16)}UL")
        assertEquals(0x503fe030500UL, bResult, "Expected 0x503fe030500UL\nReceived: 0x${bResult.toString(16)}UL")
    }

    @Test
    fun `rayMoves Perft Position 4`() {
        val board = FenToBitBoard(Fen("r3k2r/Pppp1ppp/1b3nbN/nPP5/BB2P3/q4N2/Pp1P2PP/R2Q1RK1 b kq - 0 1")).output

        val result = board.rayMoves(Square.a3, Direction.N, Color.BLACK)

        Board(result).print()
        assertEquals(0x1000000UL, result)
    }
    @Test
    fun `rayAttack all sliding directions from d4`() {
        val board = BitBoard()
        var wResult: ULong = 0UL
        var bResult: ULong = 0UL

        for (direction in Direction.sliding) {
            wResult = wResult or board.rayAttack(Square.d4, direction, Color.WHITE)
            bResult = bResult or board.rayAttack(Square.d4, direction, Color.BLACK)
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
            wResult = wResult or board.rayAttack(Square.d5, direction, Color.WHITE)
            bResult = bResult or board.rayAttack(Square.d5, direction, Color.BLACK)
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
                Piece.whitePawn -> assertEquals(BitBoard.Companion.StartPosition.P, bitBoard)
                Piece.blackPawn -> assertEquals(BitBoard.Companion.StartPosition.p, bitBoard)
                Piece.whiteKnight -> assertEquals(BitBoard.Companion.StartPosition.N, bitBoard)
                Piece.blackKnight -> assertEquals(BitBoard.Companion.StartPosition.n, bitBoard)
                Piece.whiteBishop -> assertEquals(BitBoard.Companion.StartPosition.B, bitBoard)
                Piece.blackBishop -> assertEquals(BitBoard.Companion.StartPosition.b, bitBoard)
                Piece.whiteRook -> assertEquals(BitBoard.Companion.StartPosition.R, bitBoard)
                Piece.blackRook -> assertEquals(BitBoard.Companion.StartPosition.r, bitBoard)
                Piece.whiteQueen -> assertEquals(BitBoard.Companion.StartPosition.Q, bitBoard)
                Piece.blackQueen -> assertEquals(BitBoard.Companion.StartPosition.q, bitBoard)
                Piece.whiteKing -> assertEquals(BitBoard.Companion.StartPosition.K, bitBoard)
                Piece.blackKing -> assertEquals(BitBoard.Companion.StartPosition.k, bitBoard)
            }
        }
    }

    @Test
    fun `test makeMove`() {
        val board = BitBoard()

        board.makeMove(Square.e2 to Square.e4, Piece.whitePawn)

        assertEquals(0x1000ef00UL, board.whitePawns)

        board.makeMove(Square.e7 to Square.e5, Piece.blackPawn)

        assertEquals(0xef001000000000UL, board.blackPawns)

        board.makeMove(Square.f1 to Square.c4, Piece.whiteBishop)

        assertEquals(0x4000004UL, board.whiteBishops)

        board.makeMove(Square.f8 to Square.c5, Piece.blackBishop)

        assertEquals(0x400000400000000UL, board.blackBishops)

        board.makeMove(Square.g8 to Square.f6, Piece.blackKnight)

        assertEquals(0x200200000000000UL, board.blackKnights)

        board.makeMove(Square.h8 to Square.f8, Piece.blackRook)

        assertEquals(0x2100000000000000UL, board.blackRooks)
    }
}