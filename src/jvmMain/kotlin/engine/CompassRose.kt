package engine

object CompassRose {
    const val NW = 7
    const val N = 8
    const val NE = 9
    const val E = 1
    const val SE = -7
    const val S = -8
    const val SW = -9
    const val W = -1
    const val NNE = 17
    const val NEE = 10
    const val SEE = -6
    const val SSE = -15
    const val SSW = -17
    const val SWW = -10
    const val NWW = 6
    const val NNW = 15

    fun navigate(start: ULong, direction: Direction, steps: Int = 1): ULong {
        if (steps < 1) return start
        return when (direction) {
            Direction.N -> start.shl(8 * steps)
            Direction.NE -> start.shl(9 * steps)
            Direction.E -> start.shl(1 * steps)
            Direction.SE -> start.shr(7 * steps)
            Direction.S -> start.shr(8 * steps)
            Direction.SW -> start.shr(9 * steps)
            Direction.W -> start.shr(1 * steps)
            Direction.NW -> start.shl(7 * steps)
            Direction.NNE -> start.shl(17 * steps) and Sets.NOT_A_FILE
            Direction.NEE -> start.shl(10 * steps) and Sets.NOT_AB_FILE
            Direction.SEE -> start.shr(6 * steps) and Sets.NOT_AB_FILE
            Direction.SSE -> start.shr(15 * steps) and Sets.NOT_A_FILE
            Direction.NNW -> start.shl(15 * steps) and Sets.NOT_H_FILE
            Direction.NWW -> start.shl(6 * steps) and Sets.NOT_GH_FILE
            Direction.SWW -> start.shr(10 * steps) and Sets.NOT_GH_FILE
            Direction.SSW -> start.shr(17 * steps) and Sets.NOT_H_FILE
        }
    }

    fun waypoint(start: ULong, direction: Direction, steps: Int = 1): ULong {
        if (steps < 1) return start
        return when (direction) {
            Direction.N -> start.or(start.shl(8 * steps))
            Direction.NE -> start.or(start.shl(9 * steps))
            Direction.E -> start.or(start.shl(1 * steps))
            Direction.SE -> start.or(start.shr(7 * steps))
            Direction.S -> start.or(start.shr(8 * steps))
            Direction.SW -> start.or(start.shr(9 * steps))
            Direction.W -> start.or(start.shr(1 * steps))
            Direction.NW -> start.or(start.shl(7 * steps))
            Direction.NNE -> start.or(start.shl(17 * steps))
            Direction.NEE -> start.or(start.shl(10 * steps))
            Direction.SEE -> start.or(start.shr(6 * steps))
            Direction.SSE -> start.or(start.shr(15 * steps))
            Direction.SSW -> start.or(start.shr(17 * steps))
            Direction.SWW -> start.or(start.shr(10 * steps))
            Direction.NWW -> start.or(start.shl(6 * steps))
            Direction.NNW -> start.or(start.shl(15 * steps))
        }
    }

    fun ray(start: ULong, direction: Direction): ULong {
        val steps = determineRayLength(start, direction)
        return start xor runRay(start, direction, steps)
    }

    private fun runRay(start: ULong, direction: Direction, steps: Int = 1): ULong {
        if (steps < 1) return start
        return when (direction) {
            Direction.NW -> runRay(start or start.shl(7), direction, steps - 1)
            Direction.N -> runRay(start or start.shl(8), direction, steps - 1)
            Direction.NE -> runRay(start or start.shl(9), direction, steps - 1)
            Direction.E -> runRay(start or start.shl(1), direction, steps - 1)
            Direction.SE -> runRay(start or start.shr(7), direction, steps - 1)
            Direction.S -> runRay(start or start.shr(8), direction, steps - 1)
            Direction.SW -> runRay(start or start.shr(9), direction, steps - 1)
            Direction.W -> runRay(start or start.shr(1), direction, steps - 1)
            else -> throw IllegalArgumentException("Invalid ray direction: $direction")
        }
    }

    // Must be single bit ULong for accurate measurement
    fun determineRayLength(start: ULong, direction: Direction, i: Int = 1): Int {
        if (start.countOneBits() == 0) return 0
        val nextSquare = navigate(start, direction, i)
        if (nextSquare == 0UL) return i - 1
        when (direction) {
            Direction.E, Direction.NE, Direction.SE -> {
                if ((nextSquare and Sets.A_FILE).countOneBits() == 1) {
                    return i - 1
                }
            }
            Direction.W, Direction.SW, Direction.NW -> {
                if ((nextSquare and Sets.H_FILE).countOneBits() == 1) {
                    return i - 1
                }
            }
        }

        return if (i > 7) 7 else determineRayLength(start, direction, i + 1)
    }

    fun knightMoveTargets(start: ULong): ULong {
        return navigate(start, Direction.NNE) or navigate(start, Direction.NNW) or
                navigate(start, Direction.NEE) or navigate(start, Direction.SEE) or
                navigate(start, Direction.SSE) or navigate(start, Direction.SSW) or
                navigate(start, Direction.NWW) or navigate(start, Direction.SWW)
    }

    fun kingMoveTargets(start: ULong): ULong {
        return navigate(start, Direction.N) or navigate(start, Direction.NE).and(Sets.NOT_A_FILE) or
                navigate(start, Direction.E).and(Sets.NOT_A_FILE) or navigate(start, Direction.SE).and(Sets.NOT_A_FILE) or
                navigate(start, Direction.S) or navigate(start, Direction.SW).and(Sets.NOT_H_FILE) or
                navigate(start, Direction.W).and(Sets.NOT_H_FILE) or navigate(start, Direction.NW).and(Sets.NOT_H_FILE)
    }

    fun pawnAttackTargets(start: ULong, color: Color): ULong = when (color) {
        Color.WHITE -> navigate(start, Direction.NE).and(Sets.NOT_A_FILE) or navigate(start, Direction.NW).and(Sets.NOT_H_FILE)
        Color.BLACK -> navigate(start, Direction.SE).and(Sets.NOT_A_FILE) or navigate(start, Direction.SW).and(Sets.NOT_H_FILE)
    }
}