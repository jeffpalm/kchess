package engine.v2.moves

abstract class IRule<T>(protected val context: T) {
    abstract fun shouldRun(): Boolean
    abstract fun run(): T
}