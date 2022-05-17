package engine.v2.adapters

abstract class Adapter<I, O>(input: I) {
    val output: O = adapt(input)

    protected abstract fun adapt(input: I): O
}