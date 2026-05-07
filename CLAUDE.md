# kchess

Bitboard chess engine in Kotlin. Early-stage; refactor freely. Standard workflow is **red/green TDD** — write a failing test first.

## Build & test

- Kotlin 2.0, Gradle 8.5, JVM 21 (`build.gradle.kts` declares `jvmToolchain(21)`).
- All commands: `./gradlew test` (runs everything), `./gradlew test --tests "engineTest.PerftTest"` (one class), `./gradlew test --tests "engineTest.PerftTest.Position 4 - Depth 3" -i` (single test, verbose stdout).
- HTML report: `build/reports/tests/test/index.html`.
- If gradle complains it can't find a JDK, point it at one: `JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64 ./gradlew …`.
- `Stockfish` is available at `/usr/games/stockfish` for reference perft (`go perft N`) when debugging move-generation bugs.

## Layout

```
src/main/kotlin/
  Client.kt, Demos.kt          scratchpad / demos — not the API
  engine/
    BitBoard.kt                12 ULong piece bitboards + EP/castling/turn; makeMove/undoMove
    Board.kt                   Map<Byte, Char?> "squares" view; used for FEN + log() rendering
    Game.kt                    orchestrator; keeps Board and BitBoard in sync
    GameData.kt, IGameData.kt  state record (board, turn, castle string, EP, clocks)
    Move.kt, IMove.kt          a played move (resolves capture, EP, promo on construction)
    Fen.kt                     FEN string parsing
    Square.kt (enum a1..h8), Sq.kt (ULong bit constants),
    Piece.kt ('P','N',… white; 'p','n',… black), Color.kt
    Direction.kt, Compass.kt   geometry / ray helpers
    Sets.kt                    file/rank/diagonal masks (see Gotchas)
    Perft.kt                   perft(depth, game) — prints divide at startDepth
    move/
      Magic.kt                 ~2k lines of precomputed Attack[]/Ray[]/EnPassantCaptureSq tables
      MoveGenerator.kt         the production rule+filter pipeline
      AbstractMoveGenerator.kt rules first (each addMoves), then filters in order
      MoveGenCtx.kt            mutable bag: data, moves, addMove(s), filterMoves
      PseudoMove.kt            (from, to, piece, promo?) — produced by rules
      IMoveRule.kt             shouldRun + suspend run(ctx)
      IMoveFilter.kt           suspend run(ctx): MoveGenCtx — strips illegal moves
      rules/                   one per piece + Castle + pawn push/attack split by color
      filters/                 AbsolutePins, KingInActiveCheck, EnPassantCaptureEdgeCase
    adapter/                   small one-shot transforms (FenToBitBoard, BitsToListOfBit, …)
                               all extend sealed Adapter<I,O> with `output: O`
src/test/kotlin/engineTest/    JUnit 5 + kotlin.test; PerftTest is the integration backbone
```

## Conventions

- Pieces are `Char`. White uppercase (`P N B R Q K`), black lowercase. `Piece.pawn(WHITE) == 'P'`, etc.
- `Square.x1` is the enum, `Sq.x1` is the matching ULong bit. Conversions: `square.asBit()`, `Square[bit]`, `Sq[name]`.
- Bitboard layout: `a1 = bit 0`, `h1 = bit 7`, `a8 = bit 56`, `h8 = bit 63`. Hence `Compass.navigate`: N=`shl 8`, NW=`shl 7`, NE=`shl 9`, S/SE/SW are `shr`. `Direction.positive` = NW/N/NE/E (closest = `takeLowestOneBit`); `Direction.negative` = SE/S/SW/W (closest = `takeHighestOneBit`).
- `IGameData.castleAvail` is a `String` containing some subset of `"KQkq"` (or `"-"`).
- `BitBoard.allAttackTargets(color)` is the canonical "squares the king of `color.inv()` cannot move to": sliders treat the enemy king as **transparent** (so it can't escape along the attacker's ray) and **include** squares occupied by the attacker's friendlies (covered squares). Includes king attacks. Don't reintroduce a "stop at first piece" version for king-move filtering.

## Move generation pipeline

`MoveGenerator(MoveGenCtx(gameData)).execute()` returns a `Set<PseudoMove>` of fully legal moves.

1. Each registered rule's `run(ctx)` adds pseudo-legal moves via `ctx.addMoves(...)`. Rules are launched as coroutines under a single `runBlocking`; with no suspension points they run sequentially in declaration order — fine today, but don't introduce `yield`/IO without auditing the shared `MoveGenCtx`.
2. Each filter's `run(ctx)` calls `ctx.filterMoves { … }` to strip illegal moves. Filters are also launched, but mutate the same `moves` set — they currently run sequentially for the same reason. Order of filters in `MoveGenerator` matters semantically.

Rules and filters are pure-ish: they read `ctx.data`, write `ctx`. State changes between plies happen through `Game.makeMove(PseudoMove)` / `Game.undoMove()`, never inside a rule.

## Game.makeMove must keep two boards in sync

`Game._board` (squares) and `Game._data.board` (BitBoard) both have to reflect each move. Every special move type touches both:

- **Castling** moves the king **and** the rook (`handleCastleRookMove`), and removes castling rights for the moving side.
- **En passant** captures remove the captured pawn from a square that is **not** `move.to` (use `Magic.EnPassantCaptureSq[move.to]`).
- **Promotion** flips the pawn bit off and the promoted piece bit on; `_board.setSquare(to, promo ?: piece)`.
- **Rook-from-starting-square moves OR captures** drop the corresponding castling right. Capture-of-rook is checked via `m.to in {a1,h1,a8,h8}` + `m.capture == 'R'/'r'`.

`Game.clone()` round-trips through FEN (`GameToFen` → `Game(Fen(...))`), so any inconsistency between `Board` and `BitBoard` is silently lost on clone. If you add a new special-case move, make sure both reps are updated *and* serialize correctly.

## Filters — non-obvious invariants

- **`MoveFilterAbsolutePins`** stores a per-piece allowed-targets bitmap (`Map<fromBit, allowedToBits>`). Each pinned piece is restricted to **its own** pin line (king→protector squares ∪ protector→enemy squares ∪ pinning enemy square). Don't union pin lines across directions — different pins can produce overlapping squares that would let a piece escape its actual pin.
- **`MoveFilterKingInActiveCheck`** has a special case for en passant: if the moving piece is a pawn and `to == enPassantTarget`, the *captured* square (one rank away) must be on `activePawnThreats`, not the move's `to`.
- **`MoveFilterEnPassantCaptureEdgeCase`** simulates the EP capture and rejects it if any sliding ray now attacks the friendly king (the famous "EP unpins your pawn" position).

## MoveRuleCastle gates

In order: castling right present → king not in check → all path squares between rook and king empty (b-file matters for queenside!) → squares the king walks across not attacked. `allAttackTargets` is computed on the pre-move board, which is correct for the king-pass-through check because the king is treated as transparent.

## Perft is the regression suite

`engineTest/PerftTest.kt` runs known perft positions/depths from chessprogramming.org. When something breaks at depth N but passes at depth N-1, drill in:

1. Run our `Perft.run(N, game)` — it auto-prints a per-move divide at `startDepth`.
2. Run Stockfish for the same FEN: `echo "position fen <FEN>\ngo perft N\nquit" | /usr/games/stockfish`.
3. Diff the two divides. The diverging move is the next thing to recurse into.

When adding a new perft case: it must come from chessprogramming.org or a Stockfish run on a known FEN — never invent expected counts.

## Gotchas / sharp edges

- `Sets.kt` contains a long tail of `*_SHIFT_*` / `*_SHIFT_RIGHT_n` / `*_SHIFT_LEFT_n` constants that are all assigned the same two values (`0x55AA…` / `0xAA55…`). They are clearly placeholder/wrong and unused. If you reach for one, double-check the value before trusting it.
- `Move.enPassantTarget()` only returns non-null for *starting-square* two-square pawn pushes. EP target on the resulting `GameData` is set via `handleEnPassantTarget`, which calls this and assigns `null` otherwise — relied on to clear stale EP targets on every other move type.
- `Perft.run` prints to stdout at start depth and depth 1. It's not silent.
- `Client.kt` is a scratchpad, not a public entry point. Don't build infra around its current contents.
- The `IMoveRule` doc comment says rules "run in parallel". They effectively don't (single-thread dispatcher, no yield points). Treat the comment as aspirational.
