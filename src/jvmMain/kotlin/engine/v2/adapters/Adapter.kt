package engine.v2.adapters

sealed class Adapter<I, O>(input: I, context: Any? = null) {
    val output: O = adapt(input, context)

    protected abstract fun adapt(input: I, context: Any? = null): O
}