package engine.move

import engine.move.filters.MoveFilterAbsolutePins
import engine.move.filters.MoveFilterEnPassantCaptureEdgeCase
import engine.move.filters.MoveFilterKingInActiveCheck
import engine.move.rules.*

class MoveGenerator(context: MoveGenCtx) : AbstractMoveGenerator(
    context,
    listOf(
        MoveRuleWhitePawnPush(),
        MoveRuleWhitePawnAttack(),
        MoveRuleBlackPawnPush(),
        MoveRuleBlackPawnAttack(),
        MoveRuleKnight(),
        MoveRuleKing(),
        MoveRuleBishop(),
        MoveRuleRook(),
        MoveRuleQueen(),
        MoveRuleCastle(),
    ),
    listOf(
        MoveFilterAbsolutePins(),
        MoveFilterKingInActiveCheck(),
        MoveFilterEnPassantCaptureEdgeCase()
    )
)