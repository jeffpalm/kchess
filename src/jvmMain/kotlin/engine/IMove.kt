package engine

import engine.SquareMap

interface IMove {
    val fromSquare: SquareMap
    val toSquare: SquareMap
    val piece: Char
    val capture: Char?
}