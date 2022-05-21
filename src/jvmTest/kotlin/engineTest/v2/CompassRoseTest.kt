package engineTest.v2

import engine.v2.CompassRose
import engine.v2.Direction
import engine.v2.Square
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class CompassRoseTest {
    private val directions = Direction.values()
    private val cardinalDirections = listOf(
        Direction.N,
        Direction.NE,
        Direction.E,
        Direction.SE,
        Direction.S,
        Direction.SW,
        Direction.W,
        Direction.NW
    )

    @Test
    fun navigate() {
    }

    @Test
    fun waypoint() {
    }

    @Test
    fun ray() {
        for (direction in cardinalDirections) {
            val tests = when (direction) {
                Direction.N -> northRayTests
                Direction.NE -> northEastRayTests
                Direction.E -> eastRayTests
                Direction.SE -> southEastRayTests
                Direction.S -> southRayTests
                Direction.SW -> southWestRayTests
                Direction.W -> westRayTests
                Direction.NW -> northWestRayTests
                else -> throw IllegalArgumentException("Unknown direction")
            }
            for (test in tests) {
                assertTrue("${direction.name} - ${test.first.toString(16)} - ${test.second.toString(16)}") {
                    CompassRose.ray(test.first, direction) == test.second
                }
            }
        }
    }

    @Test
    fun determineRayLength() {
    }

    @Test
    fun isOnMap() {
    }

    @Test
    fun knightMoveTargets() {
    }

    @Test
    fun kingMoveTargets() {
        var result: ULong = CompassRose.kingMoveTargets(Square.a1)
        assertEquals(0x302UL, result, "SW Corner\n\nExpected: 0x302UL\nReceived: 0x${result.toString(16)}UL")
        result = CompassRose.kingMoveTargets(Square.h1)
        assertEquals(0xC040UL, result, "SE Corner\n\nExpected: 0xC040UL\nReceived: 0x${result.toString(16)}UL")
        result = CompassRose.kingMoveTargets(Square.a8)
        assertEquals(0x203000000000000UL, result, "NW Corner\n\nExpected: 0x203000000000000UL\nReceived: 0x${result.toString(16)}UL")
        result = CompassRose.kingMoveTargets(Square.h8)
        assertEquals(0x40c0000000000000UL, result, "NE Corner\n\nExpected: 0x40c0000000000000UL\nReceived: 0x${result.toString(16)}UL")
        result = CompassRose.kingMoveTargets(Square.a4)
        assertEquals(0x302030000UL, result, "NE Corner\n\nExpected: 0x302030000UL\nReceived: 0x${result.toString(16)}UL")
        result = CompassRose.kingMoveTargets(Square.h4)
        assertEquals(0xc040c00000UL, result, "NE Corner\n\nExpected: 0xc040c00000UL\nReceived: 0x${result.toString(16)}UL")
    }

    @Test
    fun generateStaticRaysBySquare() {
    }
}


private val northEastRayTests = listOf(
    0x1UL to 0x8040201008040200UL,
    0x2UL to 0x80402010080400UL,
    0x4UL to 0x804020100800UL,
    0x8UL to 0x8040201000UL,
    0x10UL to 0x80402000UL,
    0x20UL to 0x804000UL,
    0x40UL to 0x8000UL,
    0x80UL to 0UL,
    0x8000UL to 0UL,
    0x800000UL to 0UL,
    0x80000000UL to 0UL,
    0x8000000000UL to 0UL,
    0x800000000000UL to 0UL,
    0x80000000000000UL to 0UL,
    0x8000000000000000UL to 0UL
)

private val northWestRayTests = listOf(
    0x1UL to 0UL,
    0x2UL to 0x100UL,
    0x4UL to 0x10200UL,
    0x8UL to 0x1020400UL,
    0x10UL to 0x102040800UL,
    0x20UL to 0x10204081000UL,
    0x40UL to 0x1020408102000UL,
    0x80UL to 0x102040810204000UL,
    0x100UL to 0UL,
    0x10000UL to 0UL,
    0x1000000UL to 0UL,
    0x100000000UL to 0UL,
    0x10000000000UL to 0UL,
    0x1000000000000UL to 0UL,
    0x100000000000000UL to 0UL
)

private val southEastRayTests = listOf(
    0x100000000000000UL to 0x2040810204080UL,
    0x200000000000000UL to 0x4081020408000UL,
    0x400000000000000UL to 0x8102040800000UL,
    0x800000000000000UL to 0x10204080000000UL,
    0x1000000000000000UL to 0x20408000000000UL,
    0x2000000000000000UL to 0x40800000000000UL,
    0x4000000000000000UL to 0x80000000000000UL,
    0x8000000000000000UL to 0UL,
    0x80UL to 0UL,
    0x8000UL to 0UL,
    0x800000UL to 0UL,
    0x80000000UL to 0UL,
    0x8000000000UL to 0UL,
    0x800000000000UL to 0UL,
    0x80000000000000UL to 0UL,
    0x8000000000000000UL to 0UL
)

private val southWestRayTests = listOf(
    0x100000000000000UL to 0UL,
    0x200000000000000UL to 0x1000000000000UL,
    0x400000000000000UL to 0x2010000000000UL,
    0x800000000000000UL to 0x4020100000000UL,
    0x1000000000000000UL to 0x8040201000000UL,
    0x2000000000000000UL to 0x10080402010000UL,
    0x4000000000000000UL to 0x20100804020100UL,
    0x8000000000000000UL to 0x40201008040201UL,
    0x100UL to 0UL,
    0x10000UL to 0UL,
    0x1000000UL to 0UL,
    0x100000000UL to 0UL,
    0x10000000000UL to 0UL,
    0x1000000000000UL to 0UL,
    0x100000000000000UL to 0UL
)

private val northRayTests = listOf(
    0x1UL to 0x101010101010100UL,
    0x100UL to 0x101010101010000UL,
    0x10000UL to 0x101010101000000UL,
    0x1000000UL to 0x101010100000000UL,
    0x100000000UL to 0x101010000000000UL,
    0x10000000000UL to 0x101000000000000UL,
    0x1000000000000UL to 0x100000000000000UL,
    0x100000000000000UL to 0UL
)

private val southRayTests = listOf(
    0x100000000000000UL to 0x1010101010101UL,
    0x1000000000000UL to 0x10101010101UL,
    0x10000000000UL to 0x101010101UL,
    0x100000000UL to 0x1010101UL,
    0x1000000UL to 0x10101UL,
    0x10000UL to 0x101UL,
    0x100UL to 0x1UL,
    0x1UL to 0UL,
)

private val eastRayTests = listOf(
    0x1UL to 0xFEUL,
    0x2UL to 0xFCUL,
    0x4UL to 0xF8UL,
    0x8UL to 0xF0UL,
    0x10UL to 0xE0UL,
    0x20UL to 0xC0UL,
    0x40UL to 0x80UL,
    0x80UL to 0UL,
    0x8000UL to 0UL,
    0x800000UL to 0UL,
    0x80000000UL to 0UL,
    0x8000000000UL to 0UL,
    0x800000000000UL to 0UL,
    0x80000000000000UL to 0UL,
    0x8000000000000000UL to 0UL
)

private val westRayTests = listOf(
    0x1UL to 0UL,
    0x2UL to 0x1UL,
    0x4UL to 0x3UL,
    0x8UL to 0x7UL,
    0x10UL to 0xfUL,
    0x20UL to 0x1fUL,
    0x40UL to 0x3fUL,
    0x80UL to 0x7fUL,
    0x100UL to 0UL,
    0x10000UL to 0UL,
    0x1000000UL to 0UL,
    0x100000000UL to 0UL,
    0x10000000000UL to 0UL,
    0x1000000000000UL to 0UL,
    0x100000000000000UL to 0UL
)