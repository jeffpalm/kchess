package engine.v2.adapters

import engine.v2.BitBoard
import engine.v2.Square

class BoardSquaresToBitBoard(boardRep: Map<Byte, Char?>) : Adapter<Map<Byte, Char?>, BitBoard>(boardRep) {
    override fun adapt(input: Map<Byte, Char?>, context: Any?): BitBoard {
        val board = BitBoard(true)
        for ((idx, char) in input) {
            populateBitBoard(idx, char, board)
        }
        return board
    }

    private fun populateBitBoard(idx: Byte, char: Char?, board: BitBoard) {
        if (char == null) return
        val squareWord = Square[idx]
        when (char) {
            'P' -> board.whitePawns = board.whitePawns or squareWord
            'N' -> board.whiteKnights = board.whiteKnights or squareWord
            'B' -> board.whiteBishops = board.whiteBishops or squareWord
            'R' -> board.whiteRooks = board.whiteRooks or squareWord
            'Q' -> board.whiteQueens = board.whiteQueens or squareWord
            'K' -> board.whiteKing = board.whiteKing or squareWord
            'p' -> board.blackPawns = board.blackPawns or squareWord
            'n' -> board.blackKnights = board.blackKnights or squareWord
            'b' -> board.blackBishops = board.blackBishops or squareWord
            'r' -> board.blackRooks = board.blackRooks or squareWord
            'q' -> board.blackQueens = board.blackQueens or squareWord
            'k' -> board.blackKing = board.blackKing or squareWord
            else -> throw IllegalArgumentException("Invalid character: $char")
        }
    }
}