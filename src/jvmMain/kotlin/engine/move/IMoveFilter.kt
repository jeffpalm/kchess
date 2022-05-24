package engine.move

/**
 * Runs after move rules and interacts with filterMoves method of ctx to remove illegal moves from the list
 *
 * These filters run in sequence passing the context returned from the run method
 */
interface IMoveFilter {
    suspend fun run(ctx: MoveGenCtx): MoveGenCtx
}