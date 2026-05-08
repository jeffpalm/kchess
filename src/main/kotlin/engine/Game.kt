package engine

import engine.adapter.FenToBitBoard
import engine.adapter.GameToFen
import engine.move.Magic
import engine.move.PseudoMove

class Game(private val fen: Fen = Fen(), private val _board: Board = Board(fen)) {
    private val _moves: MutableList<Move> = mutableListOf()
    val moves: List<Move>
        get() = _moves
    val board: Board
        get() = _board

    private val _data: GameData = GameData(
        FenToBitBoard(fen).output,
        if (fen.sideToMove == "w") Color.WHITE else Color.BLACK,
        CastleAvail.parse(fen.castlingAvailability),
        if (fen.enPassantTarget == "-") null else Square[fen.enPassantTarget],
        fen.halfMoveClock,
        fen.fullMoveClock
    )
    val data: IGameData
        get() = _data

    fun makeMove(move: PseudoMove): Move {
        val validatedMove = Move(
            move.from,
            move.to,
            move.piece,
            _board,
            _data.enPassantTarget,
            _data.castleAvail,
            move.promo,
        )
        _moves.add(validatedMove)
        if (move.to == _data.enPassantTarget && (validatedMove.piece == Piece.wPawn || validatedMove.piece == Piece.bPawn)) {
            _board.setSquare(Square.fromBit(Magic.EnPassantCaptureSq[move.to.asBit()]), null)
        }
        _board.setSquare(move.from, null)
        _board.setSquare(move.to, move.promo ?: validatedMove.piece)
        flipSideToMove()
        incrementClocks()
        handleRemovingCastlingAvail(validatedMove)
        _data.board.makeMove(
            move.from.asBit() to move.to.asBit(), validatedMove.piece, validatedMove.capture, move.promo
        )

        handleCastleRookMove(validatedMove)
        handleEnPassantTarget(validatedMove)
        return validatedMove
    }

    private fun handleCastleRookMove(m: Move) {
        val (rookFrom, rookTo, rookPiece) = when {
            m.isWhiteKingCastle() && m.to == Square.g1 -> Triple(Square.h1, Square.f1, 'R')
            m.isWhiteKingCastle() && m.to == Square.c1 -> Triple(Square.a1, Square.d1, 'R')
            m.isBlackKingCastle() && m.to == Square.g8 -> Triple(Square.h8, Square.f8, 'r')
            m.isBlackKingCastle() && m.to == Square.c8 -> Triple(Square.a8, Square.d8, 'r')
            else -> return
        }
        _board.setSquare(rookFrom, null)
        _board.setSquare(rookTo, rookPiece)
        _data.board.makeMove(rookFrom.asBit() to rookTo.asBit(), rookPiece)
    }

    private fun handleEnPassantTarget(move: Move) {
        val enPassantTarget = move.enPassantTarget()
        _data.enPassantTarget = enPassantTarget
        _data.board.enPassantTarget = enPassantTarget?.asBit()
    }


    fun undoMove() {
        val pMove = _moves.removeLast()
        val isEnPassant = (pMove.piece == Piece.wPawn || pMove.piece == Piece.bPawn)
            && pMove.to == pMove.prevEnPassantTarget
        val capturedSquare = if (isEnPassant) Magic.EnPassantCaptureSq[pMove.to.asBit()] else pMove.to.asBit()

        // Board (squares)
        if (isEnPassant) {
            _board.setSquare(pMove.to, null)
            _board.setSquare(Square.fromBit(capturedSquare), pMove.capture)
        } else {
            _board.setSquare(pMove.to, pMove.capture)
        }
        _board.setSquare(pMove.from, pMove.piece)

        // BitBoard
        _data.board.undoMove(
            from = pMove.from.asBit(),
            to = pMove.to.asBit(),
            piece = pMove.piece,
            capture = pMove.capture,
            capturedSquare = capturedSquare,
            promo = pMove.promo,
        )
        handleCastleRookUndo(pMove)

        // Restore game-level state from snapshot
        _data.castleAvail = pMove.prevCastleAvail
        _data.enPassantTarget = pMove.prevEnPassantTarget
        _data.board.enPassantTarget = pMove.prevEnPassantTarget?.asBit()

        flipSideToMove()
        decrementClocks()
    }

    private fun handleCastleRookUndo(m: Move) {
        val (rookFrom, rookTo, rookPiece) = when {
            m.isWhiteKingCastle() && m.to == Square.g1 -> Triple(Square.h1, Square.f1, 'R')
            m.isWhiteKingCastle() && m.to == Square.c1 -> Triple(Square.a1, Square.d1, 'R')
            m.isBlackKingCastle() && m.to == Square.g8 -> Triple(Square.h8, Square.f8, 'r')
            m.isBlackKingCastle() && m.to == Square.c8 -> Triple(Square.a8, Square.d8, 'r')
            else -> return
        }
        _board.setSquare(rookTo, null)
        _board.setSquare(rookFrom, rookPiece)
        _data.board.undoMove(from = rookFrom.asBit(), to = rookTo.asBit(), piece = rookPiece)
    }

    private fun flipSideToMove() {
        _data.turn = if (_data.turn == Color.WHITE) Color.BLACK else Color.WHITE
        _data.board.turn = !_data.board.turn
    }

    private fun incrementClocks() {
        if (_data.halfMoveClock == 1) {
            _data.fullMoveClock++
            _data.halfMoveClock = 0
        } else {
            _data.halfMoveClock = 1
        }
    }

    private fun decrementClocks() {
        if (_data.halfMoveClock == 0) {
            _data.fullMoveClock--
            _data.halfMoveClock = 1
        } else {
            _data.halfMoveClock = 0
        }
    }

    private fun handleRemovingCastlingAvail(m: Move) {
        var rights = _data.castleAvail
        if (rights == CastleAvail.NONE) return
        // Moving piece
        when (m.piece) {
            'K' -> if (m.from == Square.e1) rights = rights and CastleAvail.DROP_WHITE
            'k' -> if (m.from == Square.e8) rights = rights and CastleAvail.DROP_BLACK
            'R' -> when (m.from) {
                Square.a1 -> rights = rights and CastleAvail.Q.inv()
                Square.h1 -> rights = rights and CastleAvail.K.inv()
                else -> {}
            }
            'r' -> when (m.from) {
                Square.a8 -> rights = rights and CastleAvail.BQ.inv()
                Square.h8 -> rights = rights and CastleAvail.BK.inv()
                else -> {}
            }
        }
        // Captured rook on its starting square
        when (m.to) {
            Square.a1 -> if (m.capture == 'R') rights = rights and CastleAvail.Q.inv()
            Square.h1 -> if (m.capture == 'R') rights = rights and CastleAvail.K.inv()
            Square.a8 -> if (m.capture == 'r') rights = rights and CastleAvail.BQ.inv()
            Square.h8 -> if (m.capture == 'r') rights = rights and CastleAvail.BK.inv()
            else -> {}
        }
        _data.castleAvail = rights
    }

    fun clone(): Game {
        return Game(GameToFen(this).output)
    }
}