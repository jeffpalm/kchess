package engine

import engine.adapter.BitsToListOfBit

class BitBoard(empty: Boolean = false) : IBitBoardPieces {
    override var wPawns: ULong = if (empty) 0UL else StartPosition.P
    override var wKnights: ULong = if (empty) 0UL else StartPosition.N
    override var wBishops: ULong = if (empty) 0UL else StartPosition.B
    override var wRooks: ULong = if (empty) 0UL else StartPosition.R
    override var wQueens: ULong = if (empty) 0UL else StartPosition.Q
    override var wKing: ULong = if (empty) 0UL else StartPosition.K
    override var bPawns: ULong = if (empty) 0UL else StartPosition.p
    override var bKnights: ULong = if (empty) 0UL else StartPosition.n
    override var bBishops: ULong = if (empty) 0UL else StartPosition.b
    override var bRooks: ULong = if (empty) 0UL else StartPosition.r
    override var bQueens: ULong = if (empty) 0UL else StartPosition.q
    override var bKing: ULong = if (empty) 0UL else StartPosition.k
    var enPassantTarget: ULong? = null
    var castlingRights: UByte = 0xFU
    var turn: Boolean = true

    override fun pieceList(): List<Pair<Char, ULong>> = listOf(
        'P' to wPawns,
        'N' to wKnights,
        'B' to wBishops,
        'R' to wRooks,
        'Q' to wQueens,
        'K' to wKing,
        'p' to bPawns,
        'n' to bKnights,
        'b' to bBishops,
        'r' to bRooks,
        'q' to bQueens,
        'k' to bKing
    )

    fun print() {
        Board(this).log()
    }

    fun castlingRightsToString(): String {
        var output = ""
        for ((idx, char) in castlingRights.toString(2).toCharArray().withIndex()) {
            if (char == '0') continue
            when (idx) {
                0 -> output += "K"
                1 -> output += "Q"
                2 -> output += "k"
                3 -> output += "q"
            }
        }
        if (output == "") return "-"
        return output
    }

    fun king(color: Color): ULong = when (color) {
        Color.WHITE -> wKing
        Color.BLACK -> bKing
    }

    fun queens(color: Color): ULong = when (color) {
        Color.WHITE -> wQueens
        Color.BLACK -> bQueens
    }

    fun rooks(color: Color): ULong = when (color) {
        Color.WHITE -> wRooks
        Color.BLACK -> bRooks
    }

    fun bishops(color: Color): ULong = when (color) {
        Color.WHITE -> wBishops
        Color.BLACK -> bBishops
    }

    fun knights(color: Color): ULong = when (color) {
        Color.WHITE -> wKnights
        Color.BLACK -> bKnights
    }

    fun pawns(color: Color): ULong = when (color) {
        Color.WHITE -> wPawns
        Color.BLACK -> bPawns
    }

    fun occupied(color: Color? = null): ULong {
        return when (color) {
            Color.WHITE -> wPawns or wKnights or wBishops or wRooks or wQueens or wKing
            Color.BLACK -> bPawns or bKnights or bBishops or bRooks or bQueens or bKing
            else -> wPawns or wKnights or wBishops or wRooks or wQueens or wKing or bPawns or bKnights or bBishops or bRooks or bQueens or bKing
        }
    }

    fun empty(): ULong {
        return 0xffffffffffffffffUL xor occupied()
    }

    fun allAttackTargets(color: Color): ULong {
        var output: ULong = 0UL
        for (piece in Piece.attackPieces(color)) {
            output = when (piece) {
                Piece.wPawn, Piece.bPawn -> output or Compass.pawnAttackTargets(pawns(color), color)
                Piece.wKnight, Piece.bKnight -> output or Compass.knightMoveTargets(knights(color))
                Piece.wBishop, Piece.bBishop -> {
                    var bishopAttacks: ULong = 0UL
                    for (direction in Direction.bishops) {
                        bishopAttacks = bishopAttacks or rayAttack(bishops(color), direction, color) or rayMoves(bishops(color), direction, color)
                    }
                    output or bishopAttacks
                }
                Piece.wRook, Piece.bRook -> {
                    var rookAttacks: ULong = 0UL
                    for (direction in Direction.rooks) {
                        rookAttacks = rookAttacks or rayAttack(rooks(color), direction, color) or rayMoves(rooks(color), direction, color)
                    }
                    output or rookAttacks
                }
                Piece.wQueen, Piece.bQueen -> {
                    var queenAttacks: ULong = 0UL
                    for (direction in Direction.sliding) {
                        queenAttacks = queenAttacks or rayAttack(queens(color), direction, color) or rayMoves(queens(color), direction, color)
                    }
                    output or queenAttacks
                }
                else -> output
            }
        }
        return output
    }

    fun rayMoves(x: ULong, direction: Direction, color: Color): ULong {
        var output: ULong = 0UL
        val bits = BitsToListOfBit(x).output
        for (bit in bits) {
            var moves = Compass.ray(bit, direction)
            val blocker = moves and occupied()

            if (blocker != 0UL) {
                val square = Direction.getClosestBit(direction, blocker)
                val ray = Compass.ray(square, direction)
                moves = if ((square and occupied(color)).countOneBits() > 0) {
                    moves xor square.or(ray)
                } else {
                    moves xor ray
                }
            }
            output = output or moves

        }
        return output
    }

    fun rayAttack(x: ULong, direction: Direction, color: Color): ULong {
        val bits = BitsToListOfBit(x).output
        var output: ULong = 0UL

        for(bit in bits) {
            val moves = Compass.ray(bit, direction)
            val blockers = moves and occupied()

            if (blockers != 0UL) {
                val square = Direction.getClosestBit(direction, blockers)
                val enemy = square and occupied(color.inv())
                if (enemy != 0UL) {
                    output = output or enemy
                }
            }
        }
        return output
    }

    fun makeMove(move: Pair<ULong, ULong>, piece: Char, capture: Char? = null) {
        when (piece) {
            'P' -> wPawns = wPawns.xor(move.first).or(move.second)
            'N' -> wKnights = wKnights.xor(move.first).or(move.second)
            'B' -> wBishops = wBishops.xor(move.first).or(move.second)
            'R' -> wRooks = wRooks.xor(move.first).or(move.second)
            'Q' -> wQueens = wQueens.xor(move.first).or(move.second)
            'K' -> wKing = wKing.xor(move.first).or(move.second)
            'p' -> bPawns = bPawns.xor(move.first).or(move.second)
            'n' -> bKnights = bKnights.xor(move.first).or(move.second)
            'b' -> bBishops = bBishops.xor(move.first).or(move.second)
            'r' -> bRooks = bRooks.xor(move.first).or(move.second)
            'q' -> bQueens = bQueens.xor(move.first).or(move.second)
            'k' -> bKing = bKing.xor(move.first).or(move.second)
            else -> throw IllegalArgumentException("Piece must be one of P, N, B, R, Q, K, p, n, b, r, q, k")
        }
        when (capture) {
            'P' -> wPawns = wPawns.xor(move.second)
            'N' -> wKnights = wKnights.xor(move.second)
            'B' -> wBishops = wBishops.xor(move.second)
            'R' -> wRooks = wRooks.xor(move.second)
            'Q' -> wQueens = wQueens.xor(move.second)
            'K' -> wKing = wKing.xor(move.second)
            'p' -> bPawns = bPawns.xor(move.second)
            'n' -> bKnights = bKnights.xor(move.second)
            'b' -> bBishops = bBishops.xor(move.second)
            'r' -> bRooks = bRooks.xor(move.second)
            'q' -> bQueens = bQueens.xor(move.second)
            'k' -> bKing = bKing.xor(move.second)
        }
    }

    fun undoMove(move: Pair<ULong, ULong>, piece: Char, capture: Char?) {
        when (piece) {
            'P' -> wPawns = wPawns.xor(move.second).or(move.first)
            'N' -> wKnights = wKnights.xor(move.second).or(move.first)
            'B' -> wBishops = wBishops.xor(move.second).or(move.first)
            'R' -> wRooks = wRooks.xor(move.second).or(move.first)
            'Q' -> wQueens = wQueens.xor(move.second).or(move.first)
            'K' -> wKing = wKing.xor(move.second).or(move.first)
            'p' -> bPawns = bPawns.xor(move.second).or(move.first)
            'n' -> bKnights = bKnights.xor(move.second).or(move.first)
            'b' -> bBishops = bBishops.xor(move.second).or(move.first)
            'r' -> bRooks = bRooks.xor(move.second).or(move.first)
            'q' -> bQueens = bQueens.xor(move.second).or(move.first)
            'k' -> bKing = bKing.xor(move.second).or(move.first)
            else -> throw IllegalArgumentException("Piece must be one of P, N, B, R, Q, K, p, n, b, r, q, k")
        }
        when (capture) {
            'P' -> wPawns = wPawns.or(move.first)
            'N' -> wKnights = wKnights.or(move.first)
            'B' -> wBishops = wBishops.or(move.first)
            'R' -> wRooks = wRooks.or(move.first)
            'Q' -> wQueens = wQueens.or(move.first)
            'K' -> wKing = wKing.or(move.first)
            'p' -> bPawns = bPawns.or(move.first)
            'n' -> bKnights = bKnights.or(move.first)
            'b' -> bBishops = bBishops.or(move.first)
            'r' -> bRooks = bRooks.or(move.first)
            'q' -> bQueens = bQueens.or(move.first)
            'k' -> bKing = bKing.or(move.first)
        }
    }

    companion object {
        fun promoSquares(piece: Char): ULong = when (piece) {
            Piece.wPawn -> Sets.RANK8
            Piece.bPawn -> Sets.RANK1
            else -> 0UL
        }

        fun rotate90(x: ULong, clockWise: Boolean = true): ULong {
            return if (clockWise) flipVertical(flipDiagA1H8(x)) else flipDiagA1H8(
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

        object StartPosition {
            // White Pieces
            const val P = 0xff00UL
            const val R = 0x81UL
            const val N = 0x42UL
            const val B = 0x24UL
            const val Q = 0x8UL
            const val K = 0x10UL

            // Black Pieces
            const val p = 0xff000000000000UL
            const val r = 0x8100000000000000UL
            const val n = 0x4200000000000000UL
            const val b = 0x2400000000000000UL
            const val q = 0x800000000000000UL
            const val k = 0x1000000000000000UL
        }

    }

}