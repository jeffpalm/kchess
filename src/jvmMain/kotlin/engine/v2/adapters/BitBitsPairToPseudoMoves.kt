package engine.v2.adapters

import engine.SquareMap
import engine.v2.BitBoard
import engine.v2.moves.PseudoMove

class BitBitsPairToPseudoMoves(input: Pair<ULong, ULong>, char: Char) : Adapter<Pair<ULong, ULong>, List<PseudoMove>>(input, char) {
    override fun adapt(input: Pair<ULong, ULong>, context: Any?): List<PseudoMove> {
        if (input.first.countOneBits() > 1) throw IllegalArgumentException("First word in pair must only contain 1 bit")
        if (input.first == 0UL || input.second == 0UL) return listOf()

        val output: MutableList<PseudoMove> = mutableListOf()
        val startSquare = SquareMap[input.first]
        var targetSquares = WordToSquareIndices(input.second).output

        val targetPromos = input.second and BitBoard.promoSquares(context as Char)

        if (targetPromos != 0UL) {
            val targetPromoIndices = WordToSquareIndices(targetPromos).output
            for (toSquare in targetPromoIndices) {
                targetSquares = targetSquares.filter { it != toSquare }
                output.addAll(PseudoMove.getPromoMoves(startSquare, SquareMap[toSquare], context))
            }
        }

        for (targetIdx in targetSquares) {
            val targetSquare = SquareMap[targetIdx]
            if (startSquare != targetSquare) {
                output.add(PseudoMove(startSquare, SquareMap[targetIdx], context as Char))
            }
        }
        return output
    }

}
