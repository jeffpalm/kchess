package engine

import engine.adapter.BitBoardToBoardSquares
import engine.adapter.FenToBoardRep
import engine.adapter.WordToBoardSquares

typealias BoardSquares = Map<Byte, Char?>

class Board(input: Any = Fen()) {
    private val squares: Array<Char?> = arrayOfNulls(64)

    init {
        @Suppress("UNCHECKED_CAST")
        val src: Map<Byte, Char?> = when (input) {
            is BitBoard -> BitBoardToBoardSquares(input).output
            is IBitBoardPieces -> BitBoardToBoardSquares(input).output
            is Fen -> FenToBoardRep(input).output
            is String -> FenToBoardRep(Fen(input)).output
            is ULong -> WordToBoardSquares(input).output
            is Map<*, *> -> input as Map<Byte, Char?>
            is MutableMap<*, *> -> input as Map<Byte, Char?>
            else -> throw IllegalArgumentException("Invalid input board input: $input")
        }
        for ((k, v) in src) squares[k.toInt()] = v
    }

    fun log(note: String? = null) {
        if (note != null) {
            println(note)
        }
        var idx = 63
        for (i in 0..7) {
            print("  ")
            for (j in 7 downTo 0) {
                val piece = squares[idx - j]
                print(if (piece == null) " . " else " $piece ")
            }
            idx -= 8
            println()
        }
        println("--------------------------")
    }

    fun getPiecesByType(type: Char): List<Byte> {
        val out = ArrayList<Byte>()
        for (i in 0 until 64) if (squares[i] == type) out.add(i.toByte())
        return out
    }

    fun setSquare(sq: Square, value: Char? = null) {
        squares[sq.ordinal] = value
    }

    fun getPiece(sq: Square): Char? = squares[sq.ordinal]

    /**
     * Returns the squares as a Map. Allocates a fresh map; only used by
     * non-hot paths (e.g. FEN serialization).
     */
    fun getSquares(): BoardSquares {
        val m = HashMap<Byte, Char?>(64)
        for (i in 0 until 64) m[i.toByte()] = squares[i]
        return m
    }

    fun clone(): Board {
        val c = Board(emptyMap<Byte, Char?>())
        System.arraycopy(squares, 0, c.squares, 0, 64)
        return c
    }

    companion object {
        val emptySquares: Map<Byte, Char?> = HashMap<Byte, Char?>(64).apply {
            for (i in 0 until 64) this[i.toByte()] = null
        }
    }
}
