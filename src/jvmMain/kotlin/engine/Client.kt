package engine

import Fen

fun main () {
    val board = Board(Fen("r3k2r/Pppp1ppp/1b3nbN/nP6/BBP1P3/q4N2/Pp1P2PP/R2Q1RK1 w kq - 0 1"))
    board.rep.print()

    val move = Move(SquareMap.d2, SquareMap.d4, board.rep)
    board.makeMove(move)
    println()
    println(move.asString())
    println()

    board.rep.print()

    board.makeMoveByDirection(SquareMap.d1, CompassRose.NORTH, 2)

    println()
    println("Moved Two Steps North")
    println()
    board.rep.print()
}