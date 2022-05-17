package engine.v2

interface IGameData {
    operator fun component1(): BitBoard {
        return board
    }

    operator fun component2(): PieceColor {
        return turn
    }

    operator fun component3(): String {
        return castlingAvailability
    }

    operator fun component4(): String {
        return enPassantTarget
    }

    operator fun component5(): Int {
        return halfMoveClock
    }

    operator fun component6(): Int {
        return fullMoveNumber
    }

    val board: BitBoard
    val turn: PieceColor
    val castlingAvailability: String
    val enPassantTarget: String
    val halfMoveClock: Int
    val fullMoveNumber: Int
}