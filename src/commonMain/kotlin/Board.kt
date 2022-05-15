class Board(fen: Fen) : IBoard {
    private var board: Map<Int, Map<Int, Square>>

    init {
        board = fen.toBoard()
    }

    private fun getSquare(coords: Coords): Square {
        return board[coords.y]!![coords.x]!!
    }


    override fun getPiece(coords: Coords): Piece? {
        return getSquare(coords).piece
    }

    override fun setPiece(coords: Coords, piece: Piece?) {
        getSquare(coords).piece = piece
    }

    override fun getKing(pieceColor: PieceColor): Square {
        return getSquaresByPieceColor(pieceColor).first { it.piece?.type == PieceType.KING }
    }

    override fun getSquaresByPieceColor(pieceColor: PieceColor): List<Square> {
        return board.values.flatMap { it.values }.filter { it.piece?.color == pieceColor }
    }

    override fun getSquareNameByCoords(coords: Coords): String {
        return getSquare(coords).name
    }

    override fun isPathClear(move: Movement): Boolean {
        if (move.distanceX == 1 || move.distanceY == 1) return true
        val (from, to) = move
        val xBetween = intsBetween(from.x, to.x)
        val yBetween = intsBetween(from.y, to.y)
        when (move.trajectory) {
            null -> return false
            MoveTrajectory.VERTICAL -> {
                for (y in yBetween) {
                    if (getPiece(Coords(from.x, y)) != null) return false
                }
            }
            MoveTrajectory.HORIZONTAL -> {
                for (x in xBetween) {
                    if (getPiece(Coords(x, from.y)) != null) return false
                }
            }
            MoveTrajectory.DIAGONAL -> {
                for (i in xBetween.indices) {
                    if (getPiece(Coords(xBetween[i], yBetween[i])) != null) return false
                }
            }
            else -> {}
        }

        return true
    }

    override fun getPotentialMovesBySquareCoords(coords: Coords): List<Movement> {
        val movements = mutableListOf<Movement>()
        val square = board[coords.y]!![coords.x]!!
        val piece = square.piece ?: return movements

        if (piece is Pawn) {
            return getPotentialPawnMoves(piece, coords, square)
        }

        for (trajectory in piece.trajectories) {
            when (trajectory) {
                MoveTrajectory.VERTICAL -> {
                    for (y in 0..7) {
                        if (y == square.y) continue
                        movements.add(
                            Movement(
                                Coords(square.x, square.y),
                                Coords(square.x, y),
                            )
                        )
                    }
                }
                MoveTrajectory.HORIZONTAL -> {
                    for (x in 0..7) {
                        if (x == square.x) continue
                        movements.add(
                            Movement(
                                Coords(square.x, square.y),
                                Coords(x, square.y),
                            )
                        )
                    }
                }
                MoveTrajectory.DIAGONAL -> {
                    for (i in 1..7) {
                        if (i > 1 && piece is King) break
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
                        if (ne != null) movements.add(Movement(square.coords, ne))
                        if (se != null) movements.add(Movement(square.coords, se))
                        if (nw != null) movements.add(Movement(square.coords, nw))
                        if (sw != null) movements.add(Movement(square.coords, sw))
                    }
                }
                MoveTrajectory.KNIGHT -> {
                    for (offset in MoveOffsets.knight) {
                        val target = try {
                            Coords(square.x + offset.first, square.y + offset.second)
                        } catch (e: Exception) {
                            continue
                        }
                        movements.add(Movement(square.coords, target))
                    }
                }
            }
        }
        return movements
    }

    private fun getPotentialPawnMoves(
        piece: Piece,
        coords: Coords,
        square: Square
    ): MutableList<Movement> {
        val pawnMoves = mutableListOf<Movement>()
        val moveList = if (piece.color == PieceColor.WHITE) MoveOffsets.whitePawn else MoveOffsets.blackPawn
        for (potential in moveList) {
            val potentialCoords = try {
                Coords(coords.x + potential.first, coords.y + potential.second)
            } catch (e: IllegalArgumentException) {
                continue
            }
            pawnMoves.add(Movement(square.coords, potentialCoords))
        }
        return pawnMoves
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