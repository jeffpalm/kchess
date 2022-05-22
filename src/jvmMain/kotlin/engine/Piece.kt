package engine

object Piece {
    const val blackPawn = 'p'
    const val blackKnight = 'n'
    const val blackBishop = 'b'
    const val blackRook = 'r'
    const val blackQueen = 'q'
    const val blackKing = 'k'
    const val whitePawn = 'P'
    const val whiteKnight = 'N'
    const val whiteBishop = 'B'
    const val whiteRook = 'R'
    const val whiteQueen = 'Q'
    const val whiteKing = 'K'

    fun pawn(color: Color): Char = when (color) {
        Color.BLACK -> blackPawn
        Color.WHITE -> whitePawn
    }

    fun knight(color: Color): Char = when (color) {
        Color.BLACK -> blackKnight
        Color.WHITE -> whiteKnight
    }

    fun bishop(color: Color): Char = when (color) {
        Color.BLACK -> blackBishop
        Color.WHITE -> whiteBishop
    }

    fun rook(color: Color): Char = when (color) {
        Color.BLACK -> blackRook
        Color.WHITE -> whiteRook
    }

    fun queen(color: Color): Char = when (color) {
        Color.BLACK -> blackQueen
        Color.WHITE -> whiteQueen
    }

    fun king(color: Color): Char = when (color) {
        Color.BLACK -> blackKing
        Color.WHITE -> whiteKing
    }

    fun attackPieces(color: Color): List<Char> = when (color) {
        Color.WHITE -> listOf(whitePawn, whiteKnight, whiteBishop, whiteRook, whiteQueen)
        Color.BLACK -> listOf(blackPawn, blackKnight, blackBishop, blackRook, blackQueen)
    }
}