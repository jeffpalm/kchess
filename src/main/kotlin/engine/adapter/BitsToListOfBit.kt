package engine.adapter

import engine.Sq

class BitsToListOfBit(word: ULong) : Adapter<ULong, List<ULong>>(word) {
    override fun adapt(input: ULong, context: Any?): List<ULong> {
        val squareIndices = WordToSquareIndices(input).output
        return squareIndices.map { idx -> Sq[idx] }
    }
}