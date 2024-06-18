package engine

enum class Square {
    a1, b1, c1, d1, e1, f1, g1, h1,
    a2, b2, c2, d2, e2, f2, g2, h2,
    a3, b3, c3, d3, e3, f3, g3, h3,
    a4, b4, c4, d4, e4, f4, g4, h4,
    a5, b5, c5, d5, e5, f5, g5, h5,
    a6, b6, c6, d6, e6, f6, g6, h6,
    a7, b7, c7, d7, e7, f7, g7, h7,
    a8, b8, c8, d8, e8, f8, g8, h8;

    fun asBit(): ULong = when (this) {
        a1 -> Sq.a1
        b1 -> Sq.b1
        c1 -> Sq.c1
        d1 -> Sq.d1
        e1 -> Sq.e1
        f1 -> Sq.f1
        g1 -> Sq.g1
        h1 -> Sq.h1
        a2 -> Sq.a2
        b2 -> Sq.b2
        c2 -> Sq.c2
        d2 -> Sq.d2
        e2 -> Sq.e2
        f2 -> Sq.f2
        g2 -> Sq.g2
        h2 -> Sq.h2
        a3 -> Sq.a3
        b3 -> Sq.b3
        c3 -> Sq.c3
        d3 -> Sq.d3
        e3 -> Sq.e3
        f3 -> Sq.f3
        g3 -> Sq.g3
        h3 -> Sq.h3
        a4 -> Sq.a4
        b4 -> Sq.b4
        c4 -> Sq.c4
        d4 -> Sq.d4
        e4 -> Sq.e4
        f4 -> Sq.f4
        g4 -> Sq.g4
        h4 -> Sq.h4
        a5 -> Sq.a5
        b5 -> Sq.b5
        c5 -> Sq.c5
        d5 -> Sq.d5
        e5 -> Sq.e5
        f5 -> Sq.f5
        g5 -> Sq.g5
        h5 -> Sq.h5
        a6 -> Sq.a6
        b6 -> Sq.b6
        c6 -> Sq.c6
        d6 -> Sq.d6
        e6 -> Sq.e6
        f6 -> Sq.f6
        g6 -> Sq.g6
        h6 -> Sq.h6
        a7 -> Sq.a7
        b7 -> Sq.b7
        c7 -> Sq.c7
        d7 -> Sq.d7
        e7 -> Sq.e7
        f7 -> Sq.f7
        g7 -> Sq.g7
        h7 -> Sq.h7
        a8 -> Sq.a8
        b8 -> Sq.b8
        c8 -> Sq.c8
        d8 -> Sq.d8
        e8 -> Sq.e8
        f8 -> Sq.f8
        g8 -> Sq.g8
        h8 -> Sq.h8
    }

    companion object {
        operator fun get(str: String): Square = Square.valueOf(str.lowercase())

        operator fun get(index: Int): Square = Square.values()[index]

        operator fun get(word: ULong): Square = when (word) {
            Sq.a1 -> a1
            Sq.b1 -> b1
            Sq.c1 -> c1
            Sq.d1 -> d1
            Sq.e1 -> e1
            Sq.f1 -> f1
            Sq.g1 -> g1
            Sq.h1 -> h1
            Sq.a2 -> a2
            Sq.b2 -> b2
            Sq.c2 -> c2
            Sq.d2 -> d2
            Sq.e2 -> e2
            Sq.f2 -> f2
            Sq.g2 -> g2
            Sq.h2 -> h2
            Sq.a3 -> a3
            Sq.b3 -> b3
            Sq.c3 -> c3
            Sq.d3 -> d3
            Sq.e3 -> e3
            Sq.f3 -> f3
            Sq.g3 -> g3
            Sq.h3 -> h3
            Sq.a4 -> a4
            Sq.b4 -> b4
            Sq.c4 -> c4
            Sq.d4 -> d4
            Sq.e4 -> e4
            Sq.f4 -> f4
            Sq.g4 -> g4
            Sq.h4 -> h4
            Sq.a5 -> a5
            Sq.b5 -> b5
            Sq.c5 -> c5
            Sq.d5 -> d5
            Sq.e5 -> e5
            Sq.f5 -> f5
            Sq.g5 -> g5
            Sq.h5 -> h5
            Sq.a6 -> a6
            Sq.b6 -> b6
            Sq.c6 -> c6
            Sq.d6 -> d6
            Sq.e6 -> e6
            Sq.f6 -> f6
            Sq.g6 -> g6
            Sq.h6 -> h6
            Sq.a7 -> a7
            Sq.b7 -> b7
            Sq.c7 -> c7
            Sq.d7 -> d7
            Sq.e7 -> e7
            Sq.f7 -> f7
            Sq.g7 -> g7
            Sq.h7 -> h7
            Sq.a8 -> a8
            Sq.b8 -> b8
            Sq.c8 -> c8
            Sq.d8 -> d8
            Sq.e8 -> e8
            Sq.f8 -> f8
            Sq.g8 -> g8
            Sq.h8 -> h8
            else -> throw IllegalArgumentException("Invalid square: $word")
        }

        operator fun get(byte: Byte): Square = Square.values()[byte.toInt()]
    }
}
