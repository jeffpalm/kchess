package engine.v1

data class Square(val y: Int, val x: Int, val name: String, var piece: Piece? = null) {
    val coords: Coords = Coords(x, y)

    init {
        require(x in 0..7) { "x must be in range 0..7" }
        require(y in 0..7) { "y must be in range 0..7" }
    }
}