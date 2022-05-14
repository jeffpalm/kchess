class Board(fen: Fen) : IBoard {
    private var board: Map<Int, Map<Int, Square>>

    init {
        board = fen.toBoard()
    }

    private fun getSquare(x: Int, y: Int): Square {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            throw IllegalArgumentException("x and y must be between 0 and 7")
        }
        return board[y]!![x]!!
    }


    override fun getPiece(x: Int, y: Int): Piece? {
        var square = getSquare(x, y)
        return square.piece
    }

    override fun setPiece(x: Int, y: Int, piece: Piece?) {
        getSquare(x, y).piece = piece
    }

    override fun getKing(pieceColor: PieceColor): Square {
        return getSquaresByPieceColor(pieceColor).first { it.piece?.type == PieceType.KING }
    }

    override fun getSquaresByPieceColor(pieceColor: PieceColor): List<Square> {
        return board.values.flatMap { it.values }.filter { it.piece?.color == pieceColor }
    }

    override fun isPathClear(move: PotentialMove): Boolean {
        if (move.distanceX == 1.toUInt() || move.distanceY == 1.toUInt()) return true
        val (from, to) = move
        val xBetween = intsBetween(from.x, to.x)
        val yBetween = intsBetween(from.y, to.y)
        when (move.trajectory) {
            null -> return false
            MoveTrajectory.VERTICAL -> {
                for (y in yBetween) {
                    if (getSquare(from.x, y).piece != null) return false
                }
            }
            MoveTrajectory.HORIZONTAL -> {
                for (x in xBetween) {
                    if (getSquare(x, from.y).piece != null) return false
                }
            }
            MoveTrajectory.DIAGONAL -> {
                for (i in xBetween.indices) {
                    if (getSquare(xBetween[i], yBetween[i]).piece != null) return false
                }
            }
        }

        return true
    }

    override fun getPotentialMoveSquaresByPiece(square: Square, piece: Piece): List<PotentialMove> {
        val potentialMoves = mutableListOf<PotentialMove>()
        if (piece is Pawn) {
            return listOf(
                PotentialMove(
                    Coords(square.x, square.y), Coords(
                        square.x, (if (piece.color == PieceColor.WHITE) square.y - 1 else square.y + 1)
                    )
                ), PotentialMove(
                    Coords(square.x, square.y), Coords(
                        square.x, (if (piece.color == PieceColor.WHITE) square.y - 2 else square.y + 2)
                    )
                ), PotentialMove(
                    Coords(square.x, square.y), Coords(
                        (square.x + 1), (if (piece.color == PieceColor.WHITE) square.y - 1 else square.y + 1)
                    )
                ), PotentialMove(
                    Coords(square.x, square.y), Coords(
                        (square.x - 1), (if (piece.color == PieceColor.WHITE) square.y - 1 else square.y + 1)
                    )
                )
            )
        }
        for (trajectory in piece.trajectories) {
            when (trajectory) {
                MoveTrajectory.VERTICAL -> {
                    for (y in 0..7) {
                        if (y == square.y) continue
                        potentialMoves.add(
                            PotentialMove(
                                Coords(square.x, square.y),
                                Coords(square.x, y),
                            )
                        )
                    }
                }
                MoveTrajectory.HORIZONTAL -> {
                    for (x in 0..7) {
                        if (x == square.x) continue
                        potentialMoves.add(
                            PotentialMove(
                                Coords(square.x, square.y),
                                Coords(x, square.y),
                            )
                        )
                    }
                }
                MoveTrajectory.DIAGONAL -> {
                    for (i in 1..7) {
                        val ne = try {
                            Coords(square.x + i, square.y - i)
                        } catch (e: Exception) {
                            null
                        }
                        val se = try {
                            Coords(square.x + i, square.y + i)
                        } catch (e: Exception) {
                            null
                        }
                        val nw = try {
                            Coords(square.x - i, square.y - i)
                        } catch (e: Exception) {
                            null
                        }
                        val sw = try {
                            Coords(square.x - i, square.y + i)
                        } catch (e: Exception) {
                            null
                        }
                        if (ne != null) potentialMoves.add(PotentialMove(square.coords, ne))
                        if (se != null) potentialMoves.add(PotentialMove(square.coords, se))
                        if (nw != null) potentialMoves.add(PotentialMove(square.coords, nw))
                        if (sw != null) potentialMoves.add(PotentialMove(square.coords, sw))
                    }
                }
            }
        }
        return potentialMoves
    }

    private fun intsBetween(from: Int, to: Int): List<Int> {
        val list = mutableListOf<Int>()
        if (from < to) {
            for (i in from + 1 until to) {
                list.add(i)
            }
        } else {
            for (i in from - 1 downTo to + 1) {
                list.add(i)
            }
        }
        return list
    }

}