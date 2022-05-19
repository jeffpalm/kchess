package engine.v2.adapters

class BinaryStringToWord(input: String): Adapter<String, ULong>(input) {
    override fun adapt(input: String, context: Any?): ULong {
        return input.toULong(2)
    }
}