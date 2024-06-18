package engine.adapter

import engine.Board
import engine.IBitBoardPieces
import engine.Sq

private typealias BoardSquares = MutableMap<Byte, Char?>

class BitBoardToBoardSquares(bitBoard: IBitBoardPieces) : Adapter<IBitBoardPieces, BoardSquares>(bitBoard) {
    override fun adapt(input: IBitBoardPieces, context: Any?): BoardSquares {
        val board: BoardSquares = Board.emptySquares.toMutableMap()
        for ((char, word) in input.pieceList()) {
            populateBoardSquares(word, board, char)
        }
        return board
    }

    private fun populateBoardSquares(word: ULong, board: BoardSquares, char: Char) =
        BitsToListOfBit(word).output.forEach { bit -> board[Sq.IdxByte[bit]] = char }
}