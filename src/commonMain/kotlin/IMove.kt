interface IMove {
    val piece: Piece
    val capture: Piece?
    val move: PotentialMove
    val isValid: Boolean
    val isCheck: Boolean
    val isCheckmate: Boolean
    val isStalemate: Boolean
    val isDraw: Boolean
    val isInsufficientMaterial: Boolean
    val isRepetition: Boolean
    val isFiftyMoves: Boolean
}