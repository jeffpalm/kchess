class Board(fen: Fen) : IBoard {
    private var board: Map<Byte, Map<Byte, Square>>

    init {
        board = fen.toBoard()
    }

    private fun getSquare(x: Byte, y: Byte): Square {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            throw IllegalArgumentException("x and y must be between 0 and 7")
        }
        return board[y]!![x]!!
    }


    override fun getPiece(x: Byte, y: Byte): Piece? {
        var square = getSquare(x, y)
        return square.piece
    }

    override fun setPiece(x: Byte, y: Byte, piece: Piece?) {
        getSquare(x, y).piece = piece
    }

    override fun getKing(pieceColor: PieceColor): Square {
        return getSquaresByPieceColor(pieceColor).first { it.piece?.type == PieceType.KING }
    }

    override fun getSquaresByPieceColor(pieceColor: PieceColor): List<Square> {
        return board.values.flatMap { it.values }.filter { it.piece?.color == pieceColor }
    }

    override fun isPathClear(move: PotentialMove): Boolean {
        if (move.distanceX == 1.toUByte() || move.distanceY == 1.toUByte()) return true
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
                        square.x, (if (piece.color == PieceColor.WHITE) square.y - 1 else square.y + 1).toByte()
                    )
                ), PotentialMove(
                    Coords(square.x, square.y), Coords(
                        square.x, (if (piece.color == PieceColor.WHITE) square.y - 2 else square.y + 2).toByte()
                    )
                ), PotentialMove(
                    Coords(square.x, square.y), Coords(
                        (square.x + 1).toByte(),
                        (if (piece.color == PieceColor.WHITE) square.y - 1 else square.y + 1).toByte()
                    )
                ), PotentialMove(
                    Coords(square.x, square.y), Coords(
                        (square.x - 1).toByte(),
                        (if (piece.color == PieceColor.WHITE) square.y - 1 else square.y + 1).toByte()
                    )
                )
            )
        }
        for (trajectory in piece.trajectories) {
            when (trajectory) {
                MoveTrajectory.VERTICAL -> {
                    for (y in 0..7) {
                        if (y.toByte() == square.y) continue
                        potentialMoves.add(
                            PotentialMove(
                                Coords(square.x, square.y),
                                Coords(square.x, y.toByte()),
                            )
                        )
                    }
                }
                MoveTrajectory.HORIZONTAL -> {
                    for (x in 0..7) {
                        if (x.toByte() == square.x) continue
                        potentialMoves.add(
                            PotentialMove(
                                Coords(square.x, square.y),
                                Coords(x.toByte(), square.y),
                            )
                        )
                    }
                }
                MoveTrajectory.DIAGONAL -> {
                    for (i in 1.toByte()..7.toByte()) {
                        val ne = try {
                            Coords((square.x + i).toByte(), (square.y - i).toByte())
                        } catch (e: IllegalArgumentException) {
                            null
                        }
                        val se = try {
                            Coords((square.x + i).toByte(), (square.y + i).toByte())
                        } catch (e: IllegalArgumentException) {
                            null
                        }
                        val nw = try {
                            Coords((square.x - i).toByte(), (square.y - i).toByte())
                        } catch (e: IllegalArgumentException) {
                            null
                        }
                        val sw = try {
                            Coords((square.x - i).toByte(), (square.y + i).toByte())
                        } catch (e: IllegalArgumentException) {
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

    private fun intsBetween(from: Byte, to: Byte): List<Byte> {
        val list = mutableListOf<Byte>()
        if (from < to) {
            for (i in from + 1 until to) {
                list.add(i.toByte())
            }
        } else {
            for (i in from - 1 downTo to + 1) {
                list.add(i.toByte())
            }
        }
        return list
    }

}