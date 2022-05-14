import kotlin.math.abs

data class PotentialMove (val from: Coords, val to: Coords) {
    val deltaX: Int = (to.x - from.x)
    val deltaY: Int = (to.y - from.y)
    val distanceX: UInt = abs(deltaX.toInt()).toUInt()
    val distanceY: UInt = abs(deltaY.toInt()).toUInt()
    val trajectory: MoveTrajectory? = determineDirection()

    private fun determineDirection(): MoveTrajectory? {
        return when {
            distanceX == distanceY -> MoveTrajectory.DIAGONAL
            deltaY == 0 && deltaX != 0 -> MoveTrajectory.HORIZONTAL
            deltaX == 0 && deltaY != 0 -> MoveTrajectory.VERTICAL
            distanceY == 2.toUInt() && distanceX == 1.toUInt() -> MoveTrajectory.KNIGHT
            else -> null
        }
    }
}
