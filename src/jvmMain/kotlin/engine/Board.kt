package engine

import engine.adapter.BitBoardToBoardSquares
import engine.adapter.FenToBoardRep
import engine.adapter.WordToBoardSquares

class Board(input: Any = Fen()) {
    private val squares: MutableMap<Byte, Char?>

    init {
        squares = when (input) {
            is BitBoard -> BitBoardToBoardSquares(input).output
            is IBitBoardPieces -> BitBoardToBoardSquares(input).output
            is Fen -> FenToBoardRep(input).output
            is String -> FenToBoardRep(Fen(input)).output
            is ULong -> WordToBoardSquares(input).output
            is MutableMap<*, *> -> input as MutableMap<Byte, Char?>
            else -> throw IllegalArgumentException("Invalid input board input: $input")
        }
    }

    fun log(note: String? = null) {
        if (note != null) {
            println(note)
        }
        var idx = 63
        for (i in 0..7) {
            print("  ")
            for (j in 7 downTo 0) {
                print(if (squares[(idx - j).toByte()] == null) " . " else " ${squares[(idx - j).toByte()]} ")
            }
            idx -= 8
            println()
        }
        println("--------------------------")
    }

    fun getPiecesByType(type: Char): List<Byte> {
        return squares.filter { it.value == type }.keys.toList()
    }

    fun setSquare(index: Byte, value: Char? = null) {
        squares[index] = value
    }

    fun setSquare(sq: Square, value: Char? = null) {
        squares[sq.ordinal.toByte()] = value
    }

    fun getPiece(sq: Square): Char? {
        return squares[sq.ordinal.toByte()]
    }

    fun getSquares(): Map<Byte, Char?> {
        return squares
    }

    companion object {
        val emptySquares = mapOf<Byte, Char?>(
            0.toByte() to null,
            1.toByte() to null,
            2.toByte() to null,
            3.toByte() to null,
            4.toByte() to null,
            5.toByte() to null,
            6.toByte() to null,
            7.toByte() to null,
            8.toByte() to null,
            9.toByte() to null,
            10.toByte() to null,
            11.toByte() to null,
            12.toByte() to null,
            13.toByte() to null,
            14.toByte() to null,
            15.toByte() to null,
            16.toByte() to null,
            17.toByte() to null,
            18.toByte() to null,
            19.toByte() to null,
            20.toByte() to null,
            21.toByte() to null,
            22.toByte() to null,
            23.toByte() to null,
            24.toByte() to null,
            25.toByte() to null,
            26.toByte() to null,
            27.toByte() to null,
            28.toByte() to null,
            29.toByte() to null,
            30.toByte() to null,
            31.toByte() to null,
            32.toByte() to null,
            33.toByte() to null,
            34.toByte() to null,
            35.toByte() to null,
            36.toByte() to null,
            37.toByte() to null,
            38.toByte() to null,
            39.toByte() to null,
            40.toByte() to null,
            41.toByte() to null,
            42.toByte() to null,
            43.toByte() to null,
            44.toByte() to null,
            45.toByte() to null,
            46.toByte() to null,
            47.toByte() to null,
            48.toByte() to null,
            49.toByte() to null,
            50.toByte() to null,
            51.toByte() to null,
            52.toByte() to null,
            53.toByte() to null,
            54.toByte() to null,
            55.toByte() to null,
            56.toByte() to null,
            57.toByte() to null,
            58.toByte() to null,
            59.toByte() to null,
            60.toByte() to null,
            61.toByte() to null,
            62.toByte() to null,
            63.toByte() to null
        )
    }
}