package engine.v2.adapters

import engine.v2.SquareMap
import engine.v2.moves.PseudoMove

class OneBitWordPairToPseudoMoves(input: Pair<ULong, ULong>) : Adapter<Pair<ULong, ULong>, List<PseudoMove>>(input) {
    override fun adapt(input: Pair<ULong, ULong>): List<PseudoMove> {
        if (input.first.countOneBits() > 1) throw IllegalArgumentException("First word in pair must only contain 1 bit")
        if (input.second == 0UL) return listOf()
        val squareMap = SquareMap.values()
        val output: MutableList<PseudoMove> = mutableListOf()
        val startSquare = squareMap[WordToSquareIndices(input.first).output[0]]
        val targetSquares = WordToSquareIndices(input.second).output

        for (targetIdx in targetSquares) {
            output.add(PseudoMove(startSquare, squareMap[targetIdx]))
        }
        return output
    }
}