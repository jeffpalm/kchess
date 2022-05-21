package engine.v2.adapters

import engine.SquareMap
import engine.v2.CompassRose
import engine.v2.Square
import engine.v2.moves.PseudoMove

class WordPairToKnightPseudoMoves(input: Pair<ULong, ULong>, char: Char) : Adapter<Pair<ULong, ULong>, List<PseudoMove>>(input, char){
    override fun adapt(input: Pair<ULong, ULong>, context: Any?): List<PseudoMove> {
        val moves: MutableList<PseudoMove> = mutableListOf()
        val startSquares = WordToSquareIndices(input.first).output

        for (squareIndex in startSquares) {
            val fromSquare = SquareMap[squareIndex]
            val targetSquares = input.second and CompassRose.knightMoveTargets(Square[squareIndex])
            val targetSquareIndices = WordToSquareIndices(targetSquares).output
            for (targetSquareIndex in targetSquareIndices) {
                val targetSquare = SquareMap[targetSquareIndex]
                moves.add(PseudoMove(fromSquare, targetSquare, context as Char))
            }
        }

        return moves
    }
}