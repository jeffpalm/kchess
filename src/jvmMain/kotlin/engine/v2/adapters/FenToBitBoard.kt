package engine.v2.adapters

import engine.Fen
import engine.v2.BitBoard

class FenToBitBoard(fen: Fen = Fen()) : Adapter<Fen, BitBoard>(fen) {
    override fun adapt(input: Fen, context: Any?): BitBoard {
        return BoardSquaresToBitBoard(FenToBoardRep(input).output).output
    }
}