package engine.v2.adapters

import engine.SquareMap
import engine.v2.moves.PseudoMove

class BitsBitsPairToPseudoMoves(wordPair: Pair<ULong, ULong>, piece: Char) : Adapter<Pair<ULong, ULong>, List<PseudoMove>>(wordPair, piece) {
    override fun adapt(input: Pair<ULong, ULong>, context: Any?): List<PseudoMove> {
        val fromSquares = WordToSquareIndices(input.first).output
        val toSquares = WordToSquareIndices(input.second).output

        return fromSquares.zip(toSquares)
            .map { PseudoMove(SquareMap[it.first], SquareMap[it.second], context as Char) }
            .filter { it.fromBit != it.toBit }
    }
}