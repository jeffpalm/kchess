class Fen(private val fen: String = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1") {
    val boardRepresentation: String = fen.split(" ")[0]
    val sideToMove: String = fen.split(" ")[1]
    val castlingAvailability: String = fen.split(" ")[2]
    val enPassantTarget: String = fen.split(" ")[3]
    val halfMoveClock: Byte = fen.split(" ")[4].toByte()
    val fullMoveClock: Byte = fen.split(" ")[5].toByte()

    fun toBoard(): Map<Byte, Map<Byte, Square>> {
        return FenToBoardAdapter(this).board
    }
}