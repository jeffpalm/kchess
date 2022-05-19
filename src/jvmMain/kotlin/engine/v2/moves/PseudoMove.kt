package engine.v2.moves

import engine.v2.Constants
import engine.v2.SquareMap

data class PseudoMove(val fromSquare: SquareMap, val toSquare: SquareMap) {
    val fromBit = { Constants.SquareWords[fromSquare.name] }
    val toBit = { Constants.SquareWords[toSquare.name] }
    val asWord: () -> ULong = { Constants.SquareWords[fromSquare.name] or Constants.SquareWords[fromSquare.name] }

    fun asString(): String {
        return "${fromSquare.name}${toSquare.name}"
    }
}
