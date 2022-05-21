package engine.v2.moves

import engine.v2.SquareMap
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

abstract class AbstractMoveGenerator(
    private val context: MoveGenCtx,
    private val rules: List<IMoveRule>,
    private val filters: List<IMoveFilter>
) {
    fun execute(): Set<Pair<SquareMap, SquareMap>> = runBlocking {
        var ctx = generate(context)
        ctx = filter(ctx)
        return@runBlocking ctx.moves()
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