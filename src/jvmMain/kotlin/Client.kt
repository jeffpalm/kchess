
import engine.Game
import engine.Perft
import engine.Piece
import engine.Square
import engine.move.PseudoMove

fun main() {
    val game = Game()
    game.makeMove(PseudoMove(Square.d2, Square.d3, Piece.wPawn))
    game.makeMove(PseudoMove(Square.h7, Square.h5, Piece.bPawn))
    game.makeMove(PseudoMove(Square.d1, Square.d2, Piece.wQueen))
    Perft.run(1, game)

}
