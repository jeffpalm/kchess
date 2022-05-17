package engine.v2.adapters

import engine.Fen
import engine.v2.Constants

class FenToBoardRep(fen: Fen = Fen()) : Adapter<Fen, MutableMap<Byte, Char?>>(fen) {
    override fun adapt(input: Fen): MutableMap<Byte, Char?> {
        val boardRep = Constants.boardRep.toMutableMap()
        var i = 0.toByte()
        for (row in input.boardRepresentation.reversed().split('/')) {
            for (char in row.reversed().toCharArray()) {
                when {
                    char in Constants.fenPieces -> {
                        boardRep[i] = char
                        i++
                    }
                    char.isDigit() -> i = (i + char.digitToInt().toByte()).toByte()
                    else -> throw IllegalArgumentException("Invalid FEN representation")
                }
            }
        }
        return boardRep
    }
}