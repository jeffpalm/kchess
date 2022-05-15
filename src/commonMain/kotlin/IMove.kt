interface IMove {
    val piece: Piece
    val capture: Piece?
    val promotion: Promotion?
    val movement: Movement
    val isValid: Boolean
    val isCheck: Boolean
    val isCheckmate: Boolean
    val isPromotion: Boolean
    val enPassantTarget: String?
    fun isLegal(game: Game): Boolean
}