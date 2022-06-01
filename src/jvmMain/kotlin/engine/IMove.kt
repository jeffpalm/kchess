package engine

interface IMove {
    val from: Square
    val to: Square
    val piece: Char
    val capture: Char?
}