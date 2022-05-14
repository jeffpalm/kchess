interface IBoard {
    fun getPiece(x: Byte, y: Byte): Piece?
    fun setPiece(x: Byte, y: Byte, piece: Piece?)
    fun getKing(pieceColor: PieceColor): Square?
    fun getSquaresByPieceColor(pieceColor: PieceColor): List<Square>
    fun isPathClear(move: PotentialMove): Boolean
    fun getPotentialMoveSquaresByPiece(square: Square, piece: Piece): List<PotentialMove>
}