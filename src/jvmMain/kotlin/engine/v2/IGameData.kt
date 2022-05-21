package engine.v2

import engine.Color

interface IGameData {
    operator fun component1(): BitBoard {
        return board
    }

    operator fun component2(): Color {
        return turn
    }

    operator fun component3(): String {
        return castleAvail
    }

    operator fun component4(): String {
        return enPassantTarget
    }

    operator fun component5(): Int {
        return halfMoveClock
    }

    operator fun component6(): Int {
        return fullMoveClock
    }

    val board: BitBoard
    val turn: Color
    val castleAvail: String
    val enPassantTarget: String
    val halfMoveClock: Int
    val fullMoveClock: Int
    fun copy(): IGameData
}