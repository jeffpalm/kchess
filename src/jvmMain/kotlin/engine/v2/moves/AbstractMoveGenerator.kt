package engine.v2.moves

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

abstract class AbstractMoveGenerator<T>(
    private val context: T,
    private val rules: List<AbstractMoveRule<T>>
) {
    fun execute(): T = runBlocking {
        for (rule in rules) {
            if (rule.shouldRun()) {
                launch {
                    rule.run()
                }
            }
        }
        return@runBlocking context
    }
}