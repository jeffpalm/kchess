class BoardToFenAdapter(board: Map<Int, Map<Int, Square>>) {
    val fenBoardRepresentation: String

    init {
        var fenBoard = ""
        for (y in 0..7) {
            var emptyCount = 0
            for (x in 0..7) {
                val row = board[y] ?: throw IllegalArgumentException("Board is not valid")
                val square = row[x] ?: throw IllegalArgumentException("Board row is not valid")
                if (square.piece != null) {
                    fenBoard += if (emptyCount > 0) emptyCount.toString() + square.piece!!.symbol else square.piece!!.symbol
                    emptyCount = 0
                    continue
                }
                emptyCount++
            }
            if (emptyCount > 0) fenBoard += emptyCount.toString()
            if (y < 7) fenBoard += "/"
        }
        fenBoardRepresentation = fenBoard
    }
}