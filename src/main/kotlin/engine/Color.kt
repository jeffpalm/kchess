package engine

enum class Color {
    WHITE, BLACK;
    fun inv(): Color = inv(this)

    companion object {
        fun inv(color: Color) = when (color) {
            WHITE -> BLACK
            BLACK -> WHITE
        }
    }
}