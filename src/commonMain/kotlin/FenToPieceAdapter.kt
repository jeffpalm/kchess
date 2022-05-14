class FenToPieceAdapter(key: Char) {
    val piece: Piece? = when (key) {
        'P' -> Pawn(PieceColor.WHITE)
        'p' -> Pawn(PieceColor.BLACK)
        'N' -> Knight(PieceColor.WHITE)
        'n' -> Knight(PieceColor.BLACK)
        'B' -> Bishop(PieceColor.WHITE)
        'b' -> Bishop(PieceColor.BLACK)
        'R' -> Rook(PieceColor.WHITE)
        'r' -> Rook(PieceColor.BLACK)
        'Q' -> Queen(PieceColor.WHITE)
        'q' -> Queen(PieceColor.BLACK)
        'K' -> King(PieceColor.WHITE)
        'k' -> King(PieceColor.BLACK)
        else -> null
    }
}