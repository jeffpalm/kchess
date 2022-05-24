package engine.adapter

import engine.Square
import engine.Compass
import engine.Sq
import engine.move.PseudoMove

class WordPairToKnightPseudoMoves(input: Pair<ULong, ULong>, char: Char) : Adapter<Pair<ULong, ULong>, List<PseudoMove>>(input, char){
    override fun adapt(input: Pair<ULong, ULong>, context: Any?): List<PseudoMove> {
        val moves: MutableList<PseudoMove> = mutableListOf()
        val startSquares = WordToSquareIndices(input.first).output

        for (squareIndex in startSquares) {
            val fromSquare = Square[squareIndex]
            val targetSquares = input.second and Compass.knightMoveTargets(Sq[squareIndex])
            val targetSquareIndices = WordToSquareIndices(targetSquares).output
            for (targetSquareIndex in targetSquareIndices) {
                val targetSquare = Square[targetSquareIndex]
                moves.add(PseudoMove(fromSquare, targetSquare, context as Char))
            }
        }

        return moves
    }
}