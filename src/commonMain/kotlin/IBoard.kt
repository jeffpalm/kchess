interface IBoard {
    fun getPiece(coords: Coords): Piece?
    fun setPiece(coords: Coords, piece: Piece?)
    fun getKing(pieceColor: PieceColor): Square?
    fun getSquaresByPieceColor(pieceColor: PieceColor): List<Square>
    fun isPathClear(move: Movement): Boolean
    fun getPotentialMovesBySquareCoords(coords: Coords): List<Movement>
    fun getSquareNameByCoords(coords: Coords): String
}