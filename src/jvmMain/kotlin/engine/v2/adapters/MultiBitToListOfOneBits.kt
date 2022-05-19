package engine.v2.adapters

import engine.v2.Constants
import engine.v2.SquareMap

class MultiBitToListOfOneBits(word: ULong) : Adapter<ULong, List<ULong>>(word) {
    override fun adapt(input: ULong, context: Any?): List<ULong> {
        val squareIndices = WordToSquareIndices(input).output
        return squareIndices.map { idx -> Constants.Square[SquareMap.values()[idx].name] }
    }
}