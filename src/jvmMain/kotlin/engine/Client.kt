package engine

import engine.v2.*
import engine.v2.moves.PseudoMoveGenContext

fun main() {
    binaryMath()
}

fun compassRoseDemo() {
    val board = Board(Fen("r3k2r/Pppp1ppp/1b3nbN/nP6/BBP1P3/q4N2/Pp1P2PP/R2Q1RK1 w kq - 0 1"))
    board.rep.print()

    val move = Move(SquareMap.d2, SquareMap.d4, board.rep)
    board.makeMove(move)
    println()
    println(move.asString())
    println()

    board.rep.print()

    board.makeMoveByDirection(SquareMap.d1, CompassRose.N, 2)

    println()
    println("Moved Two Steps North")
    println()
    board.rep.print()
}

fun startPositionBitboardDemo() {
    println("\nWhite Pawns Starting Position")
    BoardRep(Constants.StartPosition.P).print()
    println("\nBlack Pawns Starting Position")
    BoardRep(Constants.StartPosition.p).print()
    println("\nWhite Rooks Starting Position")
    BoardRep(Constants.StartPosition.R).print()
    println("\nBlack Rooks Starting Position")
    BoardRep(Constants.StartPosition.r).print()
    println("\nWhite Knights Starting Position")
    BoardRep(Constants.StartPosition.N).print()
    println("\nBlack Knights Starting Position")
    BoardRep(Constants.StartPosition.n).print()
    println("\nWhite Bishops Starting Position")
    BoardRep(Constants.StartPosition.B).print()
    println("\nBlack Bishops Starting Position")
    BoardRep(Constants.StartPosition.b).print()
    println("\nWhite Queens Starting Position")
    BoardRep(Constants.StartPosition.Q).print()
    println("\nBlack Queens Starting Position")
    BoardRep(Constants.StartPosition.q).print()
    println("\nWhite Kings Starting Position")
    BoardRep(Constants.StartPosition.K).print()
    println("\nBlack Kings Starting Position")
    BoardRep(Constants.StartPosition.k).print()
}

fun boardRotation() {
    val bb = BitBoard()
    var R_SHAPE = 0x1c241c14242400UL

    BoardRep(R_SHAPE).print()

    BoardRep(bb.flipVertical(R_SHAPE)).print()
    BoardRep(bb.mirrorHorizontal(R_SHAPE)).print()
    BoardRep(bb.flipDiagA1H8(R_SHAPE)).print()
    BoardRep(bb.flipDiagA8H1(R_SHAPE)).print()
    BoardRep(bb.rotate180(R_SHAPE)).print()
    BoardRep(bb.rotate90(R_SHAPE)).print()
    BoardRep(bb.rotate90(R_SHAPE, false)).print()
}

fun binaryMath() {
    val bb = BitBoard()
//    println(0xff000000000000UL.toString(2))
//    println(0xff00000000000000UL.toString(2))
//    println(0xff00000000000000UL.toString(2).toCharArray().size)

    val start = 0x8000000UL

    val offMap = CompassRose.navigate(start, Direction.SE, 4)

    BoardRep(bb.rayMoves(Constants.SquareWords.c1, Direction.NE, PieceColor.WHITE)).print()
    BoardRep(bb.occupied()).print()
}

fun moveGeneration() {
    val bb = BitBoard()
    val gameData = GameData(
        bb,
        PieceColor.WHITE,
        "KQkq",
        "-",
        0,
        1
    )

    val result = engine.v2.moves.MoveGenerator(PseudoMoveGenContext(gameData)).execute()

    result.print()
}