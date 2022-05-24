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
        'P' -> board.wPawns = board.wPawns or Sq[idx]
        'N' -> board.wKnights = board.wKnights or Sq[idx]
        'B' -> board.wBishops = board.wBishops or Sq[idx]
        'R' -> board.wRooks = board.wRooks or Sq[idx]
        'Q' -> board.wQueens = board.wQueens or Sq[idx]
        'K' -> board.wKing = board.wKing or Sq[idx]
        'p' -> board.bPawns = board.bPawns or Sq[idx]
        'n' -> board.bKnights = board.bKnights or Sq[idx]
        'b' -> board.bBishops = board.bBishops or Sq[idx]
        'r' -> board.bRooks = board.bRooks or Sq[idx]
        'q' -> board.bQueens = board.bQueens or Sq[idx]
        'k' -> board.bKing = board.bKing or Sq[idx]
        null -> null
        else -> throw IllegalArgumentException("Invalid character: $char")
    }
}