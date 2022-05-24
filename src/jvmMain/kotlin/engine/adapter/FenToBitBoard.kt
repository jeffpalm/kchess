package engine.adapter

import engine.Fen
import engine.BitBoard
import engine.Sq

class FenToBitBoard(fen: Fen = Fen()) : Adapter<Fen, BitBoard>(fen) {
    override fun adapt(input: Fen, context: Any?): BitBoard {
        val board = BoardSquaresToBitBoard(FenToBoardRep(input).output).output
        if (input.enPassantTarget != "-") {
            board.enPassantTarget = Sq[input.enPassantTarget]
        }
        return board
    }
}