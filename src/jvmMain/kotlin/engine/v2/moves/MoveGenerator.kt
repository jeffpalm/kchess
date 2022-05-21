package engine.v2.moves

import engine.v2.moves.filters.MoveFilterAbsolutePins
import engine.v2.moves.filters.MoveFilterKingInActiveCheck
import engine.v2.moves.rules.*

class MoveGenerator(context: MoveGenCtx) : AbstractMoveGenerator(
    context,
    listOf(
        MoveRuleWhitePawnPush(),
        MoveRuleWhitePawnAttack(),
        MoveRuleBlackPawnPush(),
        MoveRuleBlackPawnAttack(),
        MoveRuleWhiteKnight(),
        MoveRuleBlackKnight(),
        MoveRuleWhiteKing(),
        MoveRuleBlackKing(),
        MoveRuleWhiteBishop(),
        MoveRuleBlackBishop(),
        MoveRuleWhiteRook(),
        MoveRuleBlackRook(),
        MoveRuleWhiteQueen(),
        MoveRuleBlackQueen(),
        MoveRuleCastleWhite(),
        MoveRuleCastleBlack()
    ),
    listOf(
        MoveFilterAbsolutePins(),
        MoveFilterKingInActiveCheck()
    )
)