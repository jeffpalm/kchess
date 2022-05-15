class CastleNotation(side: CastleSide) {
    val notation = when (side) {
        CastleSide.KING -> "O-O"
        CastleSide.QUEEN -> "O-O-O"
    }
}