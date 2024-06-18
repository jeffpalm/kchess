package engine

interface IBitBoardPieces {
    val wPawns: ULong
    val wKnights: ULong
    val wBishops: ULong
    val wRooks: ULong
    val wQueens: ULong
    val wKing: ULong
    val bPawns: ULong
    val bKnights: ULong
    val bBishops: ULong
    val bRooks: ULong
    val bQueens: ULong
    val bKing: ULong
    fun pieceList(): List<Pair<Char, ULong>>
}