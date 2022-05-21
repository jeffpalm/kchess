package engine.v2.adapters

import engine.v2.SquareMap
import engine.v2.moves.PseudoMove

class BitBitsPairToPseudoMoves(input: Pair<ULong, ULong>) : Adapter<Pair<ULong, ULong>, List<PseudoMove>>(input) {
    override fun adapt(input: Pair<ULong, ULong>, context: Any?): List<PseudoMove> {
        if (input.first.countOneBits() > 1) throw IllegalArgumentException("First word in pair must only contain 1 bit")
        if (input.first == 0UL || input.second == 0UL) return listOf()

        val output: MutableList<PseudoMove> = mutableListOf()
        val startSquare = SquareMap[input.first]
        val targetSquares = WordToSquareIndices(input.second).output

        for (targetIdx in targetSquares) {
            val targetSquare = SquareMap[targetIdx]
            if (startSquare != targetSquare) {
                output.add(PseudoMove(startSquare, SquareMap[targetIdx]))
            }
        }
        return output
    }
}