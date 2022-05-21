package engine.v2.moves

interface IMoveRule {
    fun shouldRun(ctx: MoveGenCtx): Boolean
    suspend fun run(ctx: MoveGenCtx)
}