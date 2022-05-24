package engine.adapter

import engine.BitBoard
import engine.Sq

class BoardSquaresToBitBoard(boardRep: Map<Byte, Char?>) : Adapter<Map<Byte, Char?>, BitBoard>(boardRep) {
    override fun adapt(input: Map<Byte, Char?>, context: Any?): BitBoard {
        val board = BitBoard(true)
        for ((idx, char) in input) {
            populateBitBoard(idx, char, board)
        }
        return board
    }

    private fun populateBitBoard(idx: Byte, char: Char?, board: BitBoard) = when (char) {
        'P' -> board.whitePawns = board.whitePawns or Sq[idx]
        'N' -> board.whiteKnights = board.whiteKnights or Sq[idx]
        'B' -> board.whiteBishops = board.whiteBishops or Sq[idx]
        'R' -> board.whiteRooks = board.whiteRooks or Sq[idx]
        'Q' -> board.whiteQueens = board.whiteQueens or Sq[idx]
        'K' -> board.whiteKing = board.whiteKing or Sq[idx]
        'p' -> board.blackPawns = board.blackPawns or Sq[idx]
        'n' -> board.blackKnights = board.blackKnights or Sq[idx]
        'b' -> board.blackBishops = board.blackBishops or Sq[idx]
        'r' -> board.blackRooks = board.blackRooks or Sq[idx]
        'q' -> board.blackQueens = board.blackQueens or Sq[idx]
        'k' -> board.blackKing = board.blackKing or Sq[idx]
        null -> null
        else -> throw IllegalArgumentException("Invalid character: $char")
    }
}