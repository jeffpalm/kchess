package engine.v2.moves

interface IMoveFilter {
    fun shouldRun(ctx: MoveGenCtx): Boolean
    suspend fun run(ctx: MoveGenCtx): MoveGenCtx
}