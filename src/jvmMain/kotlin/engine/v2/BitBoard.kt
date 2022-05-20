package engine.v2

class BitBoard(empty: Boolean = false) : IBitBoardPieces {
    var enPassantTarget: ULong? = null
    override var whitePawns: ULong = if (empty) 0UL else Constants.StartPosition.P
    override var whiteKnights: ULong = if (empty) 0UL else Constants.StartPosition.N
    override var whiteBishops: ULong = if (empty) 0UL else Constants.StartPosition.B
    override var whiteRooks: ULong = if (empty) 0UL else Constants.StartPosition.R
    override var whiteQueens: ULong = if (empty) 0UL else Constants.StartPosition.Q
    override var whiteKing: ULong = if (empty) 0UL else Constants.StartPosition.K
    override var blackPawns: ULong = if (empty) 0UL else Constants.StartPosition.p
    override var blackKnights: ULong = if (empty) 0UL else Constants.StartPosition.n
    override var blackBishops: ULong = if (empty) 0UL else Constants.StartPosition.b
    override var blackRooks: ULong = if (empty) 0UL else Constants.StartPosition.r
    override var blackQueens: ULong = if (empty) 0UL else Constants.StartPosition.q
    override var blackKing: ULong = if (empty) 0UL else Constants.StartPosition.k

    override fun pieceList(): List<Pair<Char, ULong>> = listOf(
       'P' to whitePawns,
       'N' to whiteKnights,
       'B' to whiteBishops,
       'R' to whiteRooks,
       'Q' to whiteQueens,
       'K' to whiteKing,
       'p' to blackPawns,
       'n' to blackKnights,
       'b' to blackBishops,
       'r' to blackRooks,
       'q' to blackQueens,
       'k' to blackKing
    )

    fun occupied(color: PieceColor? = null): ULong {
        return when (color) {
            PieceColor.WHITE -> whitePawns or whiteKnights or whiteBishops or whiteRooks or whiteQueens or whiteKing
            PieceColor.BLACK -> blackPawns or blackKnights or blackBishops or blackRooks or blackQueens or blackKing
            else -> whitePawns or whiteKnights or whiteBishops or whiteRooks or whiteQueens or whiteKing or blackPawns or blackKnights or blackBishops or blackRooks or blackQueens or blackKing
        }
    }

    fun empty(): ULong {
        return 0xffffffffffffffffUL xor occupied()
    }

    fun rayMoves(x: ULong, direction: Direction, color: PieceColor): ULong {
        var moves = CompassRose.ray(x, direction)
        val blocker = moves and occupied()

        if (blocker != 0UL) {
            var square = when (direction) {
                Direction.NW, Direction.N, Direction.NE, Direction.E -> {
                    blocker.takeLowestOneBit()
                }
                Direction.SE, Direction.S, Direction.SW, Direction.W -> {
                    blocker.takeHighestOneBit()
                }
                else -> throw IllegalArgumentException("Direction must be one of N, NE, E, SE, S, SW, W, NW")
            }
            val ray = CompassRose.ray(square, direction)
            moves = if ((blocker and occupied(color)).countOneBits() > 0) {
                moves xor square.or(ray)
            } else {
                moves xor ray
            }
        }
        return moves
    }

    companion object {
        fun rotate90(x: ULong, clockWise: Boolean = true): ULong {
            return if (clockWise) flipVertical(flipDiagA1H8(x)) else Companion.flipDiagA1H8(
                flipVertical(x)
            )
        }

        fun rotate180(x: ULong): ULong {
            return mirrorHorizontal(flipVertical(x))
        }

        fun flipDiagA1H8(_x: ULong): ULong {
            var x = _x
            var t: ULong
            val k1 = 0X5500550055005500UL
            val k2 = 0X3333000033330000UL
            val k4 = 0X0F0F0F0F00000000UL

            t = k4.and(x.xor(x.shl(28)))
            x = x.xor(t.xor(t.shr(28)))
            t = k2.and(x.xor(x.shl(14)))
            x = x.xor(t.xor(t.shr(14)))
            t = k1.and(x.xor(x.shl(7)))
            x = x.xor(t.xor(t.shr(7)))

            return x
        }

        fun flipDiagA8H1(_x: ULong): ULong {
            var x = _x
            var t: ULong
            val k1 = 0XAA00AA00AA00AA00UL
            val k2 = 0XCCCC0000CCCC0000UL
            val k4 = 0XF0F0F0F00F0F0F0FUL

            t = x.xor(x.shl(36))
            x = x.xor(k4.and(t.xor(x.shr(36))))
            t = k2.and(x.xor(x.shl(18)))
            x = x.xor(t.xor(t.shr(18)))
            t = k1.and(x.xor(x.shl(9)))
            x = x.xor(t.xor(t.shr(9)))

            return x
        }

        fun mirrorHorizontal(_x: ULong): ULong {
            var x = _x
            val k1 = 0x5555555555555555UL
            val k2 = 0x3333333333333333UL
            val k4 = 0X0F0F0F0F0F0F0F0FUL

            x = x.shr(1).and(k1).plus(x.and(k1).times(2.toULong()))
            x = x.shr(2).and(k2).plus(x.and(k2).times(4.toULong()))
            x = x.shr(4).and(k4).plus(x.and(k4).times(16.toULong()))

            return x
        }

        fun flipVertical(_x: ULong): ULong {
            var x = _x
            val k1 = 0x00FF00FF00FF00FFUL
            val k2 = 0x0000FFFF0000FFFFUL

            x = x.shr(8).and(k1).or(x.and(k1).shl(8))
            x = x.shr(16).and(k2).or(x.and(k2).shl(16))
            x = x.shr(32).or(x.shl(32))

            return x
        }
    }
}