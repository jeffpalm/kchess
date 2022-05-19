package engine.v2.moves

import engine.v2.Constants
import engine.v2.SquareMap

data class PseudoMove(val fromSquare: SquareMap, val toSquare: SquareMap) {
    val fromBit = { Constants.Square[fromSquare.name] }
    val toBit = { Constants.Square[toSquare.name] }
    val asWord: () -> ULong = { Constants.Square[fromSquare.name] or Constants.Square[fromSquare.name] }

    fun asString(): String {
        return "${fromSquare.name}${toSquare.name}"
    }
}
