package engineTest

import engine.Square
import engine.move.AbstractMoveGenerator
import kotlin.test.assertEquals

class MoveTest(moveGenerator: AbstractMoveGenerator, expected: ULong) {
    init {
        val moves = moveGenerator.execute()
        var result = 0UL
        for (move in moves) {
            result = result.or(Square[move.to.ordinal])
        }
        assertEquals(
            expected,
            result,
            "Expected: 0x${expected.toString(16)}UL\nReceived: 0x${result.toString(16)}UL\n\n"
        )
    }
}
