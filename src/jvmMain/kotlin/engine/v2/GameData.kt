package engine.v2

import engine.Color

data class GameData(
    override var board: BitBoard,
    override var turn: Color,
    override var castleAvail: String = "KQkq",
    override var enPassantTarget: String = "-",
    override var halfMoveClock: Int = 0,
    override var fullMoveClock: Int = 1
) : IGameData {
    override fun copy(): IGameData {
        return GameData(board, turn, castleAvail, enPassantTarget, halfMoveClock, fullMoveClock)
    }
}
