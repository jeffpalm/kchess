package engine.adapter

import engine.Board
import engine.Fen

class FenToBoardRep(fen: Fen = Fen()) : Adapter<Fen, MutableMap<Byte, Char?>>(fen) {
    override fun adapt(input: Fen, context: Any?): MutableMap<Byte, Char?> {
        val boardRep = Board.emptySquares.toMutableMap()
        var i = 0.toByte()
        for (row in input.boardRepresentation.reversed().split('/')) {
            for (char in row.reversed().toCharArray()) {
                when {
                    char in Fen.pieces -> {
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