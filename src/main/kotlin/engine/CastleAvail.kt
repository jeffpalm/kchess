package engine

/**
 * Castling availability encoded as a 4-bit Int. Bits map to FEN characters:
 * - [K] (1): white kingside  (`K`)
 * - [Q] (2): white queenside (`Q`)
 * - [BK] (4): black kingside  (`k`)
 * - [BQ] (8): black queenside (`q`)
 *
 * This replaces the per-make-move String allocation churn from the old
 * `String` representation. Convert to/from FEN with [parse] / [toFenString].
 */
object CastleAvail {
    const val NONE: Int = 0
    const val K: Int = 1
    const val Q: Int = 2
    const val BK: Int = 4
    const val BQ: Int = 8
    const val ALL: Int = 15

    /** Bit-AND mask that drops both white rights when applied. */
    const val DROP_WHITE: Int = ALL xor (K or Q)
    /** Bit-AND mask that drops both black rights when applied. */
    const val DROP_BLACK: Int = ALL xor (BK or BQ)

    fun parse(fenField: String): Int {
        if (fenField == "-") return NONE
        var rights = NONE
        for (c in fenField) {
            rights = rights or when (c) {
                'K' -> K
                'Q' -> Q
                'k' -> BK
                'q' -> BQ
                else -> throw IllegalArgumentException("Invalid castle avail char: $c")
            }
        }
        return rights
    }

    fun toFenString(rights: Int): String {
        if (rights == NONE) return "-"
        val sb = StringBuilder(4)
        if (rights and K != 0) sb.append('K')
        if (rights and Q != 0) sb.append('Q')
        if (rights and BK != 0) sb.append('k')
        if (rights and BQ != 0) sb.append('q')
        return sb.toString()
    }
}
