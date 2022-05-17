package engine.v1

import kotlin.math.abs

data class Movement(val from: Coords, val to: Coords) {
    val deltaX: Int = (to.x - from.x)
    val deltaY: Int = (to.y - from.y)
    val distanceX: Int = abs(deltaX)
    val distanceY: Int = abs(deltaY)
    val trajectory: MoveTrajectory? = determineDirection()

    private fun determineDirection(): MoveTrajectory? {
        return when {
            distanceX == distanceY -> MoveTrajectory.DIAGONAL
            deltaY == 0 && deltaX != 0 -> MoveTrajectory.HORIZONTAL
            deltaX == 0 && deltaY != 0 -> MoveTrajectory.VERTICAL
            distanceY == 2 && distanceX == 1 -> MoveTrajectory.KNIGHT
            distanceY == 1 && distanceX == 2 -> MoveTrajectory.KNIGHT
            else -> null
        }
    }
}
