package engine.move

/**
 * Responsible for generating possible all moves based on IGameData
 * and adding them to the MoveGenCtx via the addMoves or addMove method
 *
 * These rules run in parallel
 */
interface IMoveRule {
    fun shouldRun(ctx: MoveGenCtx): Boolean
    suspend fun run(ctx: MoveGenCtx)
}