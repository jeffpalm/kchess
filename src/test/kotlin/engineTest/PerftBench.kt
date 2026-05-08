package engineTest

import engine.Game
import engine.Perft
import org.junit.jupiter.api.Disabled
import kotlin.test.Test

internal class PerftBench {
    @Test @Disabled("Manual benchmark; flip on locally to measure")
    fun `bench depth 5 stats x5`() {
        val sample = LongArray(5)
        repeat(5) { i ->
            val t0 = System.nanoTime()
            val s = Perft.runStats(5, Game())
            val ns = System.nanoTime() - t0
            sample[i] = ns
            println("run ${i + 1}: nodes=${s.nodes} time=${ns / 1_000_000}ms")
        }
        val warm = sample.drop(1)
        val avg = warm.average() / 1_000_000
        println("warm-avg over ${warm.size} runs: %.0fms".format(avg))
    }
}
