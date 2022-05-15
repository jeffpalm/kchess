class Move(
    game: Game,
    override val movement: Movement,
    override val piece: Piece,
    override val capture: Piece? = null
) : IMove {
    override val isValid: Boolean
    override val isCheck: Boolean = false
    override val isCheckmate: Boolean = false
    override val enPassantTarget: String?
    val castling: Castling? = try { Castling(this, game) } catch (e: Exception) { null }

    init {
        isValid = determineIfValid(game)
        enPassantTarget = if (isValid) determineEnPassantTarget(game) else null
    }

    private fun determineIfValid(game: Game): Boolean {
        if (movement.trajectory == null) return false
        if (piece is King && castling?.isValidCastle == true) return true
        if (!piece.canMove(movement)) return false
        if (piece is Pawn && capture == null && piece.canCapture(movement)) return false
        if (isValidEnPassant(game)) return true
        if (capture != null && !piece.canCapture(movement)) return false
        if (!game.board.isPathClear(movement)) return false
        if (game.board.getPiece(movement.to)?.color == piece.color) return false

        return true
    }

    private fun isValidEnPassant(game: Game): Boolean {
        return piece is Pawn
                && capture == null
                && game.enPassant != null
                && piece.canCapture(movement)
                && game.board.getSquareNameByCoords(movement.to) == game.enPassant
    }

    private fun determineEnPassantTarget(game: Game): String? {
        if (piece is Pawn) {
            if (piece.color == PieceColor.WHITE && movement.from.y == 6 && movement.to.y == 4) {
                return game.board.getSquareNameByCoords(Coords(movement.from.x, 5))
            }
            if (piece.color == PieceColor.BLACK && movement.from.y == 1 && movement.to.y == 3) {
                return game.board.getSquareNameByCoords(Coords(movement.from.x, 2))
            }
        }
        return null
    }

}
