class CastlingAvailability(fen: String = "KQkq") {
    var blackKingSide: Boolean = false
    var whiteKingSide: Boolean = false
    var blackQueenSide: Boolean = false
    var whiteQueenSide: Boolean = false

    init {
        for (element in fen) {
            when (element) {
                'K' -> whiteKingSide = true
                'k' -> blackKingSide = true
                'Q' -> whiteQueenSide = true
                'q' -> blackQueenSide = true
            }
        }
    }

    val asString: String
        get() {
            var result = ""
            if (whiteKingSide) {
                result += "K"
            }
            if (whiteQueenSide) {
                result += "Q"
            }
            if (blackKingSide) {
                result += "k"
            }
            if (blackQueenSide) {
                result += "q"
            }
            return result
        }

    fun makeMove(move: Move) {
        when (move.movement.from) {
            CastleCoords.WHITE_FROM -> {
                whiteKingSide = false
                whiteQueenSide = false
            }
            CastleCoords.BLACK_FROM -> {
                blackKingSide = false
                whiteQueenSide = false
            }
            CastleCoords.WHITE_ROOK_FROM_KING_SIDE -> whiteKingSide = false
            CastleCoords.WHITE_ROOK_FROM_QUEEN_SIDE -> whiteQueenSide = false
            CastleCoords.BLACK_ROOK_FROM_KING_SIDE -> blackKingSide = false
            CastleCoords.BLACK_ROOK_FROM_QUEEN_SIDE -> blackQueenSide = false
        }
    }

    fun undoMove(move: Move) {
        when (move.movement.from) {
            CastleCoords.WHITE_FROM -> {
                whiteKingSide = true
                whiteQueenSide = true
            }
            CastleCoords.BLACK_FROM -> {
                blackKingSide = true
                whiteQueenSide = true
            }
            CastleCoords.WHITE_ROOK_FROM_KING_SIDE -> whiteKingSide = true
            CastleCoords.WHITE_ROOK_FROM_QUEEN_SIDE -> whiteQueenSide = true
            CastleCoords.BLACK_ROOK_FROM_KING_SIDE -> blackKingSide = true
            CastleCoords.BLACK_ROOK_FROM_QUEEN_SIDE -> blackQueenSide = true
        }
    }
}
