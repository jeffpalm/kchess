package engine

import engine.adapter.BoardSquaresToBitBoard
import engine.adapter.GameToFen
import engine.move.PseudoMove

class Game(private val fen: Fen = Fen(), private val _board: Board = Board(fen)) {
    private val _moves: MutableList<Move> = mutableListOf()
    val moves: List<Move>
        get() = _moves
    val board: Board
        get() = _board

    private val _data: GameData = GameData(
        BoardSquaresToBitBoard(_board.getSquares()).output,
        if (fen.sideToMove == "w") Color.WHITE else Color.BLACK,
        fen.castlingAvailability,
        fen.enPassantTarget,
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
            Sq[move.from.ordinal] to Sq[move.to.ordinal], validatedMove.piece, validatedMove.capture
        )

        handleEnPassantTarget(validatedMove)
        return validatedMove
    }

    private fun handleEnPassantTarget(move: Move) {
        _data.enPassantTarget = move.enPassantTarget()
        if (_data.enPassantTarget != "-") {
            _data.board.enPassantTarget = Sq[_data.enPassantTarget]
        }
    }


    fun undoMove() {
        val lastMove = _moves.removeLast()
        _board.setSquare(lastMove.fromSquare, lastMove.piece)
        _board.setSquare(lastMove.toSquare, lastMove.capture)
        flipSideToMove()
        decrementClocks()
        handleAddingCastlingAvail(lastMove)
        _data.board.undoMove(
            Sq[lastMove.fromSquare.ordinal] to Sq[lastMove.toSquare.ordinal], lastMove.piece, lastMove.capture
        )
        _data.enPassantTarget = lastMove.prevEnPassantTarget
    }

    private fun flipSideToMove() {
        _data.turn = if (_data.turn == Color.WHITE) Color.BLACK else Color.WHITE
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
            m.piece == 'K' && m.fromSquare == Square.e1 -> {
                removeCastlingAbility { c -> c != 'K' && c != 'Q' }
            }
            m.piece == 'k' && m.fromSquare == Square.e8 -> {
                removeCastlingAbility { c -> c != 'k' && c != 'q' }
            }
            m.piece == 'R' && m.fromSquare == Square.a1 -> {
                removeCastlingAbility { c -> c != 'Q' }
            }
            m.piece == 'R' && m.fromSquare == Square.h1 -> {
                removeCastlingAbility { c -> c != 'K' }
            }
            m.piece == 'r' && m.fromSquare == Square.a8 -> {
                removeCastlingAbility { c -> c != 'q' }
            }
            m.piece == 'r' && m.fromSquare == Square.h8 -> {
                removeCastlingAbility { c -> c != 'k' }
            }
        }
    }

    private fun handleAddingCastlingAvail(m: Move) {
        when {
            m.piece == 'K' && m.fromSquare == Square.e1 -> {
                addCastlingAbility('K')
                addCastlingAbility('Q')
            }
            m.piece == 'k' && m.fromSquare == Square.e8 -> {
                addCastlingAbility('k')
                addCastlingAbility('q')
            }
            m.piece == 'R' && m.fromSquare == Square.a1 -> {
                addCastlingAbility('Q')
            }
            m.piece == 'R' && m.fromSquare == Square.h1 -> {
                addCastlingAbility('K')
            }
            m.piece == 'r' && m.fromSquare == Square.a8 -> {
                addCastlingAbility('q')
            }
            m.piece == 'r' && m.fromSquare == Square.h8 -> {
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