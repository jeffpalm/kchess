package engine.move

abstract class AbstractMoveGenerator(
    private val context: MoveGenCtx,
    private val rules: List<IMoveRule>,
    private val filters: List<IMoveFilter>,
) {
    fun execute(): List<PseudoMove> {
        for (rule in rules) {
            if (rule.shouldRun(context)) rule.run(context)
        }
        for (filter in filters) {
            filter.run(context)
        }
        return context.moves()
    }
}
