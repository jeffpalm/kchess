
import engine.Board
import engine.Game
import engine.Piece
import engine.Square
import engine.move.PseudoMove

fun main() {
    val game = Game()
    game.makeMove(PseudoMove(Square.d2, Square.d3, Piece.wPawn))
    game.makeMove(PseudoMove(Square.h7, Square.h5, Piece.bPawn))
    Board(game.data.board.bPawns).log()
    game.makeMove(PseudoMove(Square.c1, Square.h6, Piece.wBishop))
    Board(game.data.board.bPawns).log()
//    Perft.run(1, game)

}
