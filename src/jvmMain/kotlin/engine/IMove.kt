package engine

interface IMove {
    val fromSquare: SquareMap
    val toSquare: SquareMap
    val piece: Char
    val capture: Char?
}