package engine.v2.moves

import engine.SquareMap
import engine.v2.Square

data class PseudoMove(val from: SquareMap, val to: SquareMap, val piece: Char) {
    val fromBit = Square[from.name]
    val toBit = Square[to.name]
    val asWord: ULong = Square[from.name] or Square[from.name]

    fun asString(): String {
        return "${from.name}${to.name}"
    }
    fun asPair(): Pair<SquareMap, SquareMap> {
        return Pair(from, to)
    }
}
