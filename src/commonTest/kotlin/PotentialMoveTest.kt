import kotlin.test.Test
import kotlin.test.assertEquals

class PotentialMoveTest {
    @Test
    fun testDeterminesCorrectTrajectory() {
        assertEquals(PotentialMove(Coords(0, 0), Coords(1, 0)).trajectory, MoveTrajectory.HORIZONTAL)
        assertEquals(PotentialMove(Coords(7, 0), Coords(1, 0)).trajectory, MoveTrajectory.HORIZONTAL)
        assertEquals(PotentialMove(Coords(0, 0), Coords(0, 1)).trajectory, MoveTrajectory.VERTICAL)
        assertEquals(PotentialMove(Coords(0, 0), Coords(1, 1)).trajectory, MoveTrajectory.DIAGONAL)
        assertEquals(PotentialMove(Coords(7, 7), Coords(6, 6)).trajectory, MoveTrajectory.DIAGONAL)
        assertEquals(PotentialMove(Coords(0, 0), Coords(1, 4)).trajectory, null)
    }
}
