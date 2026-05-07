package engine.adapter

class WordToSquareIndices(word: ULong) : Adapter<ULong, List<Int>>(word) {
    override fun adapt(input: ULong, context: Any?): List<Int> {
        if (input == 0UL) return emptyList()
        val out = ArrayList<Int>(input.countOneBits())
        var w = input
        while (w != 0UL) {
            out.add(w.countTrailingZeroBits())
            w = w and (w - 1UL)
        }
        return out
    }
}
