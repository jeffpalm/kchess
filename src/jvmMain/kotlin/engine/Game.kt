package engine

import engine.v2.BoardRep
import engine.v2.GameData
import engine.v2.Move
import engine.v2.PieceColor
import engine.v2.adapters.BoardSquaresToBitBoard
import engine.v2.moves.PseudoMove

class Game(fen: Fen = Fen()) {
    private val moves: MutableList<Move> = mutableListOf()
    private val boardRep: BoardRep = BoardRep(fen)

    val gameData: GameData = GameData(
        BoardSquaresToBitBoard(boardRep.getSquares()).output,
        if (fen.sideToMove == "w") PieceColor.WHITE else PieceColor.BLACK,
        fen.castlingAvailability,
        fen.enPassantTarget,
        fen.halfMoveClock,
        fen.fullMoveClock
    )

    fun makeMove(move: PseudoMove) {
        val validatedMove = Move(move.fromSquare, move.toSquare, boardRep)
        moves.add(validatedMove)
        boardRep.setSquare(move.fromSquare.ordinal.toByte(), null)
        boardRep.setSquare(move.toSquare.ordinal.toByte(), validatedMove.piece)
        gameData.board = BoardSquaresToBitBoard(boardRep.getSquares()).output
    }

    fun undoMove() {
        val lastMove = moves.removeLast()
        boardRep.setSquare(lastMove.fromSquare.ordinal.toByte(), lastMove.piece)
        boardRep.setSquare(lastMove.toSquare.ordinal.toByte(), lastMove.capture)
        gameData.board = BoardSquaresToBitBoard(boardRep.getSquares()).output
    }

}