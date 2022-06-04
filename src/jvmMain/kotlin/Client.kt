
import engine.*
import engine.move.PseudoMove

fun main() {
    val game = Game(Fen("r4rk1/1pp1qppp/p1np1n2/2b1p1B1/2B1P1b1/P1NP1N2/1PP1QPPP/R4RK1 w - - 0 10"))
    game.makeMove(PseudoMove(Square.c4, Square.e6, Piece.wBishop))
    Perft.run(1, game)

}
