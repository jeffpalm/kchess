package engine.move

/**
 * Generates pseudo-legal moves into [MoveGenCtx]. Filters run after rules
 * to remove illegal ones.
 */
interface IMoveRule {
    fun shouldRun(ctx: MoveGenCtx): Boolean
    fun run(ctx: MoveGenCtx)
}
