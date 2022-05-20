package engine.v2.adapters

import engine.v2.Constants
import engine.v2.IBitBoardPieces
import engine.v2.Square

private typealias BoardSquares = MutableMap<Byte, Char?>

class BitBoardToBoardSquares(bitBoard: IBitBoardPieces) : Adapter<IBitBoardPieces, BoardSquares>(bitBoard) {
    override fun adapt(input: IBitBoardPieces, context: Any?): BoardSquares {
        val board: BoardSquares = Constants.boardRep.toMutableMap()
        for ((char, word) in input.pieceList()) {
            populateBoardSquares(word, board, char)
        }
        return board
    }

    private fun populateBoardSquares(word: ULong, board: BoardSquares, char: Char) =
        BitsToListOfBit(word).output.forEach { bit -> board[Square.IdxByte[bit]] = char }
}