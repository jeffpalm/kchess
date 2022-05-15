class Move(
    game: Game,
    override val movement: Movement,
    override val piece: Piece,
    override val capture: Piece? = null,
    override val promotion: Promotion? = null
) : IMove {
    override val isValid: Boolean
    override val isCheck: Boolean = false
    override val isCheckmate: Boolean = false
    override val isPromotion: Boolean = determineIfPromotion()
    override val enPassantTarget: String?
    val castling: Castling? = try {
        Castling(this, game)
    } catch (e: Exception) {
        null
    }

    init {
        isValid = determineIfValidMovement(game)
        enPassantTarget = determineIfValidEnPassant(game)
    }

    override fun isLegal(game: Game): Boolean {
        return isValid && game.willRemoveKingFromCheck(this) || !game.willMovePutKingInCheck(this)
    }

    private fun determineIfValidMovement(game: Game): Boolean {
        return when {
            !isValidPieceMovement(game) -> false
            piece is Pawn -> determineIfValidPawnMove(game)
            piece is King -> determineIfValidKingMove()
            else -> true
        }
    }

    private fun isValidPieceMovement(game: Game): Boolean {
        return when {
            movement.trajectory == null -> false
            capture?.color == piece.color -> false
            !piece.canMove(movement) -> false
            !game.board.isPathClear(movement) -> false
            capture != null && !piece.canCapture(movement) -> false
            else -> true
        }
    }

    private fun determineIfValidKingMove(): Boolean {
        return when {
            castling?.isValidCastle == true -> true
            movement.distanceY < 2 && movement.distanceX < 2 -> true
            else -> false
        }
    }

    private fun determineIfValidPawnMove(game: Game): Boolean {
        // Assumptions:
        // Path is clear
        // Movement.distanceX == 1 || 0
        return when {
            movement.distanceX == 0 -> true
            capture != null -> true
            else -> isValidEnPassant(game)
        }
    }

    private fun isValidEnPassant(game: Game): Boolean {
        return piece is Pawn
                && capture == null
                && game.enPassant != null
                && game.board.getSquareNameByCoords(movement.to) == game.enPassant
    }

    private fun determineIfValidEnPassant(game: Game): String? {
        when {
            movement.distanceY != 2 -> return null
            piece is Pawn -> {
                if (piece.color == PieceColor.WHITE && movement.from.y == 6 && movement.to.y == 4) {
                    return game.board.getSquareNameByCoords(Coords(movement.from.x, 5))
                }
                if (piece.color == PieceColor.BLACK && movement.from.y == 1 && movement.to.y == 3) {
                    return game.board.getSquareNameByCoords(Coords(movement.from.x, 2))
                }
            }
        }
        return null
    }

    private fun determineIfPromotion(): Boolean {
        return when {
            piece !is Pawn -> false
            piece.color == PieceColor.WHITE -> movement.to.y == 0
            piece.color == PieceColor.BLACK -> movement.to.y == 7
            else -> false
        }
    }

}
