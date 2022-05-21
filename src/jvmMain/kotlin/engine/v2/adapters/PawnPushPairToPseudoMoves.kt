package engine.v2.adapters

import engine.SquareMap
import engine.v2.BitBoard
import engine.v2.moves.PseudoMove

class PawnPushPairToPseudoMoves(wordPair: Pair<ULong, ULong>, piece: Char) :
    Adapter<Pair<ULong, ULong>, List<PseudoMove>>(wordPair, piece) {
    override fun adapt(input: Pair<ULong, ULong>, context: Any?): List<PseudoMove> {
        var fromSquares = WordToSquareIndices(input.first).output
        var toSquares = WordToSquareIndices(input.second).output

        val targetPromos = input.second and BitBoard.promoSquares(context as Char)
        if (targetPromos == 0UL) {
            return getPushMoves(fromSquares, toSquares, context as Char)
        }

        val output: MutableList<PseudoMove> = mutableListOf()

        val targetPromoIndices = WordToSquareIndices(targetPromos).output

        for (toSquare in targetPromoIndices) {
            val toSquaresIdx = toSquares.indexOf(toSquare)
            val fromSquare = fromSquares[toSquaresIdx]
            fromSquares = fromSquares.filter { it != fromSquare }
            toSquares = toSquares.filter { it != toSquare }
            output.addAll(PseudoMove.getPromoMoves(SquareMap[fromSquare], SquareMap[toSquare], context as Char))
        }

        output.addAll(getPushMoves(fromSquares, toSquares, context as Char))

        return output
    }

    private fun getPushMoves(from: List<Int>, to: List<Int>, char: Char): List<PseudoMove> =  from.zip(to)
        .map { PseudoMove(SquareMap[it.first], SquareMap[it.second], char) }
        .filter { it.fromBit != it.toBit }

}