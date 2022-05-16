package engine

interface IMove {
    val fromSquare: SquareMap
    val toSquare: SquareMap
    val boardRep: BoardRep
    val piece: Char
    val capture: Char?
}