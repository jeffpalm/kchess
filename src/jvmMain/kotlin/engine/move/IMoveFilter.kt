package engine.move

interface IMoveFilter {
    fun shouldRun(ctx: MoveGenCtx): Boolean
    suspend fun run(ctx: MoveGenCtx): MoveGenCtx
}