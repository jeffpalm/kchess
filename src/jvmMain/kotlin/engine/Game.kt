package engine

import engine.v2.*
import engine.v2.adapters.BoardSquaresToBitBoard
import engine.v2.moves.PseudoMove

class Game(fen: Fen = Fen()) {
    private val _moves: MutableList<Move> = mutableListOf()
    val moves: List<Move>
        get() = _moves
    private val boardRep: BoardRep = BoardRep(fen)

    private val _gameData: GameData = GameData(
        BoardSquaresToBitBoard(boardRep.getSquares()).output,
        if (fen.sideToMove == "w") PieceColor.WHITE else PieceColor.BLACK,
        fen.castlingAvailability,
        fen.enPassantTarget,
        fen.halfMoveClock,
        fen.fullMoveClock
    )
    val gameData: IGameData
        get() = _gameData

    fun makeMove(move: PseudoMove) {
        val validatedMove = Move(move.fromSquare, move.toSquare, boardRep)
        _moves.add(validatedMove)
        boardRep.setSquare(move.fromSquare.ordinal.toByte(), null)
        boardRep.setSquare(move.toSquare.ordinal.toByte(), validatedMove.piece)
        _gameData.board = BoardSquaresToBitBoard(boardRep.getSquares()).output
    }

    fun undoMove() {
        val lastMove = _moves.removeLast()
        boardRep.setSquare(lastMove.fromSquare.ordinal.toByte(), lastMove.piece)
        boardRep.setSquare(lastMove.toSquare.ordinal.toByte(), lastMove.capture)
        _gameData.board = BoardSquaresToBitBoard(boardRep.getSquares()).output
    }

}