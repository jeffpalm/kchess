package engine.move

interface IMoveRule {
    fun shouldRun(ctx: MoveGenCtx): Boolean
    suspend fun run(ctx: MoveGenCtx)
}