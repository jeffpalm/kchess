class Game(fenString: String? = null) : IGame {
    var fen: Fen = if (fenString != null) Fen(fenString) else Fen()
    var board: Board = Board(fen)
    var moves: MutableList<Move> = mutableListOf()
    var turn: PieceColor = PieceColor.WHITE
    var castling: CastlingAvailability = CastlingAvailability(fen.castlingAvailability)
    var enPassant: String? = fen.enPassantTarget
    var halfMoves: Int = fen.halfMoveClock
    var fullMoves: Int = fen.fullMoveClock

    private fun oppositeSide(): PieceColor {
        return if (turn == PieceColor.WHITE) PieceColor.BLACK else PieceColor.WHITE
    }

    override fun generateMoves(): List<Move> {
        var moves: MutableList<Move> = mutableListOf()
        val fromSquares = board.getSquaresByPieceColor(turn)
        for (square in fromSquares) {
            val potentials = board.getPotentialMovesBySquareCoords(square.coords)
            for (potential in potentials) {
                val piece = square.piece ?: throw Exception("No piece on from square")
                val capture = board.getPiece(potential.to)
                val move = Move(this, potential, piece, capture)
                if (move.isValid) {
                    moves.add(move)
                }
            }
        }
        return moves
    }

    override fun makeMove(move: Move) {
        moves.add(move)
        board.setPiece(move.movement.from, null)
        board.setPiece(move.movement.to, move.piece)
        incrementMoveClock()
        switchSideToMove()
        enPassant = move.enPassantTarget
    }

    override fun undoMove() {
        val lastMove = moves.removeLast()
        board.setPiece(lastMove.movement.from, lastMove.piece)
        board.setPiece(lastMove.movement.to, lastMove.capture)
        decrementMoveClock()
        switchSideToMove()
        enPassant = lastMove.enPassantTarget
    }

    override fun isMoveCheck(move: Movement): Boolean {
        TODO("Not yet implemented")
    }

    override fun getActiveChecks(): List<Movement> {
        TODO("Not yet implemented")
    }

    override fun isMoveValid(move: Movement): Boolean {
        TODO("Not yet implemented")
    }

    private fun switchSideToMove() {
        turn = if (turn == PieceColor.WHITE) PieceColor.BLACK else PieceColor.WHITE
    }

    private fun incrementMoveClock() {
        if (halfMoves == 0) {
            halfMoves = 1
        } else {
            halfMoves = 0
            fullMoves++
        }
    }

    private fun decrementMoveClock() {
        if (halfMoves == 0) {
            halfMoves = 1
            fullMoves--
        } else {
            halfMoves = 0
        }
    }
}