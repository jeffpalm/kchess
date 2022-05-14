data class Coords(val x: Int, val y: Int) {
    init {
        require(x in 0..7) { "x must be in range 0..7" }
        require(y in 0..7) { "y must be in range 0..7" }
    }
}
