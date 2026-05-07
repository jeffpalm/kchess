package engine.move

/**
 * Strips illegal moves from a [MoveGenCtx] populated by rules. Filters are
 * called sequentially in declaration order; each one's output becomes the
 * next one's input.
 */
interface IMoveFilter {
    fun run(ctx: MoveGenCtx): MoveGenCtx
}
