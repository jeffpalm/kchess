package engine

interface IBitBoardPieces {
    val whitePawns: ULong
    val whiteKnights: ULong
    val whiteBishops: ULong
    val whiteRooks: ULong
    val whiteQueens: ULong
    val whiteKing: ULong
    val blackPawns: ULong
    val blackKnights: ULong
    val blackBishops: ULong
    val blackRooks: ULong
    val blackQueens: ULong
    val blackKing: ULong
    fun pieceList(): List<Pair<Char, ULong>>
}