package engine

object Piece {
    const val bPawn = 'p'
    const val bKnight = 'n'
    const val bBishop = 'b'
    const val bRook = 'r'
    const val bQueen = 'q'
    const val bKing = 'k'
    const val wPawn = 'P'
    const val wKnight = 'N'
    const val wBishop = 'B'
    const val wRook = 'R'
    const val wQueen = 'Q'
    const val wKing = 'K'

    fun pawn(color: Color): Char = when (color) {
        Color.BLACK -> bPawn
        Color.WHITE -> wPawn
    }

    fun knight(color: Color): Char = when (color) {
        Color.BLACK -> bKnight
        Color.WHITE -> wKnight
    }

    fun bishop(color: Color): Char = when (color) {
        Color.BLACK -> bBishop
        Color.WHITE -> wBishop
    }

    fun rook(color: Color): Char = when (color) {
        Color.BLACK -> bRook
        Color.WHITE -> wRook
    }

    fun queen(color: Color): Char = when (color) {
        Color.BLACK -> bQueen
        Color.WHITE -> wQueen
    }

    fun king(color: Color): Char = when (color) {
        Color.BLACK -> bKing
        Color.WHITE -> wKing
    }

    fun attackPieces(color: Color): List<Char> = when (color) {
        Color.WHITE -> listOf(wPawn, wKnight, wBishop, wRook, wQueen)
        Color.BLACK -> listOf(bPawn, bKnight, bBishop, bRook, bQueen)
    }
}