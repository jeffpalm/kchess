package engine.move.filters

import engine.*
import engine.move.IMoveFilter
import engine.move.Magic
import engine.move.MoveGenCtx

class MoveFilterKingInActiveCheck : IMoveFilter {

    override suspend fun run(ctx: MoveGenCtx): MoveGenCtx {
        val (board, turn) = ctx.data

        val (activeSlidingThreats, passiveSlidingThreats) = getSlidingThreats(board, turn)
        val activeKnightThreats = getKnightThreats(board, turn)
        val activePawnThreats = getPawnThreats(board, turn)

        val activeThreats = activeSlidingThreats or activeKnightThreats

        if (activeThreats != 0UL || activePawnThreats != 0UL) {
            ctx.filterMoves {
                when (it.piece) {
                    Piece.wKing, Piece.bKing -> it.toBit.and(activeThreats) == 0UL
                    else -> it.toBit.and(activeThreats) != 0UL || it.toBit.and(activePawnThreats) != 0UL
                }
            }
        }

        if (passiveSlidingThreats != 0UL) {
            ctx.filterMoves { it.toBit.and(passiveSlidingThreats) == 0UL }
        }

        return ctx
    }

    private fun getSlidingThreats(board: BitBoard, turn: Color): Pair<ULong, ULong> {
        var activeKingThreats: ULong = 0UL
        // Threats that impact the king movement only
        var passiveKingThreats: ULong = 0UL

        for (direction in Direction.sliding) {
            val (activeThreats, passiveThreats) = getSlidingThreatsByDirectionFromKing(board, turn, direction)
            activeKingThreats = activeKingThreats or activeThreats
            passiveKingThreats = passiveKingThreats or passiveThreats
        }

        return activeKingThreats to passiveKingThreats
    }

    private fun getSlidingThreatsByDirectionFromKing(board: BitBoard, turn: Color, direction: Direction): Pair<ULong, ULong> {
        val friendlyKing = board.king(turn)
        val enemyColor = turn.inv()

        val enemySquare = board.rayAttack(friendlyKing, direction, turn)
        if (enemySquare != 0UL) {
            val enemyThreats = when (direction) {
                in Direction.bishops -> enemySquare and (board.queens(enemyColor) or board.bishops(enemyColor))
                in Direction.rooks -> enemySquare and (board.queens(enemyColor) or board.rooks(enemyColor))
                else -> throw IllegalStateException("Unknown direction $direction")
            }
            if (enemyThreats != 0UL) {
                val activeKingThreats = board.rayMoves(friendlyKing, direction, turn)
                // Threats that impact the king movement only
                val passiveKingThreats = board.rayMoves(friendlyKing, direction.inv(), turn)
                return activeKingThreats to passiveKingThreats
            }
        }
        return 0UL to 0UL
    }

    private fun getPawnThreats(board: BitBoard, turn: Color): ULong {
        val enemyColor = turn.inv()
        val friendlyKing = board.king(turn)

        val enemyPawnsNearKing = Compass.kingMoveTargets(friendlyKing) and board.pawns(enemyColor)
        if (enemyPawnsNearKing != 0UL) {
            val enemyPawnAttacks = Compass.pawnAttackTargets(enemyPawnsNearKing, enemyColor).and(friendlyKing)
            if (enemyPawnAttacks.and(friendlyKing) != 0UL) {
                val pawnThreats = when (turn) {
                    Color.WHITE -> enemyPawnsNearKing and Magic.Attack.WhitePawn[Square[friendlyKing]]
                    Color.BLACK -> enemyPawnsNearKing and Magic.Attack.BlackPawn[Square[friendlyKing]]
                }
                return pawnThreats
            }
        }
        return 0UL
    }

    private fun getKnightThreats(board: BitBoard, turn: Color): ULong {
        val enemyColor = turn.inv()
        val friendlyKing = board.king(turn)

        return Compass.knightMoveTargets(friendlyKing).and(board.knights(enemyColor))
    }
}