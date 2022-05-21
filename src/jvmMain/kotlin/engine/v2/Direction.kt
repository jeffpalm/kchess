package engine.v2

enum class Direction {
    N, S, E, W, NE, NW, SE, SW, NNW, NNE, NWW, NEE, SWW, SSW, SSE, SEE;

    companion object {
        fun inv(direction: Direction) = when (direction) {
            N -> S
            S -> N
            E -> W
            W -> E
            NE -> SW
            NW -> SE
            SE -> NW
            SW -> NE
            NNW -> SEE
            NNE -> SSW
            NWW -> SSE
            NEE -> SWW
            SWW -> NEE
            SSW -> NWW
            SSE -> NNW
            SEE -> NNE
        }

        val sliding: List<Direction> = listOf(
            N, S, E, W, NE, NW, SE, SW
        )
        val bishops: List<Direction> = listOf(
            NE, NW, SE, SW
        )
        val rooks: List<Direction> = listOf(
            N, S, E, W
        )
        val knights: List<Direction> = listOf(
            NNW, NNE, NWW, NEE, SWW, SSW, SSE, SEE
        )
        val positive: List<Direction> = listOf(
            NW, N, NE, E
        )
        val negative: List<Direction> = listOf(
            SE, S, SW, W
        )

        fun getClosestBit(direction: Direction, word: ULong) = when (direction) {
            in positive -> word.takeLowestOneBit()
            in negative -> word.takeHighestOneBit()
            else -> throw IllegalArgumentException("Direction must be positive or negative")
        }
    }
}