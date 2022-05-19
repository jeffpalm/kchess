package engine.v2.adapters

import engine.v2.Constants
import engine.v2.SquareMap

class SquareIndexToOneBitWord(index: Int) : Adapter<Int, ULong>(index) {
    override fun adapt(input: Int, context: Any?): ULong {
        if (input < 0 || input > 63) throw IllegalArgumentException("Invalid square index: $input")
        return Constants.SquareWords[SquareMap.values()[input].name]
    }
}