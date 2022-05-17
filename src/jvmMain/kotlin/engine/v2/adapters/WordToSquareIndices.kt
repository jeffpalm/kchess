package engine.v2.adapters

class WordToSquareIndices(word: ULong) : Adapter<ULong, List<Int>>(word) {
    override fun adapt(input: ULong): List<Int> {
        return input.toString(2).reversed().toCharArray().withIndex().map { (index, char) ->
            if (char == '1') index else -1
        }.filter { it != -1 }
    }

}