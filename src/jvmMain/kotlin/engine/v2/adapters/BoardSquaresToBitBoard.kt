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

    private fun populateBitBoard(idx: Byte, char: Char?, board: BitBoard) = when (char) {
        'P' -> board.whitePawns = board.whitePawns or Square[idx]
        'N' -> board.whiteKnights = board.whiteKnights or Square[idx]
        'B' -> board.whiteBishops = board.whiteBishops or Square[idx]
        'R' -> board.whiteRooks = board.whiteRooks or Square[idx]
        'Q' -> board.whiteQueens = board.whiteQueens or Square[idx]
        'K' -> board.whiteKing = board.whiteKing or Square[idx]
        'p' -> board.blackPawns = board.blackPawns or Square[idx]
        'n' -> board.blackKnights = board.blackKnights or Square[idx]
        'b' -> board.blackBishops = board.blackBishops or Square[idx]
        'r' -> board.blackRooks = board.blackRooks or Square[idx]
        'q' -> board.blackQueens = board.blackQueens or Square[idx]
        'k' -> board.blackKing = board.blackKing or Square[idx]
        null -> null
        else -> throw IllegalArgumentException("Invalid character: $char")
    }
}