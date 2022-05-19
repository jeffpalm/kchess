package engine.v2.moves

import engine.v2.Square
import engine.v2.SquareMap

data class PseudoMove(val fromSquare: SquareMap, val toSquare: SquareMap) {
    val fromBit = { Square[fromSquare.name] }
    val toBit = { Square[toSquare.name] }
    val asWord: () -> ULong = { Square[fromSquare.name] or Square[fromSquare.name] }

    fun asString(): String {
        return "${fromSquare.name}${toSquare.name}"
    }
}
