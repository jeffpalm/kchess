package engine.v2.adapters

import engine.Fen
import engine.v2.BitBoard
import engine.v2.Square

class FenToBitBoard(fen: Fen = Fen()) : Adapter<Fen, BitBoard>(fen) {
    override fun adapt(input: Fen, context: Any?): BitBoard {
        val board = BoardSquaresToBitBoard(FenToBoardRep(input).output).output
        if (input.enPassantTarget != "-") {
            board.enPassantTarget = Square[input.enPassantTarget]
        }
        return board
    }
}