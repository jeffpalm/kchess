package engine.move

interface IMoveFilter {
    suspend fun run(ctx: MoveGenCtx): MoveGenCtx
}