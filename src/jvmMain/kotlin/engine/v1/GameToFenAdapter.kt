package engine.v1

class GameToFenAdapter(game: Game) {
    val fen: String

    init {
        val fenRows = BoardToFenAdapter(game.board.getBoard()).fenBoardRepresentation
        val sideToMove = if (game.turn == PieceColor.WHITE) "w" else "b"
        val castling = game.castling.asString
        val enPassant = game.enPassant ?: "-"
        val halfMoveClock = game.halfMoves.toString()
        val fullMoveClock = game.fullMoves.toString()

        fen = "$fenRows $sideToMove $castling $enPassant $halfMoveClock $fullMoveClock"
    }
}