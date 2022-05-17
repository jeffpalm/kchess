package engine.v2.moves.pawn

import engine.v2.IGameData

class PawnContext(val gameData: IGameData) {
    val moveWords: MutableList<ULong> = mutableListOf()


}