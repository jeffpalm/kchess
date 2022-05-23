package engine

class BitBoard(empty: Boolean = false) : IBitBoardPieces {
    var enPassantTarget: ULong? = null
    override var whitePawns: ULong = if (empty) 0UL else StartPosition.P
    override var whiteKnights: ULong = if (empty) 0UL else StartPosition.N
    override var whiteBishops: ULong = if (empty) 0UL else StartPosition.B
    override var whiteRooks: ULong = if (empty) 0UL else StartPosition.R
    override var whiteQueens: ULong = if (empty) 0UL else StartPosition.Q
    override var whiteKing: ULong = if (empty) 0UL else StartPosition.K
    override var blackPawns: ULong = if (empty) 0UL else StartPosition.p
    override var blackKnights: ULong = if (empty) 0UL else StartPosition.n
    override var blackBishops: ULong = if (empty) 0UL else StartPosition.b
    override var blackRooks: ULong = if (empty) 0UL else StartPosition.r
    override var blackQueens: ULong = if (empty) 0UL else StartPosition.q
    override var blackKing: ULong = if (empty) 0UL else StartPosition.k
    var castlingRights: UByte = 0xFU

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
        Color.WHITE -> whiteKing
        Color.BLACK -> blackKing
    }

    fun queens(color: Color): ULong = when (color) {
        Color.WHITE -> whiteQueens
        Color.BLACK -> blackQueens
    }

    fun rooks(color: Color): ULong = when (color) {
        Color.WHITE -> whiteRooks
        Color.BLACK -> blackRooks
    }

    fun bishops(color: Color): ULong = when (color) {
        Color.WHITE -> whiteBishops
        Color.BLACK -> blackBishops
    }

    fun knights(color: Color): ULong = when (color) {
        Color.WHITE -> whiteKnights
        Color.BLACK -> blackKnights
    }

    fun pawns(color: Color): ULong = when (color) {
        Color.WHITE -> whitePawns
        Color.BLACK -> blackPawns
    }

    fun occupied(color: Color? = null): ULong {
        return when (color) {
            Color.WHITE -> whitePawns or whiteKnights or whiteBishops or whiteRooks or whiteQueens or whiteKing
            Color.BLACK -> blackPawns or blackKnights or blackBishops or blackRooks or blackQueens or blackKing
            else -> whitePawns or whiteKnights or whiteBishops or whiteRooks or whiteQueens or whiteKing or blackPawns or blackKnights or blackBishops or blackRooks or blackQueens or blackKing
        }
    }

    fun empty(): ULong {
        return 0xffffffffffffffffUL xor occupied()
    }

    fun allAttackTargets(color: Color): ULong {
        var output: ULong = 0UL
        for (piece in Piece.attackPieces(color)) {
            output = when (piece) {
                Piece.whitePawn, Piece.blackPawn -> output or Compass.pawnAttackTargets(pawns(color), color)
                Piece.whiteKnight, Piece.blackKnight -> output or Compass.knightMoveTargets(knights(color))
                Piece.whiteBishop, Piece.blackBishop -> {
                    var bishopAttacks: ULong = 0UL
                    for (direction in Direction.bishops) {
                        bishopAttacks = bishopAttacks or rayAttack(bishops(color), direction, color) or rayMoves(bishops(color), direction, color)
                    }
                    output or bishopAttacks
                }
                Piece.whiteRook, Piece.blackRook -> {
                    var rookAttacks: ULong = 0UL
                    for (direction in Direction.rooks) {
                        rookAttacks = rookAttacks or rayAttack(rooks(color), direction, color) or rayMoves(rooks(color), direction, color)
                    }
                    output or rookAttacks
                }
                Piece.whiteQueen, Piece.blackQueen -> {
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
        var moves = Compass.ray(x, direction)
        val blocker = moves and occupied()

        if (blocker != 0UL) {
            var square = Direction.getClosestBit(direction, blocker)
            val ray = Compass.ray(square, direction)
            moves = if ((blocker and occupied(color)).countOneBits() > 0) {
                moves xor square.or(ray)
            } else {
                moves xor ray
            }
        }
        return moves
    }

    fun rayAttack(x: ULong, direction: Direction, color: Color): ULong {
        val moves = Compass.ray(x, direction)
        val blockers = moves and occupied()

        if (blockers != 0UL) {
            val square = Direction.getClosestBit(direction, blockers)
            val enemy = square and occupied(color.inv())
            if (enemy != 0UL) {
                return enemy
            }
        }
        return 0UL
    }

    fun makeMove(move: Pair<ULong, ULong>, piece: Char, capture: Char? = null) {
        when (piece) {
            'P' -> whitePawns = whitePawns.xor(move.first).or(move.second)
            'N' -> whiteKnights = whiteKnights.xor(move.first).or(move.second)
            'B' -> whiteBishops = whiteBishops.xor(move.first).or(move.second)
            'R' -> whiteRooks = whiteRooks.xor(move.first).or(move.second)
            'Q' -> whiteQueens = whiteQueens.xor(move.first).or(move.second)
            'K' -> whiteKing = whiteKing.xor(move.first).or(move.second)
            'p' -> blackPawns = blackPawns.xor(move.first).or(move.second)
            'n' -> blackKnights = blackKnights.xor(move.first).or(move.second)
            'b' -> blackBishops = blackBishops.xor(move.first).or(move.second)
            'r' -> blackRooks = blackRooks.xor(move.first).or(move.second)
            'q' -> blackQueens = blackQueens.xor(move.first).or(move.second)
            'k' -> blackKing = blackKing.xor(move.first).or(move.second)
            else -> throw IllegalArgumentException("Piece must be one of P, N, B, R, Q, K, p, n, b, r, q, k")
        }
        when (capture) {
            'P' -> whitePawns = whitePawns.xor(move.second)
            'N' -> whiteKnights = whiteKnights.xor(move.second)
            'B' -> whiteBishops = whiteBishops.xor(move.second)
            'R' -> whiteRooks = whiteRooks.xor(move.second)
            'Q' -> whiteQueens = whiteQueens.xor(move.second)
            'K' -> whiteKing = whiteKing.xor(move.second)
            'p' -> blackPawns = blackPawns.xor(move.second)
            'n' -> blackKnights = blackKnights.xor(move.second)
            'b' -> blackBishops = blackBishops.xor(move.second)
            'r' -> blackRooks = blackRooks.xor(move.second)
            'q' -> blackQueens = blackQueens.xor(move.second)
            'k' -> blackKing = blackKing.xor(move.second)
        }
    }

    fun undoMove(move: Pair<ULong, ULong>, piece: Char, capture: Char?) {
        when (piece) {
            'P' -> whitePawns = whitePawns.xor(move.second).or(move.first)
            'N' -> whiteKnights = whiteKnights.xor(move.second).or(move.first)
            'B' -> whiteBishops = whiteBishops.xor(move.second).or(move.first)
            'R' -> whiteRooks = whiteRooks.xor(move.second).or(move.first)
            'Q' -> whiteQueens = whiteQueens.xor(move.second).or(move.first)
            'K' -> whiteKing = whiteKing.xor(move.second).or(move.first)
            'p' -> blackPawns = blackPawns.xor(move.second).or(move.first)
            'n' -> blackKnights = blackKnights.xor(move.second).or(move.first)
            'b' -> blackBishops = blackBishops.xor(move.second).or(move.first)
            'r' -> blackRooks = blackRooks.xor(move.second).or(move.first)
            'q' -> blackQueens = blackQueens.xor(move.second).or(move.first)
            'k' -> blackKing = blackKing.xor(move.second).or(move.first)
            else -> throw IllegalArgumentException("Piece must be one of P, N, B, R, Q, K, p, n, b, r, q, k")
        }
        when (capture) {
            'P' -> whitePawns = whitePawns.or(move.first)
            'N' -> whiteKnights = whiteKnights.or(move.first)
            'B' -> whiteBishops = whiteBishops.or(move.first)
            'R' -> whiteRooks = whiteRooks.or(move.first)
            'Q' -> whiteQueens = whiteQueens.or(move.first)
            'K' -> whiteKing = whiteKing.or(move.first)
            'p' -> blackPawns = blackPawns.or(move.first)
            'n' -> blackKnights = blackKnights.or(move.first)
            'b' -> blackBishops = blackBishops.or(move.first)
            'r' -> blackRooks = blackRooks.or(move.first)
            'q' -> blackQueens = blackQueens.or(move.first)
            'k' -> blackKing = blackKing.or(move.first)
        }
    }

    companion object {
        fun promoSquares(piece: Char): ULong = when (piece) {
            Piece.whitePawn -> Sets.RANK8
            Piece.blackPawn -> Sets.RANK1
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