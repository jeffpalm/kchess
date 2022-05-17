package engine.v2.adapters

import engine.v2.Constants

class WordToBoardRep(input: ULong, private val pieceToFill: Char? = null) : Adapter<ULong, MutableMap<Byte, Char?>>(input) {
    override fun adapt(input: ULong): MutableMap<Byte, Char?> {
        val boardRep = Constants.boardRep.toMutableMap()
        val charArray = input.toString(2).toCharArray().reversedArray()

        for ((idx, char) in charArray.withIndex()) {
            if (char == '1') {
                boardRep[idx.toByte()] = pieceToFill ?: 'X'
            }
        }

        return boardRep
    }
}