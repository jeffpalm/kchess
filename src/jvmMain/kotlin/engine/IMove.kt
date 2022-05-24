package engine

interface IMove {
    val fromSquare: Square
    val toSquare: Square
    val piece: Char
    val capture: Char?
}