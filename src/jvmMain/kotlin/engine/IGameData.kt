package engine

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

    operator fun component4(): Square? {
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
    val enPassantTarget: Square?
    val halfMoveClock: Int
    val fullMoveClock: Int
    fun clone(): IGameData
}