package engine.v2

object CompassRose {
    const val NORTH = 8
    const val NORTHEAST = 9
    const val EAST = 1
    const val SOUTHEAST = (-7)
    const val SOUTH = (-8)
    const val SOUTHWEST = (-9)
    const val WEST = (-1)
    const val NORTHWEST = 7

    fun navigate(start: ULong, direction: Direction, steps: Int = 1): ULong {
        if (steps < 1) return start
        return when (direction) {
            Direction.NORTH -> navigate(start.shl(8), direction, steps - 1)
            Direction.NORTHEAST -> navigate(start.shl(9), direction, steps - 1)
            Direction.EAST -> navigate(start.shl(1), direction, steps - 1)
            Direction.SOUTHEAST -> navigate(start.shr(7), direction, steps - 1)
            Direction.SOUTH -> navigate(start.shr(8), direction, steps - 1)
            Direction.SOUTHWEST -> navigate(start.shr(9), direction, steps - 1)
            Direction.WEST -> navigate(start.shr(1), direction, steps - 1)
            Direction.NORTHWEST -> navigate(start.shl(7), direction, steps - 1)
        }
    }

    fun waypoint(start: ULong, direction: Direction, steps: Int = 1): ULong {
        if (steps < 1) return start
        return when (direction) {
            Direction.NORTH -> start.or(start.shl(8 * steps))
            Direction.NORTHEAST -> start.or(start.shl(9 * steps))
            Direction.EAST -> start.or(start.shl(1 * steps))
            Direction.SOUTHEAST -> start.or(start.shr(7 * steps))
            Direction.SOUTH -> start.or(start.shr(8 * steps))
            Direction.SOUTHWEST -> start.or(start.shr(9 * steps))
            Direction.WEST -> start.or(start.shr(1 * steps))
            Direction.NORTHWEST -> start.or(start.shl(7 * steps))
        }
    }

    fun vector(start: ULong, direction: Direction, steps: Int = 1): ULong {
        if (steps < 1) return start
        return when (direction) {
            Direction.NORTH -> vector(start.or(start.shl(8)), direction, steps - 1)
            Direction.NORTHEAST -> vector(start.or(start.shl(9)), direction, steps - 1)
            Direction.EAST -> vector(start.or(start.shl(1)), direction, steps - 1)
            Direction.SOUTHEAST -> vector(start.or(start.shr(7)), direction, steps - 1)
            Direction.SOUTH -> vector(start.or(start.shr(8)), direction, steps - 1)
            Direction.SOUTHWEST -> vector(start.or(start.shr(9)), direction, steps - 1)
            Direction.WEST -> vector(start.or(start.shr(1)), direction, steps - 1)
            Direction.NORTHWEST -> vector(start.or(start.shl(7)), direction, steps - 1)
        }
    }


    fun isOnMap(word: ULong): Boolean {
        return word != 0UL
    }
}