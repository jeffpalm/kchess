package engine.v2.adapters

import engine.v2.Constants
import engine.v2.SquareMap

class WordToOneBitWords(word: ULong) : Adapter<ULong, List<ULong>>(word) {
    override fun adapt(input: ULong): List<ULong> {
        val squareIndices = WordToSquareIndices(input).output
        return squareIndices.map { idx -> Constants.SquareWords[SquareMap.values()[idx].name] }
    }
}