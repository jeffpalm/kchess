package engine.v2.adapters

import engine.v2.Constants

class WordToBoardSquares(input: ULong, private val pieceToFill: Char? = null) : Adapter<ULong, MutableMap<Byte, Char?>>(input, pieceToFill) {
    override fun adapt(input: ULong, context: Any?): MutableMap<Byte, Char?> {
        val boardRep = Constants.boardRep.toMutableMap()
        val charArray = input.toString(2).toCharArray().reversedArray()

        for ((idx, char) in charArray.withIndex()) {
            if (char == '1') {
                boardRep[idx.toByte()] = context as Char? ?: 'X'
            }
        }

        return boardRep
    }
}