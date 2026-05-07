package engine

/**
 * Aggregated counts over the leaves of a perft search.
 *
 * Each non-`nodes` field counts how many of the leaf moves (i.e. moves played
 * at the deepest ply of the search) match that classification, per the
 * convention used by https://www.chessprogramming.org/Perft_Results.
 *
 * - [captures] includes en passant captures.
 * - [discoveryChecks] is single-checker checks where the checker isn't the
 *   piece that just moved.
 * - [doubleChecks] is checks with two simultaneous checkers; these are not
 *   also counted as discoveries.
 */
data class PerftStats(
    val nodes: Long = 0,
    val captures: Long = 0,
    val enPassant: Long = 0,
    val castles: Long = 0,
    val promotions: Long = 0,
    val checks: Long = 0,
    val discoveryChecks: Long = 0,
    val doubleChecks: Long = 0,
    val checkmates: Long = 0,
) {
    operator fun plus(other: PerftStats) = PerftStats(
        nodes + other.nodes,
        captures + other.captures,
        enPassant + other.enPassant,
        castles + other.castles,
        promotions + other.promotions,
        checks + other.checks,
        discoveryChecks + other.discoveryChecks,
        doubleChecks + other.doubleChecks,
        checkmates + other.checkmates,
    )

    companion object {
        val ZERO = PerftStats()
    }
}
