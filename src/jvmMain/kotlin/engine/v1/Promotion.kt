package engine.v1

class Promotion(color: PieceColor, choice: PromotionPiece) {
    val piece: Piece

    init {
        piece = when (choice) {
            PromotionPiece.KNIGHT -> Knight(color)
            PromotionPiece.BISHOP -> Bishop(color)
            PromotionPiece.ROOK -> Rook(color)
            PromotionPiece.QUEEN -> Queen(color)
        }
    }
}