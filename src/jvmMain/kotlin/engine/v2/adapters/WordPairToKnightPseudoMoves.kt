package engine.v2.adapters

import engine.v2.CompassRose
import engine.v2.Constants
import engine.v2.SquareMap
import engine.v2.moves.PseudoMove

class WordPairToKnightPseudoMoves(input: Pair<ULong, ULong>) : Adapter<Pair<ULong, ULong>, List<PseudoMove>>(input){
    override fun adapt(input: Pair<ULong, ULong>, context: Any?): List<PseudoMove> {
        val moves: MutableList<PseudoMove> = mutableListOf()
        val startSquares = WordToSquareIndices(input.first).output

        for (squareIndex in startSquares) {
            val fromSquare = SquareMap.values()[squareIndex]
            val fromSquareWord = Constants.Square[fromSquare.name]
            val targetSquares = input.second and CompassRose.knightMoveTargets(fromSquareWord)
            val targetSquareIndices = WordToSquareIndices(targetSquares).output
            for (targetSquareIndex in targetSquareIndices) {
                val targetSquare = SquareMap.values()[targetSquareIndex]
                moves.add(PseudoMove(fromSquare, targetSquare))
            }
        }

        return moves
    }
}