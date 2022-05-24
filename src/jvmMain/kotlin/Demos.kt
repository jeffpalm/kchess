
import engine.*
import engine.move.MoveGenCtx
import engine.move.MoveGenerator

object Demos {
    fun boardRepresentation() {
        println("Default position")
        Board().print()

        println("Fen String")
        Board("r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1").print()

        println("Default BitBoard")
        Board(BitBoard()).print()

        println("Any ULong")
        Board(0x1UL).print()
        Board(Sq.h1).print()
    }

    fun startPositionBitboard() {
        println("\nWhite Pawns Starting Position")
        Board(BitBoard.Companion.StartPosition.P).print()
        println("\nBlack Pawns Starting Position")
        Board(BitBoard.Companion.StartPosition.p).print()
        println("\nWhite Rooks Starting Position")
        Board(BitBoard.Companion.StartPosition.R).print()
        println("\nBlack Rooks Starting Position")
        Board(BitBoard.Companion.StartPosition.r).print()
        println("\nWhite Knights Starting Position")
        Board(BitBoard.Companion.StartPosition.N).print()
        println("\nBlack Knights Starting Position")
        Board(BitBoard.Companion.StartPosition.n).print()
        println("\nWhite Bishops Starting Position")
        Board(BitBoard.Companion.StartPosition.B).print()
        println("\nBlack Bishops Starting Position")
        Board(BitBoard.Companion.StartPosition.b).print()
        println("\nWhite Queens Starting Position")
        Board(BitBoard.Companion.StartPosition.Q).print()
        println("\nBlack Queens Starting Position")
        Board(BitBoard.Companion.StartPosition.q).print()
        println("\nWhite Kings Starting Position")
        Board(BitBoard.Companion.StartPosition.K).print()
        println("\nBlack Kings Starting Position")
        Board(BitBoard.Companion.StartPosition.k).print()
    }

    fun boardRotation() {
        val rShape = 0x1c241c14242400UL

        Board(rShape).print()

        Board(BitBoard.flipVertical(rShape)).print()
        Board(BitBoard.mirrorHorizontal(rShape)).print()
        Board(BitBoard.flipDiagA1H8(rShape)).print()
        Board(BitBoard.flipDiagA8H1(rShape)).print()
        Board(BitBoard.rotate180(rShape)).print()
        Board(BitBoard.rotate90(rShape)).print()
        Board(BitBoard.rotate90(rShape, false)).print()
    }

    fun binaryMath() {
        val bb = BitBoard()

        Board(bb.rayMoves(Sq.c1, Direction.NE, Color.WHITE)).print()
        Board(bb.occupied()).print()
    }

    fun moveGeneration() {
        val bb = BitBoard()
        val gameData = GameData(
            bb,
            Color.WHITE,
            "KQkq",
            "-",
            0,
            1
        )

        val result = MoveGenerator(MoveGenCtx(gameData)).execute()

        for (move in result) {
            println("${move.from.name}${move.to.name}")
        }
    }
}