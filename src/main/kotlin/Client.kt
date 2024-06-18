package dev.jeffpalm

import engine.*
import engine.adapter.GameToFen
import engine.move.PseudoMove

fun main() {
    val game = Game(Fen("r3k2r/Pppp1ppp/1b3nbN/nP6/BBP1P3/q4N2/Pp1P2PP/R2Q1RK1 w kq - 0 1"))
    game.makeMove(PseudoMove(Square.f1, Square.f2, Piece.wRook))
    game.board.log()
    var fen = GameToFen(game)
    println(fen.output.string)
    Perft.run(2, game)
    val game2 = Game(Fen("r3k2r/Pppp1ppp/1b3nbN/nP6/BBP1P3/q4N2/P2P1RPP/Rq1Q2K1 w kq - 0 2"))
    Perft.run(1, game2)
//    Perft.run(3, game)
//    game.makeMove(PseudoMove(Square.a3, Square.a2, Piece.bQueen))
//    Perft.run(2, game)
    
}
