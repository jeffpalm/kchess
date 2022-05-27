
import engine.*
import engine.move.MoveGenCtx
import engine.move.MoveGenerator

object Demos {
    fun boardRepresentation() {
        println("Default position")
        Board().log()

        println("Fen String")
        Board("r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1").log()

        println("Default BitBoard")
        Board(BitBoard()).log()

        println("Any ULong")
        Board(0x1UL).log()
        Board(Sq.h1).log()
    }

    fun startPositionBitboard() {
        println("\nWhite Pawns Starting Position")
        Board(BitBoard.Companion.StartPosition.P).log()
        println("\nBlack Pawns Starting Position")
        Board(BitBoard.Companion.StartPosition.p).log()
        println("\nWhite Rooks Starting Position")
        Board(BitBoard.Companion.StartPosition.R).log()
        println("\nBlack Rooks Starting Position")
        Board(BitBoard.Companion.StartPosition.r).log()
        println("\nWhite Knights Starting Position")
        Board(BitBoard.Companion.StartPosition.N).log()
        println("\nBlack Knights Starting Position")
        Board(BitBoard.Companion.StartPosition.n).log()
        println("\nWhite Bishops Starting Position")
        Board(BitBoard.Companion.StartPosition.B).log()
        println("\nBlack Bishops Starting Position")
        Board(BitBoard.Companion.StartPosition.b).log()
        println("\nWhite Queens Starting Position")
        Board(BitBoard.Companion.StartPosition.Q).log()
        println("\nBlack Queens Starting Position")
        Board(BitBoard.Companion.StartPosition.q).log()
        println("\nWhite Kings Starting Position")
        Board(BitBoard.Companion.StartPosition.K).log()
        println("\nBlack Kings Starting Position")
        Board(BitBoard.Companion.StartPosition.k).log()
    }

    fun boardRotation() {
        val rShape = 0x1c241c14242400UL

        Board(rShape).log()

        Board(BitBoard.flipVertical(rShape)).log()
        Board(BitBoard.mirrorHorizontal(rShape)).log()
        Board(BitBoard.flipDiagA1H8(rShape)).log()
        Board(BitBoard.flipDiagA8H1(rShape)).log()
        Board(BitBoard.rotate180(rShape)).log()
        Board(BitBoard.rotate90(rShape)).log()
        Board(BitBoard.rotate90(rShape, false)).log()
    }

    fun binaryMath() {
        val bb = BitBoard()

        Board(bb.rayMoves(Sq.c1, Direction.NE, Color.WHITE)).log()
        Board(bb.occupied()).log()
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