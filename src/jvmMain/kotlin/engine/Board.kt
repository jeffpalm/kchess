package engine

class Board(fen: Fen = Fen()) {
    val rep: BoardRep = BoardRep(fen)
    val bitBoard: BitBoard = BitBoard()

    fun makeMove(move: Move) {
        rep.setSquare(move.fromSquare)
        rep.setSquare(move.toSquare, move.piece)
    }

    fun makeMoveByDirection(startSquare: SquareMap, direction: Int, steps: Int = 1) {
        val piece = rep.getPiece(startSquare)
        rep.setSquare(startSquare)
        val nextSquareIdx = (startSquare.ordinal + (direction * steps)).toByte()
        rep.setSquare(nextSquareIdx, piece)
    }
}