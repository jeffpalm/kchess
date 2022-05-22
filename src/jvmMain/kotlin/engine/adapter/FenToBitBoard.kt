package engine.adapter

import engine.Fen
import engine.BitBoard
import engine.Square

class FenToBitBoard(fen: Fen = Fen()) : Adapter<Fen, BitBoard>(fen) {
    override fun adapt(input: Fen, context: Any?): BitBoard {
        val board = BoardSquaresToBitBoard(FenToBoardRep(input).output).output
        if (input.enPassantTarget != "-") {
            board.enPassantTarget = Square[input.enPassantTarget]
        }
        return board
    }
}