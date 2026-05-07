package engine

import engine.adapter.BitsToListOfBit
import engine.move.Magic

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
        val enemyKing = king(color.inv())
        for (piece in Piece.attackPieces(color)) {
            output = when (piece) {
                Piece.wPawn, Piece.bPawn -> output or Compass.pawnAttackTargets(pawns(color), color)
                Piece.wKnight, Piece.bKnight -> output or Compass.knightMoveTargets(knights(color))
                Piece.wBishop, Piece.bBishop -> output or sliderAttacks(bishops(color), Direction.bishops, enemyKing)
                Piece.wRook, Piece.bRook -> output or sliderAttacks(rooks(color), Direction.rooks, enemyKing)
                Piece.wQueen, Piece.bQueen -> output or sliderAttacks(queens(color), Direction.sliding, enemyKing)
                else -> output
            }
        }
        output = output or Compass.kingMoveTargets(king(color))
        return output
    }

    private fun sliderAttacks(pieces: ULong, directions: List<Direction>, enemyKing: ULong): ULong {
        if (pieces == 0UL) return 0UL
        val occupiedMinusEnemyKing = occupied() xor (occupied() and enemyKing)
        var output: ULong = 0UL
        val bits = engine.adapter.BitsToListOfBit(pieces).output
        for (bit in bits) {
            for (direction in directions) {
                val ray = Compass.ray(bit, direction)
                val blockers = ray and occupiedMinusEnemyKing
                output = output or if (blockers == 0UL) {
                    ray
                } else {
                    val first = Direction.getClosestBit(direction, blockers)
                    val beyond = Compass.ray(first, direction)
                    (ray xor beyond)
                }
            }
        }
        return output
    }

    /**
     * Bitboard of pieces of [byColor] that attack [squareBit]. Returns 0 when
     * [squareBit] is empty/0. Counts each attacker as one bit, so
     * `attackersOf(...).countOneBits()` gives the number of attackers.
     */
    fun attackersOf(squareBit: ULong, byColor: Color): ULong {
        if (squareBit == 0UL) return 0UL
        val sq = Square[squareBit]
        var attackers = 0UL

        attackers = attackers or (Magic.Attack.Knight[sq] and knights(byColor))
        // a byColor pawn attacks squareBit iff a (byColor.inv()) pawn placed on
        // squareBit would attack the byColor pawn's square.
        attackers = attackers or (Compass.pawnAttackTargets(squareBit, byColor.inv()) and pawns(byColor))
        attackers = attackers or (Compass.kingMoveTargets(squareBit) and king(byColor))

        for (direction in Direction.bishops) {
            val ray = Compass.ray(squareBit, direction)
            val blockers = ray and occupied()
            if (blockers != 0UL) {
                val first = Direction.getClosestBit(direction, blockers)
                if (first and (bishops(byColor) or queens(byColor)) != 0UL) {
                    attackers = attackers or first
                }
            }
        }
        for (direction in Direction.rooks) {
            val ray = Compass.ray(squareBit, direction)
            val blockers = ray and occupied()
            if (blockers != 0UL) {
                val first = Direction.getClosestBit(direction, blockers)
                if (first and (rooks(byColor) or queens(byColor)) != 0UL) {
                    attackers = attackers or first
                }
            }
        }
        return attackers
    }

    fun rayMoves(x: ULong, direction: Direction, color: Color): ULong {
        if (x == 0UL) return 0UL
        var output: ULong = 0UL
        val occ = occupied()
        val own = occupied(color)
        var w = x
        while (w != 0UL) {
            val bit = w.takeLowestOneBit()
            w = w xor bit
            var moves = Compass.ray(bit, direction)
            val blocker = moves and occ
            if (blocker != 0UL) {
                val square = Direction.getClosestBit(direction, blocker)
                val ray = Compass.ray(square, direction)
                moves = if (square and own != 0UL) {
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
        if (x == 0UL) return 0UL
        val occ = occupied()
        val enemy = occupied(color.inv())
        var output: ULong = 0UL
        var w = x
        while (w != 0UL) {
            val bit = w.takeLowestOneBit()
            w = w xor bit
            val moves = Compass.ray(bit, direction)
            val blockers = moves and occ

            if (blockers != 0UL) {
                val square = Direction.getClosestBit(direction, blockers)
                val enemyHit = square and enemy
                if (enemyHit != 0UL) {
                    output = output or enemyHit
                }
            }
        }
        return output
    }

    fun makeMove(move: Pair<ULong, ULong>, piece: Char, capture: Char? = null, promo: Char? = null) {
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
        when (promo) {
            'N' -> { wPawns = wPawns.xor(move.second); wKnights = wKnights.or(move.second) }
            'B' -> { wPawns = wPawns.xor(move.second); wBishops = wBishops.or(move.second) }
            'R' -> { wPawns = wPawns.xor(move.second); wRooks = wRooks.or(move.second) }
            'Q' -> { wPawns = wPawns.xor(move.second); wQueens = wQueens.or(move.second) }
            'n' -> { bPawns = bPawns.xor(move.second); bKnights = bKnights.or(move.second) }
            'b' -> { bPawns = bPawns.xor(move.second); bBishops = bBishops.or(move.second) }
            'r' -> { bPawns = bPawns.xor(move.second); bRooks = bRooks.or(move.second) }
            'q' -> { bPawns = bPawns.xor(move.second); bQueens = bQueens.or(move.second) }
            null -> {}
            else -> throw IllegalArgumentException("Invalid promo piece: $promo")
        }
        when (capture) {
            'P' -> wPawns = wPawns.xor(if (move.second == enPassantTarget && piece == Piece.bPawn) Magic.EnPassantCaptureSq[move.second] else move.second)
            'N' -> wKnights = wKnights.xor(move.second)
            'B' -> wBishops = wBishops.xor(move.second)
            'R' -> wRooks = wRooks.xor(move.second)
            'Q' -> wQueens = wQueens.xor(move.second)
            'K' -> wKing = wKing.xor(move.second)
            'p' -> bPawns = bPawns.xor(if (move.second == enPassantTarget && piece == Piece.wPawn) Magic.EnPassantCaptureSq[move.second] else move.second)
            'n' -> bKnights = bKnights.xor(move.second)
            'b' -> bBishops = bBishops.xor(move.second)
            'r' -> bRooks = bRooks.xor(move.second)
            'q' -> bQueens = bQueens.xor(move.second)
            'k' -> bKing = bKing.xor(move.second)
        }
    }

    /**
     * Reverse a previously-played move. [from]/[to] are the square the piece
     * came from and the square it ended up on. [capturedSquare] is where the
     * captured piece (if any) was — usually equal to [to], but not for en
     * passant where the captured pawn was on a different rank.
     */
    fun undoMove(from: ULong, to: ULong, piece: Char, capture: Char? = null, capturedSquare: ULong = to, promo: Char? = null) {
        when (promo) {
            'N' -> { wKnights = wKnights.xor(to); wPawns = wPawns.or(to) }
            'B' -> { wBishops = wBishops.xor(to); wPawns = wPawns.or(to) }
            'R' -> { wRooks = wRooks.xor(to); wPawns = wPawns.or(to) }
            'Q' -> { wQueens = wQueens.xor(to); wPawns = wPawns.or(to) }
            'n' -> { bKnights = bKnights.xor(to); bPawns = bPawns.or(to) }
            'b' -> { bBishops = bBishops.xor(to); bPawns = bPawns.or(to) }
            'r' -> { bRooks = bRooks.xor(to); bPawns = bPawns.or(to) }
            'q' -> { bQueens = bQueens.xor(to); bPawns = bPawns.or(to) }
            null -> {}
            else -> throw IllegalArgumentException("Invalid promo piece: $promo")
        }
        when (piece) {
            'P' -> wPawns = wPawns.xor(to).or(from)
            'N' -> wKnights = wKnights.xor(to).or(from)
            'B' -> wBishops = wBishops.xor(to).or(from)
            'R' -> wRooks = wRooks.xor(to).or(from)
            'Q' -> wQueens = wQueens.xor(to).or(from)
            'K' -> wKing = wKing.xor(to).or(from)
            'p' -> bPawns = bPawns.xor(to).or(from)
            'n' -> bKnights = bKnights.xor(to).or(from)
            'b' -> bBishops = bBishops.xor(to).or(from)
            'r' -> bRooks = bRooks.xor(to).or(from)
            'q' -> bQueens = bQueens.xor(to).or(from)
            'k' -> bKing = bKing.xor(to).or(from)
            else -> throw IllegalArgumentException("Piece must be one of P, N, B, R, Q, K, p, n, b, r, q, k")
        }
        when (capture) {
            'P' -> wPawns = wPawns.or(capturedSquare)
            'N' -> wKnights = wKnights.or(capturedSquare)
            'B' -> wBishops = wBishops.or(capturedSquare)
            'R' -> wRooks = wRooks.or(capturedSquare)
            'Q' -> wQueens = wQueens.or(capturedSquare)
            'K' -> wKing = wKing.or(capturedSquare)
            'p' -> bPawns = bPawns.or(capturedSquare)
            'n' -> bKnights = bKnights.or(capturedSquare)
            'b' -> bBishops = bBishops.or(capturedSquare)
            'r' -> bRooks = bRooks.or(capturedSquare)
            'q' -> bQueens = bQueens.or(capturedSquare)
            'k' -> bKing = bKing.or(capturedSquare)
        }
    }

    fun clone(): BitBoard {
        val clone = BitBoard(true)
        clone.wPawns = wPawns
        clone.wKnights = wKnights
        clone.wBishops = wBishops
        clone.wRooks = wRooks
        clone.wQueens = wQueens
        clone.wKing = wKing
        clone.bPawns = bPawns
        clone.bKnights = bKnights
        clone.bBishops = bBishops
        clone.bRooks = bRooks
        clone.bQueens = bQueens
        clone.bKing = bKing
        clone.enPassantTarget = enPassantTarget
        clone.castlingRights = castlingRights
        clone.turn = turn
        return clone
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