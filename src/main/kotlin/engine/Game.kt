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
        fen.castlingAvailability,
        if (fen.enPassantTarget == "-") null else Square[fen.enPassantTarget],
        fen.halfMoveClock,
        fen.fullMoveClock
    )
    val data: IGameData
        get() = _data

    fun makeMove(move: PseudoMove): Move {
        val validatedMove = Move(move.from, move.to, _board, _data.enPassantTarget)
        _moves.add(validatedMove)
        _board.setSquare(move.from, null)
        _board.setSquare(move.to, validatedMove.piece)
        flipSideToMove()
        incrementClocks()
        handleRemovingCastlingAvail(validatedMove)
        _data.board.makeMove(
            move.from.asBit() to move.to.asBit(), validatedMove.piece, validatedMove.capture
        )

        handleEnPassantTarget(validatedMove)
        return validatedMove
    }

    private fun handleEnPassantTarget(move: Move) {
        val enPassantTarget = move.enPassantTarget()
        _data.enPassantTarget = enPassantTarget
        _data.board.enPassantTarget = enPassantTarget?.asBit()
    }


    fun undoMove() {
        val pMove = _moves.removeLast()
        _board.setSquare(pMove.from, pMove.piece)
        _board.setSquare(pMove.to, pMove.capture)
        flipSideToMove()
        decrementClocks()
        handleAddingCastlingAvail(pMove)
        val toSquare = if (pMove.to == pMove.prevEnPassantTarget) Magic.EnPassantCaptureSq[pMove.to] else pMove.to.asBit()
        _data.board.undoMove(
            pMove.from.asBit() to toSquare, pMove.piece, pMove.capture
        )
        _data.enPassantTarget = pMove.prevEnPassantTarget
        _data.board.enPassantTarget = pMove.prevEnPassantTarget?.asBit()
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
        when {
            m.piece == 'K' && m.from == Square.e1 -> {
                removeCastlingAbility { c -> c != 'K' && c != 'Q' }
            }
            m.piece == 'k' && m.from == Square.e8 -> {
                removeCastlingAbility { c -> c != 'k' && c != 'q' }
            }
            m.piece == 'R' && m.from == Square.a1 -> {
                removeCastlingAbility { c -> c != 'Q' }
            }
            m.piece == 'R' && m.from == Square.h1 -> {
                removeCastlingAbility { c -> c != 'K' }
            }
            m.piece == 'r' && m.from == Square.a8 -> {
                removeCastlingAbility { c -> c != 'q' }
            }
            m.piece == 'r' && m.from == Square.h8 -> {
                removeCastlingAbility { c -> c != 'k' }
            }
        }
    }

    private fun handleAddingCastlingAvail(m: Move) {
        when {
            m.piece == 'K' && m.from == Square.e1 -> {
                addCastlingAbility('K')
                addCastlingAbility('Q')
            }
            m.piece == 'k' && m.from == Square.e8 -> {
                addCastlingAbility('k')
                addCastlingAbility('q')
            }
            m.piece == 'R' && m.from == Square.a1 -> {
                addCastlingAbility('Q')
            }
            m.piece == 'R' && m.from == Square.h1 -> {
                addCastlingAbility('K')
            }
            m.piece == 'r' && m.from == Square.a8 -> {
                addCastlingAbility('q')
            }
            m.piece == 'r' && m.from == Square.h8 -> {
                addCastlingAbility('k')
            }
        }
    }

    private fun removeCastlingAbility(filter: (Char) -> Boolean) {
        _data.castleAvail = _data.castleAvail.toCharArray().filter(filter).joinToString("")
        if (_data.castleAvail.isEmpty()) {
            _data.castleAvail = "-"
        }
    }

    private fun addCastlingAbility(c: Char) {
        if (_data.castleAvail == "-") {
            _data.castleAvail = c.toString()
        } else {
            _data.castleAvail += c
        }
    }

    fun clone(): Game {
        return Game(GameToFen(this).output)
    }
}