package engine.v2.adapters

import engine.v2.SquareMap
import engine.v2.moves.PseudoMove

class WordPairToPseudoMoves(wordPair: Pair<ULong, ULong>) : Adapter<Pair<ULong, ULong>, List<PseudoMove>>(wordPair) {
    override fun adapt(input: Pair<ULong, ULong>): List<PseudoMove> {
        val fromSquares = WordToSquareIndices(input.first).output
        val toSquares = WordToSquareIndices(input.second).output

        return fromSquares.zip(toSquares)
            .map { PseudoMove(SquareMap.values()[it.first], SquareMap.values()[it.second]) }
    }
}