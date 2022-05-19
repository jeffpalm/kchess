package engine.v2.moves

import engine.v2.moves.rules.*

class MoveGenerator(context: MoveGenCtx) : AbstractMoveGenerator(
    context,
    listOf(
        MoveRuleWhitePawnPush(context),
        MoveRuleWhitePawnAttack(context),
        MoveRuleBlackPawnPush(context),
        MoveRuleBlackPawnAttack(context),
        MoveRuleWhiteKnight(context),
        MoveRuleBlackKnight(context),
        MoveRuleWhiteKing(context),
        MoveRuleBlackKing(context),
        MoveRuleWhiteBishop(context),
        MoveRuleBlackBishop(context),
        MoveRuleWhiteRook(context),
        MoveRuleBlackRook(context),
        MoveRuleWhiteQueen(context),
        MoveRuleBlackQueen(context)
    )
)