import kotlin.math.abs

data class PotentialMove (val from: Coords, val to: Coords) {
    val deltaX: Byte = (to.x - from.x).toByte()
    val deltaY: Byte = (to.y - from.y).toByte()
    val distanceX: UByte = abs(deltaX.toInt()).toUByte()
    val distanceY: UByte = abs(deltaY.toInt()).toUByte()
    val trajectory: MoveTrajectory? = determineDirection()

    private fun determineDirection(): MoveTrajectory? {
        return when {
            distanceX == distanceY -> MoveTrajectory.DIAGONAL
            deltaY == 0.toByte() && deltaX != 0.toByte() -> MoveTrajectory.HORIZONTAL
            deltaX == 0.toByte() && deltaY != 0.toByte() -> MoveTrajectory.VERTICAL
            distanceY == 2.toUByte() && distanceX == 1.toUByte() -> MoveTrajectory.KNIGHT
            else -> null
        }
    }
}
