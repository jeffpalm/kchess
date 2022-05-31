Credit: https://www.reddit.com/r/ComputerChess/comments/b6rdez/comment/ejpohen/?utm_source=share&utm_medium=web2x&context=3

Step 1: Initiate your chess engine executable in command line (on mac/unix this is ./stockfish)

Step 2: Type: `isready` (this step isn't necessary for stockfish, but some engines do (e.g. Discocheck and Quazar)

Output: readyok

Step 3: Type 'uci'

The Output, should provide the engine ID, version number, and author information, followed by a list of all supported uci options, such as Hash, Threads, MultiPV, Ponder, etc...

This also shows you the default setting for each parameter

The uci string always ends on a newline 'uciok'



Step 4: How to change a supported UCI Option (Generic Formula)

`setoption name [supported uci option] value [value you want to change it to]`

e.g. to change hash size to 1024 MB and use 2 threads, type the following into commandline:

`setoption name hash value 1024`

`setoption name threads value 2`

*Note: that the option name is case insensitive, so you could write instead : setoption name HaSh value 1024, and get the same results



Step 5: Set or change the position

How to set the Starting Position

`position startpos`

How to Move (e.g. move pawn to e4 from starting position)

`position startpos moves e2e4`

Note that you must use uci notation (a variant of long algebraic notation) of moves which only includes the square it comes from and square it goes to;

In order to castle kingside, you must use the notation e1g1 (or e8g8), to castle queenside : e1c1 (e8c8)

How to set a Position with a specific fen string

`position fen [fen string here]`

e.g. change position to this fen : 4kb1r/p2rqppp/5n2/1B2p1B1/4P3/1Q6/PPP2PPP/2K4R w k - 0 14

`position fen 4kb1r/p2rqppp/5n2/1B2p1B1/4P3/1Q6/PPP2PPP/2K4R w k - 0 14`

How to make a move from a specific fen position (using above example)

`position fen 4kb1r/p2rqppp/5n2/1B2p1B1/4P3/1Q6/PPP2PPP/2K4R w k - 0 14 moves h1d1`



Step 6: Search / Analysis... Type 'go', followed by any number of commands:

`infinite`

`depth [ply depth]`

`movetime [time in ms]`

Note: there are other options available, but they really aren't useful without a gui (such as setting movestogo, winc, binc)



Stockfish Specific Commands (i.e. not portable to other uci engines)

Stockfish can display a diagram of the current position

Type 'd' into command line

Stockfish can display a static eval (and breakdown of the position)

Type 'eval' into command line

Benchmark Testing

type 'bench'

PERFT

type: 'go perft [ply depth]'