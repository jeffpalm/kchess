package engine

object CompassRose {
    const val NORTH = 8
    const val NORTH_EAST = 9
    const val EAST = 1
    const val SOUTH_EAST = (-7)
    const val SOUTH = (-8)
    const val SOUTH_WEST = (-9)
    const val WEST = (-1)
    const val NORTH_WEST = 7

    val directions = listOf(
        "NORTH", "NORTH_EAST", "EAST", "SOUTH_EAST", "SOUTH", "SOUTH_WEST", "WEST", "NORTH_WEST"
    )
}