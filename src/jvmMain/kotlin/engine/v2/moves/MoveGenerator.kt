package engine.v2.moves

import engine.v2.moves.rules.*

class MoveGenerator(context: PseudoMoveGenContext) : AbstractMoveGenerator<PseudoMoveGenContext>(
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
    )
)