package engine.v2.moves

abstract class MovesGenerator<T>(private val rules: List<IRule<T>>, private val context: T) {
    fun execute(): T {
        for (rule in rules) {
            if (rule.shouldRun()) rule.run()
        }
        return context
    }
}