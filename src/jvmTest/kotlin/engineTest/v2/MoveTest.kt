package engineTest.v2

import engine.v2.moves.AbstractMoveGenerator
import kotlin.test.assertEquals

class MoveTest(moveGenerator: AbstractMoveGenerator, expected: ULong) {
    init {
        val moves = moveGenerator.execute()
        var result = 0UL
        for (move in moves) {
            result = result.or(move.toBit())
        }
        assertEquals(
            expected,
            result,
            "Expected: 0x${expected.toString(16)}UL\nReceived: 0x${result.toString(16)}UL\n\n"
        )
    }
}
