package engine.v2.moves

import engine.v2.SquareMap
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

abstract class AbstractMoveGenerator(
    private val context: MoveGenCtx,
    private val rules: List<AbstractMoveRule<MoveGenCtx>>
) {
    fun execute(): Set<Pair<SquareMap, SquareMap>> = runBlocking {
        for (rule in rules) {
            if (rule.shouldRun()) {
                launch {
                    rule.run()
                }
            }
        }
        return@runBlocking context.getMoves()
    }
}