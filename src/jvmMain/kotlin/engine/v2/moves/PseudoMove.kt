package engine.v2.moves

import engine.v2.SquareMap

data class PseudoMove(val fromSquare: SquareMap, val toSquare: SquareMap) {
    fun asString(): String {
        return "${fromSquare.name}${toSquare.name}"
    }
}
