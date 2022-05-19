package engine.v2

interface IMove {
    val fromSquare: SquareMap
    val toSquare: SquareMap
    val piece: Char
    val capture: Char?
}