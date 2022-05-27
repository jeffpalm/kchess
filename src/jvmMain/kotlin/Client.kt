import engine.*

fun main() {

//    Board(Compass.ray(Sq.b4 or Sq.e4, Direction.NW)).print()

    val game = Game(Fen("r3k2r/Pppp1ppp/1b3nbN/nPP5/BB2P3/q4N2/Pp1P2PP/R2Q1RK1 b kq - 0 1"))

    val (board) = game.data

    Board(board.rayMoves(board.bishops(Color.WHITE), Direction.NE, Color.WHITE)).log()
    Board(board.allAttackTargets(Color.WHITE)).log()
    println("--------------------------")
//    Board(game.data.board.wBishops).print()

//    val squares = Square.values()
//
//    for (direction in Direction.bishops) {
//        Board(game.data.board.rayMoves(Sq.b4, direction, Color.WHITE))
//    }
//
//    for (direction in Direction.sliding) {
//        println(direction.name)
//        for (square in squares) {
//            val moves = Compass.ray(Sq[square.ordinal], direction)
////            println("${square.name} - ${direction.name}")
////            Board(moves).print()
////            println("-------------------------")
//
//        println("const val ${square.name} = 0x${moves.toString(16)}UL")
//        }
//    }

}
