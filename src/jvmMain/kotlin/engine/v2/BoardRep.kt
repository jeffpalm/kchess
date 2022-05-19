package engine.v2

import engine.Fen
import engine.v2.adapters.FenToBoardRep
import engine.v2.adapters.WordToBoardSquares

class BoardRep(input: Any) {
    private val squares: MutableMap<Byte, Char?>

    init {
        squares = when (input) {
            is Fen -> FenToBoardRep(input).output
            is ULong -> WordToBoardSquares(input).output
            is MutableMap<*, *> -> input as MutableMap<Byte, Char?>
            else -> throw IllegalArgumentException("Invalid input board rep input: $input")
        }
    }

    fun print() {
        println()
        var idx = 63
        for (i in 0..7) {
            print("    ")
            for (j in 7 downTo 0) {
                print(if (squares[(idx - j).toByte()] == null) " . " else " ${squares[(idx - j).toByte()]} ")
            }
            idx -= 8
            println()
        }
    }

    fun getPiecesByType(type: Char): List<Byte> {
        return squares.filter { it.value == type }.keys.toList()
    }

    fun setSquare(index: Byte, value: Char? = null) {
        squares[index] = value
    }

    fun setSquare(sq: SquareMap, value: Char? = null) {
        squares[sq.ordinal.toByte()] = value
    }

    fun getPiece(sq: SquareMap): Char? {
        return squares[sq.ordinal.toByte()]
    }

    fun getSquares(): Map<Byte, Char?> {
        return squares
    }
}