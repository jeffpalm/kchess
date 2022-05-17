package engine.v2.moves

abstract class AbstractMoveRule<T>(protected val context: T) {
    abstract fun shouldRun(): Boolean
    abstract suspend fun run()
}