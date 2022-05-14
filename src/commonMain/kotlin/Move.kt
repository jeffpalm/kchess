class Move(override val move: PotentialMove, override val piece: Piece, override val capture: Piece? = null) : IMove {
    override val isValid: Boolean
    override val isCheck: Boolean = false
    override val isCheckmate: Boolean = false
    override val isStalemate: Boolean = false
    override val isDraw: Boolean = false
    override val isInsufficientMaterial: Boolean = false
    override val isRepetition: Boolean = false
    override val isFiftyMoves: Boolean = false

    init {
        isValid = determineIfValid()
    }

    private fun determineIfValid(): Boolean {
        if (move.trajectory == null) return false
        if (!piece.canMove(move)) return false
        if (capture != null && !piece.canCapture(move)) return false

        return false
    }
}
