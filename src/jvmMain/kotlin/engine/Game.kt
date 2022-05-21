package engine

import engine.v2.*
import engine.v2.adapters.BoardSquaresToBitBoard
import engine.v2.moves.PseudoMove

class Game(fen: Fen = Fen()) {
    private val _moves: MutableList<Move> = mutableListOf()
    val moves: List<Move>
        get() = _moves
    private val _boardRep: BoardRep = BoardRep(fen)
    val boardRep: BoardRep
        get() = _boardRep

    private val _data: GameData = GameData(
        BoardSquaresToBitBoard(_boardRep.getSquares()).output,
        if (fen.sideToMove == "w") Color.WHITE else Color.BLACK,
        fen.castlingAvailability,
        fen.enPassantTarget,
        fen.halfMoveClock,
        fen.fullMoveClock
    )
    val data: IGameData
        get() = _data

    fun makeMove(move: PseudoMove): Move {
        val validatedMove = Move(move.from, move.to, _boardRep)
        _moves.add(validatedMove)
        _boardRep.setSquare(move.from.ordinal.toByte(), null)
        _boardRep.setSquare(move.to.ordinal.toByte(), validatedMove.piece)
        flipSideToMove()
        incrementClocks()
        handleRemovingCastlingAvail(validatedMove)
        _data.board.makeMove(
            Square[move.from.ordinal] to Square[move.to.ordinal], validatedMove.piece, validatedMove.capture
        )

        handleEnPassantTarget(validatedMove)
        return validatedMove
    }

    private fun handleEnPassantTarget(move: Move) {
        _data.enPassantTarget = move.enPassantTarget()
        if (_data.enPassantTarget != "-") {
            _data.board.enPassantTarget = Square[_data.enPassantTarget]
        }
    }


    fun undoMove() {
        val lastMove = _moves.removeLast()
        _boardRep.setSquare(lastMove.fromSquare.ordinal.toByte(), lastMove.piece)
        _boardRep.setSquare(lastMove.toSquare.ordinal.toByte(), lastMove.capture)
        flipSideToMove()
        decrementClocks()
        handleAddingCastlingAvail(lastMove)
        _data.board.undoMove(
            Square[lastMove.fromSquare.ordinal] to Square[lastMove.toSquare.ordinal], lastMove.piece, lastMove.capture
        )
        handleEnPassantTarget(lastMove)
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
            m.piece == 'K' && m.fromSquare == SquareMap.e1 -> {
                removeCastlingAbility { c -> c != 'K' && c != 'Q' }
            }
            m.piece == 'k' && m.fromSquare == SquareMap.e8 -> {
                removeCastlingAbility { c -> c != 'k' && c != 'q' }
            }
            m.piece == 'R' && m.fromSquare == SquareMap.a1 -> {
                removeCastlingAbility { c -> c != 'Q' }
            }
            m.piece == 'R' && m.fromSquare == SquareMap.h1 -> {
                removeCastlingAbility { c -> c != 'K' }
            }
            m.piece == 'r' && m.fromSquare == SquareMap.a8 -> {
                removeCastlingAbility { c -> c != 'q' }
            }
            m.piece == 'r' && m.fromSquare == SquareMap.h8 -> {
                removeCastlingAbility { c -> c != 'k' }
            }
        }
    }

    private fun handleAddingCastlingAvail(m: Move) {
        when {
            m.piece == 'K' && m.fromSquare == SquareMap.e1 -> {
                addCastlingAbility('K')
                addCastlingAbility('Q')
            }
            m.piece == 'k' && m.fromSquare == SquareMap.e8 -> {
                addCastlingAbility('k')
                addCastlingAbility('q')
            }
            m.piece == 'R' && m.fromSquare == SquareMap.a1 -> {
                addCastlingAbility('Q')
            }
            m.piece == 'R' && m.fromSquare == SquareMap.h1 -> {
                addCastlingAbility('K')
            }
            m.piece == 'r' && m.fromSquare == SquareMap.a8 -> {
                addCastlingAbility('q')
            }
            m.piece == 'r' && m.fromSquare == SquareMap.h8 -> {
                addCastlingAbility('k')
            }
        }
    }

    private fun removeCastlingAbility(filter: (Char) -> Boolean) {
        _data.castleAvail = _data.castleAvail.toCharArray().filter(filter).toString()
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
}