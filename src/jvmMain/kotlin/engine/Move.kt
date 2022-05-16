package engine

class Move(
    override val fromSquare: SquareMap,
    override val toSquare: SquareMap,
    override val boardRep: BoardRep
) : IMove {
    override val piece: Char = boardRep.getPiece(fromSquare) ?: throw IllegalArgumentException("No piece at $fromSquare")
    override val capture: Char? = boardRep.getPiece(toSquare)

    fun asString(): String {
        return "${fromSquare.name}${toSquare.name}"
    }
}
