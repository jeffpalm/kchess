class FenToBoardAdapter(private val fen: Fen) {
    val board: Map<Int, Map<Int, Square>> = mapOf(
        0 to mapOf(
            0 to Square(0, 0, "a8"),
            1 to Square(0, 1, "b8"),
            2 to Square(0, 2, "c8"),
            3 to Square(0, 3, "d8"),
            4 to Square(0, 4, "e8"),
            5 to Square(0, 5, "f8"),
            6 to Square(0, 6, "g8"),
            7 to Square(0, 7, "h8")
        ),
        1 to mapOf(
            0 to Square(1, 0, "a7"),
            1 to Square(1, 1, "b7"),
            2 to Square(1, 2, "c7"),
            3 to Square(1, 3, "d7"),
            4 to Square(1, 4, "e7"),
            5 to Square(1, 5, "f7"),
            6 to Square(1, 6, "g7"),
            7 to Square(1, 7, "h7")
        ),
        2 to mapOf(
            0 to Square(2, 0, "a6"),
            1 to Square(2, 1, "b6"),
            2 to Square(2, 2, "c6"),
            3 to Square(2, 3, "d6"),
            4 to Square(2, 4, "e6"),
            5 to Square(2, 5, "f6"),
            6 to Square(2, 6, "g6"),
            7 to Square(2, 7, "h6")
        ),
        3 to mapOf(
            0 to Square(3, 0, "a5"),
            1 to Square(3, 1, "b5"),
            2 to Square(3, 2, "c5"),
            3 to Square(3, 3, "d5"),
            4 to Square(3, 4, "e5"),
            5 to Square(3, 5, "f5"),
            6 to Square(3, 6, "g5"),
            7 to Square(3, 7, "h5")
        ),
        4 to mapOf(
            0 to Square(4, 0, "a4"),
            1 to Square(4, 1, "b4"),
            2 to Square(4, 2, "c4"),
            3 to Square(4, 3, "d4"),
            4 to Square(4, 4, "e4"),
            5 to Square(4, 5, "f4"),
            6 to Square(4, 6, "g4"),
            7 to Square(4, 7, "h4")
        ),
        5 to mapOf(
            0 to Square(5, 0, "a3"),
            1 to Square(5, 1, "b3"),
            2 to Square(5, 2, "c3"),
            3 to Square(5, 3, "d3"),
            4 to Square(5, 4, "e3"),
            5 to Square(5, 5, "f3"),
            6 to Square(5, 6, "g3"),
            7 to Square(5, 7, "h3")
        ),
        6 to mapOf(
            0 to Square(6, 0, "a2"),
            1 to Square(6, 1, "b2"),
            2 to Square(6, 2, "c2"),
            3 to Square(6, 3, "d2"),
            4 to Square(6, 4, "e2"),
            5 to Square(6, 5, "f2"),
            6 to Square(6, 6, "g2"),
            7 to Square(6, 7, "h2")
        ),
        7 to mapOf(
            0 to Square(7, 0, "a1"),
            1 to Square(7, 1, "b1"),
            2 to Square(7, 2, "c1"),
            3 to Square(7, 3, "d1"),
            4 to Square(7, 4, "e1"),
            5 to Square(7, 5, "f1"),
            6 to Square(7, 6, "g1"),
            7 to Square(7, 7, "h1")
        )
    )

    private val fenNumbers: List<Char> = listOf('8', '7', '6', '5', '4', '3', '2', '1')

    init {
        val fenRows = fen.boardRepresentation.split("/").map { it.toCharArray() }

        for (y in 0..7) {
            if (fenRows[y][0] == '8') {
                continue
            }
            val boardRow = board[y] ?: throw IllegalStateException("Row $y is null")
            var x = 0
            val fenRow = fenRows[y]
            fenRow.iterator().forEach { fenChar ->
                if (fenChar == '8') return@forEach

                if (fenChar in fenNumbers) {
                    x += fenChar.digitToInt()
                    return@forEach
                }
                val square = boardRow[x] ?: throw IllegalStateException("Square $x,$y is null")
                square.piece = FenToPieceAdapter(fenChar).piece
                x++
            }
        }
    }
}
