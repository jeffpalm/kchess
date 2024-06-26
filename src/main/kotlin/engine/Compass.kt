package engine

import engine.adapter.BitsToListOfBit
import engine.move.Magic

object Compass {
    fun navigate(start: ULong, direction: Direction, steps: Int = 1): ULong {
        if (steps < 1) return start
        return when (direction) {
            Direction.NW -> start.shl(7 * steps)
            Direction.N -> start.shl(8 * steps)
            Direction.NE -> start.shl(9 * steps)
            Direction.E -> start.shl(1 * steps)
            Direction.SE -> start.shr(7 * steps)
            Direction.S -> start.shr(8 * steps)
            Direction.SW -> start.shr(9 * steps)
            Direction.W -> start.shr(1 * steps)
            Direction.NNE -> start.shl(17 * steps) and Sets.NOT_A_FILE
            Direction.NEE -> start.shl(10 * steps) and Sets.NOT_AB_FILE
            Direction.SEE -> start.shr(6 * steps) and Sets.NOT_AB_FILE
            Direction.SSE -> start.shr(15 * steps) and Sets.NOT_A_FILE
            Direction.NNW -> start.shl(15 * steps) and Sets.NOT_H_FILE
            Direction.NWW -> start.shl(6 * steps) and Sets.NOT_GH_FILE
            Direction.SWW -> start.shr(10 * steps) and Sets.NOT_GH_FILE
            Direction.SSW -> start.shr(17 * steps) and Sets.NOT_H_FILE
        }
    }

    fun ray(start: ULong, direction: Direction): ULong {
        var output: ULong = 0UL

        val bits = BitsToListOfBit(start).output

        for (bit in bits) {
            output = output or bit.xor(Magic.Ray[Square[bit], direction])
        }

        return start xor output
    }

    private fun walkRay(start: ULong, direction: Direction, steps: Int = 1): ULong {
        if (steps < 1) return start
        return when (direction) {
            Direction.NW -> walkRay(start or start.shl(7), direction, steps - 1)
            Direction.N -> walkRay(start or start.shl(8), direction, steps - 1)
            Direction.NE -> walkRay(start or start.shl(9), direction, steps - 1)
            Direction.E -> walkRay(start or start.shl(1), direction, steps - 1)
            Direction.SE -> walkRay(start or start.shr(7), direction, steps - 1)
            Direction.S -> walkRay(start or start.shr(8), direction, steps - 1)
            Direction.SW -> walkRay(start or start.shr(9), direction, steps - 1)
            Direction.W -> walkRay(start or start.shr(1), direction, steps - 1)
            else -> throw IllegalArgumentException("Invalid ray direction: $direction")
        }
    }

    // Must be single bit ULong for accurate measurement
    private fun determineRayLength(start: ULong, direction: Direction, i: Int = 1): Int {
        if (start.countOneBits() == 0) return 0
        val nextSquare = navigate(start, direction, i)
        if (nextSquare == 0UL) return i - 1
        when (direction) {
            Direction.E, Direction.NE, Direction.SE -> {
                if ((nextSquare and Sets.A_FILE).countOneBits() == 1) {
                    return i - 1
                }
            }
            Direction.W, Direction.SW, Direction.NW -> {
                if ((nextSquare and Sets.H_FILE).countOneBits() == 1) {
                    return i - 1
                }
            }

            else -> {}
        }

        return if (i > 7) 7 else determineRayLength(start, direction, i + 1)
    }

    fun knightMoveTargets(start: ULong): ULong {
        return navigate(start, Direction.NNE) or navigate(start, Direction.NNW) or
                navigate(start, Direction.NEE) or navigate(start, Direction.SEE) or
                navigate(start, Direction.SSE) or navigate(start, Direction.SSW) or
                navigate(start, Direction.NWW) or navigate(start, Direction.SWW)
    }

    fun kingMoveTargets(start: ULong): ULong {
        return navigate(start, Direction.N) or navigate(start, Direction.NE).and(Sets.NOT_A_FILE) or
                navigate(start, Direction.E).and(Sets.NOT_A_FILE) or navigate(start, Direction.SE).and(Sets.NOT_A_FILE) or
                navigate(start, Direction.S) or navigate(start, Direction.SW).and(Sets.NOT_H_FILE) or
                navigate(start, Direction.W).and(Sets.NOT_H_FILE) or navigate(start, Direction.NW).and(Sets.NOT_H_FILE)
    }

    fun bishopMoveTargets(start: ULong): ULong {
        if (start.countOneBits() != 1) throw IllegalArgumentException("Invalid start square: $start")
        var output: ULong = 0UL
        for (direction in Direction.bishops) {
            output = output or ray(start, direction)
        }
        return output
    }

    fun queenMoveTargets(start: ULong): ULong {
        if (start.countOneBits() != 1) throw IllegalArgumentException("Invalid start square: $start")
        var output: ULong = 0UL
        for (direction in Direction.sliding) {
            output = output or ray(start, direction)
        }
        return output
    }

    fun rookMoveTargets(start: ULong): ULong {
        if (start.countOneBits() != 1) throw IllegalArgumentException("Invalid start square: $start")
        var output: ULong = 0UL
        for (direction in Direction.rooks) {
            output = output or ray(start, direction)
        }
        return output
    }

    fun pawnAttackTargets(start: ULong, color: Color): ULong = when (color) {
        Color.WHITE -> {
            val neAttacks = navigate(start, Direction.NE).and(Sets.NOT_A_FILE)
            val nwAttacks = navigate(start, Direction.NW).and(Sets.NOT_H_FILE)
            if (start.and(Sets.A_FILE) != 0UL) {
                neAttacks or nwAttacks.and(Sets.NOT_H_FILE)
            }
            if (start.and(Sets.H_FILE) != 0UL) {
                nwAttacks or neAttacks.and(Sets.NOT_A_FILE)
            }
            neAttacks or nwAttacks
        }
        Color.BLACK -> {
            val seAttacks = navigate(start, Direction.SE).and(Sets.NOT_A_FILE)
            val swAttacks = navigate(start, Direction.SW).and(Sets.NOT_H_FILE)
            if (start.and(Sets.A_FILE) != 0UL) {
                seAttacks or swAttacks.and(Sets.NOT_H_FILE)
            }
            if (start.and(Sets.H_FILE) != 0UL) {
                swAttacks or seAttacks.and(Sets.NOT_A_FILE)
            }
            seAttacks or swAttacks
        }
    }
}