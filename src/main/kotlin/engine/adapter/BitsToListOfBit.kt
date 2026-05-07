package engine.adapter

class BitsToListOfBit(word: ULong) : Adapter<ULong, List<ULong>>(word) {
    override fun adapt(input: ULong, context: Any?): List<ULong> {
        if (input == 0UL) return emptyList()
        val out = ArrayList<ULong>(input.countOneBits())
        var w = input
        while (w != 0UL) {
            val low = w.takeLowestOneBit()
            out.add(low)
            w = w xor low
        }
        return out
    }
}
