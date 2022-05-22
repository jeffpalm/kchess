package engine.move

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

abstract class AbstractMoveGenerator(
    private val context: MoveGenCtx,
    private val rules: List<IMoveRule>,
    private val filters: List<IMoveFilter>
) {
    fun execute(): Set<PseudoMove> = runBlocking {
        return@runBlocking filter(generate(context)).moves()
    }

    private suspend fun generate(context: MoveGenCtx) = runBlocking {
        for (rule in rules) {
            if (rule.shouldRun(context)) {
                launch {
                    rule.run(context)
                }
            }
        }
        return@runBlocking context
    }

    private suspend fun filter(context: MoveGenCtx) = runBlocking {
        for (filter in filters) {
            if (filter.shouldRun(context)) {
                launch {
                    filter.run(context)
                }
            }
        }
        return@runBlocking context
    }
}