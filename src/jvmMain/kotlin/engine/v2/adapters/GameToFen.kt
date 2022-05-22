package engine.v2.adapters

import engine.Color
import engine.Fen
import engine.Game

class GameToFen(game: Game) : Adapter<Game, Fen>(game) {
    override fun adapt(input: Game, context: Any?): Fen {
        var fenSquares: String = ""

        val squares = input.boardRep.getSquares()

        var lastSquareIdx = 63
        for (x in 0..7) {
            var empty = 0
            for (y in 7 downTo 0) {
                val squareIdx: Byte = (lastSquareIdx - y).toByte()
                val piece = squares[squareIdx]
                if (piece == null) {
                    empty++
                } else {
                    fenSquares += "${if (empty != 0) empty else ""}$piece"
                    empty = 0
                }
            }
            lastSquareIdx -= 8
            if (empty > 0) fenSquares += empty
            if (x != 7) fenSquares += "/"
        }

        val fenElements = listOf(
            fenSquares,
            if(input.data.turn == Color.WHITE) "w" else "b",
            input.data.castleAvail,
            input.data.enPassantTarget,
            input.data.halfMoveClock,
            input.data.fullMoveClock
        )

        return Fen(fenElements.joinToString(" "))
    }
}