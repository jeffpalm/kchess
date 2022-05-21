package engine

enum class Color {
    WHITE, BLACK;

    companion object {
        fun inv(color: Color) = when (color) {
            WHITE -> BLACK
            BLACK -> WHITE
        }
    }
}