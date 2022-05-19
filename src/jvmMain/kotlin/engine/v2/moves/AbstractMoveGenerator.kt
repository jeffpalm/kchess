package engine.v2.moves

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

abstract class AbstractMoveGenerator(
    private val context: MoveGenCtx,
    private val rules: List<AbstractMoveRule<MoveGenCtx>>
) {
    fun execute(): List<PseudoMove> = runBlocking {
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