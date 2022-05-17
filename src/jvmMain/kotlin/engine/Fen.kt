package engine

class Fen(fen: String = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1") {
    val boardRepresentation: String
    val sideToMove: String
    val castlingAvailability: String
    val enPassantTarget: String
    val halfMoveClock: Int
    val fullMoveClock: Int
    val string: String = fen

    init {
        val fenArray = fen.split(" ")
        boardRepresentation = fenArray[0]
        sideToMove = fenArray[1]
        castlingAvailability = fenArray[2]
        enPassantTarget = fenArray[3]
        halfMoveClock = fenArray[4].toInt()
        fullMoveClock = fenArray[5].toInt()
    }
}