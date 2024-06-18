package engine.adapter

import engine.Board

class WordToBoardSquares(input: ULong, pieceToFill: Char? = null) : Adapter<ULong, MutableMap<Byte, Char?>>(input, pieceToFill) {
    override fun adapt(input: ULong, context: Any?): MutableMap<Byte, Char?> {
        val boardRep = Board.emptySquares.toMutableMap()
        val charArray = input.toString(2).toCharArray().reversedArray()

        for ((idx, char) in charArray.withIndex()) {
            if (char == '1') {
                boardRep[idx.toByte()] = context as Char? ?: 'X'
            }
        }

        return boardRep
    }
}