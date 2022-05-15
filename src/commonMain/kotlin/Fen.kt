class Fen(fen: String = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1") {
    val boardRepresentation: String = fen.split(" ")[0]
    val sideToMove: String = fen.split(" ")[1]
    val castlingAvailability: String = fen.split(" ")[2]
    val enPassantTarget: String = fen.split(" ")[3]
    val halfMoveClock: Int = fen.split(" ")[4].toInt()
    val fullMoveClock: Int = fen.split(" ")[5].toInt()
    val string: String = fen

    fun toBoard(): Map<Int, Map<Int, Square>> {
        return FenToBoardAdapter(this).board
    }
}