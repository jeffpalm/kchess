package engine.move

import engine.move.rules.*
import engine.move.filters.MoveFilterAbsolutePins
import engine.move.filters.MoveFilterKingInActiveCheck

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