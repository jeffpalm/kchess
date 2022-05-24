package engine.adapter

import engine.BitBoard
import engine.Square
import engine.move.PseudoMove

class BitBitsPairToPseudoMoves(input: Pair<ULong, ULong>, char: Char) : Adapter<Pair<ULong, ULong>, List<PseudoMove>>(input, char) {
    override fun adapt(input: Pair<ULong, ULong>, context: Any?): List<PseudoMove> {
        if (input.first.countOneBits() > 1) throw IllegalArgumentException("First word in pair must only contain 1 bit")
        if (input.first == 0UL || input.second == 0UL) return listOf()

        val output: MutableList<PseudoMove> = mutableListOf()
        val startSquare = Square[input.first]
        var targetSquares = WordToSquareIndices(input.second).output

        val targetPromos = input.second and BitBoard.promoSquares(context as Char)

        if (targetPromos != 0UL) {
            val targetPromoIndices = WordToSquareIndices(targetPromos).output
            for (toSquare in targetPromoIndices) {
                targetSquares = targetSquares.filter { it != toSquare }
                output.addAll(PseudoMove.getPromoMoves(startSquare, Square[toSquare], context))
            }
        }

        for (targetIdx in targetSquares) {
            val targetSquare = Square[targetIdx]
            if (startSquare != targetSquare) {
                output.add(PseudoMove(startSquare, Square[targetIdx], context as Char))
            }
        }
        return output
    }

}
