package engine.v2.moves.pawn

import engine.v2.moves.IRule
import engine.v2.moves.MovesGenerator

class PawnMovesGenerator(rules: List<IRule<PawnContext>>, context: PawnContext) : MovesGenerator<PawnContext>(rules, context)