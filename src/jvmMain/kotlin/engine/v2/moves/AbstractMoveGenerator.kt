package engine.v2.moves

abstract class AbstractMoveGenerator<T>(private val rules: List<AbstractRule<T>>, private val context: T) {
    fun execute(): T {
        for (rule in rules) {
            if (rule.shouldRun()) rule.run()
        }
        return context
    }
}