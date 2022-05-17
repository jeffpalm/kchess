package engine.v2.moves.pawn

import engine.v2.moves.AbstractMoveGenerator
import engine.v2.moves.PseudoMoveGenContext

class PawnPseudoMoveGenerator(context: PseudoMoveGenContext) : AbstractMoveGenerator<PseudoMoveGenContext>(listOf(
    RulePushMoves(context),
    RuleAttackMoves(context),
),context)