package engine.move

import engine.move.filters.MoveFilterAbsolutePins
import engine.move.filters.MoveFilterEnPassantCaptureEdgeCase
import engine.move.filters.MoveFilterKingInActiveCheck
import engine.move.rules.*

class MoveGenerator(context: MoveGenCtx) : AbstractMoveGenerator(context, RULES, FILTERS) {
    companion object {
        private val RULES: List<IMoveRule> = listOf(
            MoveRuleWhitePawnPush,
            MoveRuleWhitePawnAttack,
            MoveRuleBlackPawnPush,
            MoveRuleBlackPawnAttack,
            MoveRuleKnight,
            MoveRuleKing,
            MoveRuleBishop,
            MoveRuleRook,
            MoveRuleQueen,
            MoveRuleCastle,
        )
        private val FILTERS: List<IMoveFilter> = listOf(
            MoveFilterAbsolutePins,
            MoveFilterKingInActiveCheck,
            MoveFilterEnPassantCaptureEdgeCase,
        )

        /**
         * Allocation-free entry point for the production rule+filter pipeline.
         * Runs against a caller-owned [ctx] (which they may reuse). Tests that
         * want a custom rule/filter set can still subclass [AbstractMoveGenerator].
         */
        fun executeOn(ctx: MoveGenCtx): List<PseudoMove> {
            for (rule in RULES) if (rule.shouldRun(ctx)) rule.run(ctx)
            for (filter in FILTERS) filter.run(ctx)
            return ctx.moves()
        }
    }
}
