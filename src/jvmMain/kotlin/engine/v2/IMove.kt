package engine.v2

import engine.Fen

interface IMove {
    val fromSquare: SquareMap
    val toSquare: SquareMap
    val boardRep: BoardRep<Fen>
    val piece: Char
    val capture: Char?
}