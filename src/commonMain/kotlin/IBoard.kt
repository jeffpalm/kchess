interface IBoard {
    fun getPiece(x: Int, y: Int): Piece?
    fun setPiece(x: Int, y: Int, piece: Piece?)
    fun getKing(pieceColor: PieceColor): Square?
    fun getSquaresByPieceColor(pieceColor: PieceColor): List<Square>
    fun isPathClear(move: PotentialMove): Boolean
    fun getPotentialMoveSquaresByPiece(square: Square, piece: Piece): List<PotentialMove>
}