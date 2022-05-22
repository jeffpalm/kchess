
import engine.Board
import engine.Color
import engine.Direction
import engine.Fen
import engine.adapter.FenToBitBoard

fun main() {
    val board = FenToBitBoard(Fen("rnb3nr/ppp1k1pp/8/1B1pp1q1/P2PPP2/B4p2/P1P4P/RN1QK2R b KQ - 1 9")).output

    Board(board.rayMoves(board.whiteBishops, Direction.NE, Color.WHITE)).print()
    Board(board.allAttackTargets(Color.WHITE)).print()
}
