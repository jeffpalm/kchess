package engine.v2.moves

abstract class AbstractRule<T>(protected val context: T) {
    abstract fun shouldRun(): Boolean
    abstract fun run(): T
}