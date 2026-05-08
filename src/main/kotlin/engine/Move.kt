package engine

import engine.move.Magic

class Move(
    override val from: Square,
    override val to: Square,
    override val piece: Char,
    board: Board,
    val prevEnPassantTarget: Square?,
    val prevCastleAvail: Int = CastleAvail.NONE,
    val promo: Char? = null
) : IMove {
    override val capture: Char? = when {
        to == prevEnPassantTarget && (piece == Piece.wPawn || piece == Piece.bPawn) ->
            board.getPiece(Square.fromBit(Magic.EnPassantCaptureSq[to]))
        else -> board.getPiece(to)
    }

    fun asString(): String {
        return "${from.name}${to.name}"
    }

    fun isWhiteKingCastle(): Boolean =
        piece == 'K' && from == Square.e1 && (to == Square.g1 || to == Square.c1)

    fun isBlackKingCastle(): Boolean =
        piece == 'k' && from == Square.e8 && (to == Square.g8 || to == Square.c8)

    fun enPassantTarget(): Square? = when {
        isWhiteTwoMoveJump(this) -> Square[from.ordinal + 8]
        isBlackTwoMoveJump(this) -> Square[from.ordinal - 8]
        else -> null
    }

    companion object {
        // White pawns start on rank 2 (ordinals 8..15); black on rank 7 (48..55).
        // A two-square jump lands on rank 4 (24..31) for white, rank 5 (32..39) for black.
        fun isWhiteTwoMoveJump(move: Move): Boolean =
            move.piece == Piece.wPawn && move.from.ordinal in 8..15 && move.to.ordinal == move.from.ordinal + 16

        fun isBlackTwoMoveJump(move: Move): Boolean =
            move.piece == Piece.bPawn && move.from.ordinal in 48..55 && move.to.ordinal == move.from.ordinal - 16
    }
}
