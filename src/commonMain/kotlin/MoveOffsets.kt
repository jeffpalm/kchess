object MoveOffsets {
    val knight: List<Pair<Int, Int>> =
        listOf(Pair(1, 2), Pair(1, -2), Pair(-1, 2), Pair(-1, -2), Pair(2, 1), Pair(2, -1), Pair(-2, 1), Pair(-2, -1))

    val whitePawn: List<Pair<Int, Int>> = listOf(Pair(0, -1), Pair(0, -2), Pair(-1, -1), Pair(1, -1))

    val blackPawn: List<Pair<Int, Int>> = listOf(Pair(0, 1), Pair(0, 2), Pair(-1, 1), Pair(1, 1))

}