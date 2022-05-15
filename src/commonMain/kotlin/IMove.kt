interface IMove {
    val piece: Piece
    val capture: Piece?
    val movement: Movement
    val isValid: Boolean
    val isCheck: Boolean
    val isCheckmate: Boolean
    val enPassantTarget: String?
    fun isLegal(game: Game): Boolean
}