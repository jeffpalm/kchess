package engine

data class GameData(
    override var board: BitBoard,
    override var turn: Color,
    override var castleAvail: Int = CastleAvail.ALL,
    override var enPassantTarget: Square? = null,
    override var halfMoveClock: Int = 0,
    override var fullMoveClock: Int = 1
) : IGameData {
    override fun clone(): IGameData {
        return GameData(board.clone(), turn, castleAvail, enPassantTarget, halfMoveClock, fullMoveClock)
    }
}
