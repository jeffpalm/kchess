
import engine.*
import engine.move.PseudoMove

fun main() {
    val game = Game(Fen("r3k2r/Pppp1ppp/1b3nbN/nP6/BBP1P3/q4N2/Pp1P2PP/R2Q1RK1 w kq - 0 1"))
    game.makeMove(PseudoMove(Square.b4, Square.c5, Piece.wKing))
    game.makeMove(PseudoMove(Square.a3, Square.a2, Piece.bQueen))
    Perft.run(2, game)

}
