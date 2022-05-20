package engine.v2

data class GameData(
    override var board: BitBoard,
    override var turn: PieceColor,
    override var castlingAvailability: String = "KQkq",
    override var enPassantTarget: String = "-",
    override var halfMoveClock: Int = 0,
    override var fullMoveClock: Int = 1
) : IGameData {
    override fun copy(): IGameData {
        return GameData(board, turn, castlingAvailability, enPassantTarget, halfMoveClock, fullMoveClock)
    }
}
