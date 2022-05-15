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

}
