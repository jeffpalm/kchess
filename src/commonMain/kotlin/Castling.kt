class Castling(move: Move, game: Game) {
    val isValidCastle: Boolean
    val castleSide: CastleSide?
    val notation: String?
    val rookMovement: Movement?

    init {
        if (move.piece !is King) {
            throw IllegalArgumentException("Castling move must be made by a king")
        }
        when {
            move.movement.from == CastleCoords.WHITE_FROM && move.movement.to == CastleCoords.WHITE_TO_QUEEN_SIDE -> {
                castleSide = CastleSide.QUEEN
                notation = CastleNotation(CastleSide.QUEEN).notation
                rookMovement = Movement(CastleCoords.WHITE_ROOK_FROM_QUEEN_SIDE, CastleCoords.WHITE_ROOK_TO_QUEEN_SIDE)
                isValidCastle = game.board.isPathClear(Movement(CastleCoords.WHITE_ROOK_FROM_QUEEN_SIDE, CastleCoords.WHITE_FROM)) && game.castling.whiteQueenSide
            }
            move.movement.from == CastleCoords.WHITE_FROM && move.movement.to == CastleCoords.WHITE_TO_KING_SIDE -> {
                castleSide = CastleSide.KING
                notation = CastleNotation(CastleSide.KING).notation
                rookMovement = Movement(CastleCoords.WHITE_ROOK_FROM_KING_SIDE, CastleCoords.WHITE_ROOK_TO_KING_SIDE)
                isValidCastle = game.board.isPathClear(Movement(CastleCoords.WHITE_ROOK_FROM_KING_SIDE, CastleCoords.WHITE_FROM)) && game.castling.whiteKingSide
            }
            move.movement.from == CastleCoords.BLACK_FROM && move.movement.to == CastleCoords.BLACK_TO_QUEEN_SIDE -> {
                castleSide = CastleSide.QUEEN
                notation = CastleNotation(CastleSide.QUEEN).notation
                rookMovement = Movement(CastleCoords.BLACK_ROOK_FROM_QUEEN_SIDE, CastleCoords.BLACK_ROOK_TO_QUEEN_SIDE)
                isValidCastle = game.board.isPathClear(Movement(CastleCoords.BLACK_ROOK_FROM_QUEEN_SIDE, CastleCoords.BLACK_FROM)) && game.castling.blackQueenSide
            }
            move.movement.from == CastleCoords.BLACK_FROM && move.movement.to == CastleCoords.BLACK_TO_KING_SIDE -> {
                castleSide = CastleSide.KING
                notation = CastleNotation(CastleSide.KING).notation
                rookMovement = Movement(CastleCoords.BLACK_ROOK_FROM_KING_SIDE, CastleCoords.BLACK_ROOK_TO_KING_SIDE)
                isValidCastle = game.board.isPathClear(Movement(CastleCoords.BLACK_ROOK_FROM_KING_SIDE, CastleCoords.BLACK_FROM)) && game.castling.blackKingSide
            }
            else -> {
                isValidCastle = false
                castleSide = null
                notation = null
                rookMovement = null
            }
        }
    }

}