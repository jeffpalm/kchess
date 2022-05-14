class Game(fenString: String) : IGame {
    var fen: Fen = Fen(fenString)
    var board: Board = Board(fen)
    var moves: MutableList<Move> = mutableListOf()
    var turn: PieceColor = PieceColor.WHITE
    var castling: String = fen.castlingAvailability
    var enPassant: String = fen.enPassantTarget
    var halfMoves: Byte = fen.halfMoveClock
    var fullMoves: Byte = fen.fullMoveClock

    private fun oppositeSide(): PieceColor {
        return if (turn == PieceColor.WHITE) PieceColor.BLACK else PieceColor.WHITE
    }

    override fun generateMoves(): List<Move> {
        val fromSquares = board.getSquaresByPieceColor(turn)
        for (square in fromSquares) {

        }

        TODO("Not yet implemented")
    }

    override fun makeMove(move: Move) {
        moves.add(move)
        board.setPiece(move.move.from.x, move.move.from.y, null)
        board.setPiece(move.move.to.x, move.move.to.y, move.piece)
        incrementMoveClock()
        switchSideToMove()
    }

    override fun undoMove() {
        val lastMove = moves.removeLast()
        board.setPiece(lastMove.move.from.x, lastMove.move.from.y, lastMove.piece)
        board.setPiece(lastMove.move.to.x, lastMove.move.to.y, lastMove.capture)
        decrementMoveClock()
        switchSideToMove()
    }

    override fun isMoveCheck(move: PotentialMove): Boolean {
        TODO("Not yet implemented")
    }

    override fun getActiveChecks(): List<PotentialMove> {
        TODO("Not yet implemented")
    }

    override fun isMoveValid(move: PotentialMove): Boolean {
        TODO("Not yet implemented")
    }

    private fun switchSideToMove() {
        turn = if (turn == PieceColor.WHITE) PieceColor.BLACK else PieceColor.WHITE
    }

    private fun incrementMoveClock() {
        if (halfMoves == 0.toByte()) {
            halfMoves = 1
        } else {
            halfMoves = 0
            fullMoves++
        }
    }

    private fun decrementMoveClock() {
        if (halfMoves == 0.toByte()) {
            halfMoves = 1
            fullMoves--
        } else {
            halfMoves = 0
        }
    }
}