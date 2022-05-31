import engine.*
import engine.move.PseudoMove

fun main() {
    val game = Game(Fen("8/2p5/3p4/KP5r/1R3p1k/8/4P1P1/8 w - - 0 1"))
    game.makeMove(PseudoMove(Square.a2, Square.a4, Piece.wPawn))
    Perft.run(1, game)
}
