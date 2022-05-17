package engine.v1

import engine.Fen

class Game(fenString: String? = null) : IGame {
    var fen: Fen = if (fenString != null) Fen(fenString) else Fen()
    var board: Board = Board(fen)
    var moves: MutableList<Move> = mutableListOf()
    var turn: PieceColor = if (fen.sideToMove == "w") PieceColor.WHITE else PieceColor.BLACK
    var castling: CastlingAvailability = CastlingAvailability(fen.castlingAvailability)
    var enPassant: String? = fen.enPassantTarget
    var halfMoves: Int = fen.halfMoveClock
    var fullMoves: Int = fen.fullMoveClock

    private fun oppositeSide(): PieceColor {
        return if (turn == PieceColor.WHITE) PieceColor.BLACK else PieceColor.WHITE
    }

    override fun generateMoves(): List<Move> {
        val moves: MutableList<Move> = mutableListOf()
        val fromSquares = board.getSquaresByPieceColor(turn)
        for (square in fromSquares) {
            val potentials = board.getPotentialMovesBySquareCoords(square.coords)
            for (potential in potentials) {
                val piece = square.piece ?: throw Exception("No piece on from square")
                val capture = board.getPiece(potential.to)
                val move = Move(this, potential, piece, capture)
                if (move.isValid && move.isLegal(this)) {
                    if (move.isPromotion) {
                        for (promoPiece in PromotionPiece.values()) {
                            val promoMove = Move(this, potential, piece, capture, Promotion(piece.color, promoPiece))
                            moves.add(promoMove)
                        }
                    } else {
                        moves.add(move)
                    }
                }
            }
        }
        return moves
    }

    override fun makeMove(move: Move) {
        moves.add(move)
        board.setPiece(move.movement.from, null)
        if (move.isPromotion && move.promotion != null) board.setPiece(
            move.movement.to,
            move.promotion.piece
        ) else board.setPiece(move.movement.to, move.piece)
        incrementMoveClock()
        switchSideToMove()
        enPassant = move.enPassantTarget
        castling.makeMove(move)
        move.castling?.handleMove(this)
    }

    override fun undoMove() {
        val lastMove = moves.removeLast()
        board.setPiece(lastMove.movement.from, lastMove.piece)
        board.setPiece(lastMove.movement.to, lastMove.capture)
        decrementMoveClock()
        switchSideToMove()
        enPassant = lastMove.enPassantTarget
        castling.undoMove(lastMove)
        lastMove.castling?.handleUndoMove(this)
    }

    override fun isMoveCheck(move: Move): Boolean {
        makeMove(move)
        val isCheck = isKingInCheck()
        undoMove()
        return isCheck
    }

    fun willRemoveKingFromCheck(move: Move): Boolean {
        board.setPiece(move.movement.from, null)
        board.setPiece(move.movement.to, move.piece)
        val kingStillInCheck = isKingInCheck()
        board.setPiece(move.movement.from, move.piece)
        board.setPiece(move.movement.to, move.capture)
        return !kingStillInCheck
    }

    override fun willMovePutKingInCheck(move: Move): Boolean {
        board.setPiece(move.movement.from, null)
        val toPiece = board.getPiece(move.movement.to)
        if (move.piece is King) {
            board.setPiece(move.movement.to, move.piece)
        }
        val willBeInCheck = isKingInCheck()
        board.setPiece(move.movement.from, move.piece)
        if (move.piece is King) {
            board.setPiece(move.movement.to, toPiece)
        }
        return willBeInCheck
    }

    override fun isKingInCheck(): Boolean {
        val kingSquare = board.getKing(turn)
        val potentials = board.getSquaresByPieceColor(oppositeSide())

        for (potential in potentials) {
            val move = Move(this, Movement(potential.coords, kingSquare.coords), potential.piece!!, kingSquare.piece)
            if (move.isValid) return true
        }
        return false
    }

    override fun getActiveChecks(): List<Move> {
        val activeChecks: MutableList<Move> = mutableListOf()
        val kingSquare = board.getKing(turn)
        val potentials = board.getSquaresByPieceColor(oppositeSide())

        for (potential in potentials) {
            val move = Move(this, Movement(potential.coords, kingSquare.coords), potential.piece!!, kingSquare.piece)
            if (move.isValid) {
                activeChecks.add(move)
            }
        }
        return activeChecks
    }

    override fun isMoveValid(move: Movement): Boolean {
        val piece = board.getPiece(move.from) ?: return false
        val potentials = board.getPotentialMovesBySquareCoords(move.from)
        for (potential in potentials) {
            if (potential.to == move.to) {
                val capture = board.getPiece(potential.to)
                val move = Move(this, potential, piece, capture)
                return move.isValid
            }
        }
        return false
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