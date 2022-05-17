package engineTest.adapters

import engine.v2.adapters.BinaryStringToWord
import kotlin.test.Test
import kotlin.test.assertEquals

class TestBinaryStringToWord {
    @Test
    fun testAdapter() {
        val string = "1111111100000000000000000000000000000000000000000000000000000000"
        assertEquals(0xff00000000000000UL, BinaryStringToWord(string).output)
    }
}