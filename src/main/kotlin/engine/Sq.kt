package engine

object Sq {
    object IdxInt {
        operator fun get(word: ULong): Int = when (word) {
            a1 -> 0
            b1 -> 1
            c1 -> 2
            d1 -> 3
            e1 -> 4
            f1 -> 5
            g1 -> 6
            h1 -> 7
            a2 -> 8
            b2 -> 9
            c2 -> 10
            d2 -> 11
            e2 -> 12
            f2 -> 13
            g2 -> 14
            h2 -> 15
            a3 -> 16
            b3 -> 17
            c3 -> 18
            d3 -> 19
            e3 -> 20
            f3 -> 21
            g3 -> 22
            h3 -> 23
            a4 -> 24
            b4 -> 25
            c4 -> 26
            d4 -> 27
            e4 -> 28
            f4 -> 29
            g4 -> 30
            h4 -> 31
            a5 -> 32
            b5 -> 33
            c5 -> 34
            d5 -> 35
            e5 -> 36
            f5 -> 37
            g5 -> 38
            h5 -> 39
            a6 -> 40
            b6 -> 41
            c6 -> 42
            d6 -> 43
            e6 -> 44
            f6 -> 45
            g6 -> 46
            h6 -> 47
            a7 -> 48
            b7 -> 49
            c7 -> 50
            d7 -> 51
            e7 -> 52
            f7 -> 53
            g7 -> 54
            h7 -> 55
            a8 -> 56
            b8 -> 57
            c8 -> 58
            d8 -> 59
            e8 -> 60
            f8 -> 61
            g8 -> 62
            h8 -> 63
            else -> throw IllegalArgumentException("Invalid square")
        }

        operator fun get(name: String): Int = when (name) {
            "a1" -> 0
            "b1" -> 1
            "c1" -> 2
            "d1" -> 3
            "e1" -> 4
            "f1" -> 5
            "g1" -> 6
            "h1" -> 7
            "a2" -> 8
            "b2" -> 9
            "c2" -> 10
            "d2" -> 11
            "e2" -> 12
            "f2" -> 13
            "g2" -> 14
            "h2" -> 15
            "a3" -> 16
            "b3" -> 17
            "c3" -> 18
            "d3" -> 19
            "e3" -> 20
            "f3" -> 21
            "g3" -> 22
            "h3" -> 23
            "a4" -> 24
            "b4" -> 25
            "c4" -> 26
            "d4" -> 27
            "e4" -> 28
            "f4" -> 29
            "g4" -> 30
            "h4" -> 31
            "a5" -> 32
            "b5" -> 33
            "c5" -> 34
            "d5" -> 35
            "e5" -> 36
            "f5" -> 37
            "g5" -> 38
            "h5" -> 39
            "a6" -> 40
            "b6" -> 41
            "c6" -> 42
            "d6" -> 43
            "e6" -> 44
            "f6" -> 45
            "g6" -> 46
            "h6" -> 47
            "a7" -> 48
            "b7" -> 49
            "c7" -> 50
            "d7" -> 51
            "e7" -> 52
            "f7" -> 53
            "g7" -> 54
            "h7" -> 55
            "a8" -> 56
            "b8" -> 57
            "c8" -> 58
            "d8" -> 59
            "e8" -> 60
            "f8" -> 61
            "g8" -> 62
            "h8" -> 63
            else -> throw IllegalArgumentException("Invalid square $name")
        }
    }

    object IdxByte {
        operator fun get(word: ULong): Byte = when (word) {
            a1 -> 0.toByte()
            b1 -> 1.toByte()
            c1 -> 2.toByte()
            d1 -> 3.toByte()
            e1 -> 4.toByte()
            f1 -> 5.toByte()
            g1 -> 6.toByte()
            h1 -> 7.toByte()
            a2 -> 8.toByte()
            b2 -> 9.toByte()
            c2 -> 10.toByte()
            d2 -> 11.toByte()
            e2 -> 12.toByte()
            f2 -> 13.toByte()
            g2 -> 14.toByte()
            h2 -> 15.toByte()
            a3 -> 16.toByte()
            b3 -> 17.toByte()
            c3 -> 18.toByte()
            d3 -> 19.toByte()
            e3 -> 20.toByte()
            f3 -> 21.toByte()
            g3 -> 22.toByte()
            h3 -> 23.toByte()
            a4 -> 24.toByte()
            b4 -> 25.toByte()
            c4 -> 26.toByte()
            d4 -> 27.toByte()
            e4 -> 28.toByte()
            f4 -> 29.toByte()
            g4 -> 30.toByte()
            h4 -> 31.toByte()
            a5 -> 32.toByte()
            b5 -> 33.toByte()
            c5 -> 34.toByte()
            d5 -> 35.toByte()
            e5 -> 36.toByte()
            f5 -> 37.toByte()
            g5 -> 38.toByte()
            h5 -> 39.toByte()
            a6 -> 40.toByte()
            b6 -> 41.toByte()
            c6 -> 42.toByte()
            d6 -> 43.toByte()
            e6 -> 44.toByte()
            f6 -> 45.toByte()
            g6 -> 46.toByte()
            h6 -> 47.toByte()
            a7 -> 48.toByte()
            b7 -> 49.toByte()
            c7 -> 50.toByte()
            d7 -> 51.toByte()
            e7 -> 52.toByte()
            f7 -> 53.toByte()
            g7 -> 54.toByte()
            h7 -> 55.toByte()
            a8 -> 56.toByte()
            b8 -> 57.toByte()
            c8 -> 58.toByte()
            d8 -> 59.toByte()
            e8 -> 60.toByte()
            f8 -> 61.toByte()
            g8 -> 62.toByte()
            h8 -> 63.toByte()
            else -> throw IllegalArgumentException("Invalid square")
        }

        operator fun get(name: String): Byte = when (name) {
            "a1" -> 0.toByte()
            "b1" -> 1.toByte()
            "c1" -> 2.toByte()
            "d1" -> 3.toByte()
            "e1" -> 4.toByte()
            "f1" -> 5.toByte()
            "g1" -> 6.toByte()
            "h1" -> 7.toByte()
            "a2" -> 8.toByte()
            "b2" -> 9.toByte()
            "c2" -> 10.toByte()
            "d2" -> 11.toByte()
            "e2" -> 12.toByte()
            "f2" -> 13.toByte()
            "g2" -> 14.toByte()
            "h2" -> 15.toByte()
            "a3" -> 16.toByte()
            "b3" -> 17.toByte()
            "c3" -> 18.toByte()
            "d3" -> 19.toByte()
            "e3" -> 20.toByte()
            "f3" -> 21.toByte()
            "g3" -> 22.toByte()
            "h3" -> 23.toByte()
            "a4" -> 24.toByte()
            "b4" -> 25.toByte()
            "c4" -> 26.toByte()
            "d4" -> 27.toByte()
            "e4" -> 28.toByte()
            "f4" -> 29.toByte()
            "g4" -> 30.toByte()
            "h4" -> 31.toByte()
            "a5" -> 32.toByte()
            "b5" -> 33.toByte()
            "c5" -> 34.toByte()
            "d5" -> 35.toByte()
            "e5" -> 36.toByte()
            "f5" -> 37.toByte()
            "g5" -> 38.toByte()
            "h5" -> 39.toByte()
            "a6" -> 40.toByte()
            "b6" -> 41.toByte()
            "c6" -> 42.toByte()
            "d6" -> 43.toByte()
            "e6" -> 44.toByte()
            "f6" -> 45.toByte()
            "g6" -> 46.toByte()
            "h6" -> 47.toByte()
            "a7" -> 48.toByte()
            "b7" -> 49.toByte()
            "c7" -> 50.toByte()
            "d7" -> 51.toByte()
            "e7" -> 52.toByte()
            "f7" -> 53.toByte()
            "g7" -> 54.toByte()
            "h7" -> 55.toByte()
            "a8" -> 56.toByte()
            "b8" -> 57.toByte()
            "c8" -> 58.toByte()
            "d8" -> 59.toByte()
            "e8" -> 60.toByte()
            "f8" -> 61.toByte()
            "g8" -> 62.toByte()
            "h8" -> 63.toByte()
            else -> throw IllegalArgumentException("Invalid square $name")
        }
    }

    object Name {
        operator fun get(word: ULong): String = when (word) {
            a1 -> "a1"
            b1 -> "b1"
            c1 -> "c1"
            d1 -> "d1"
            e1 -> "e1"
            f1 -> "f1"
            g1 -> "g1"
            h1 -> "h1"
            a2 -> "a2"
            b2 -> "b2"
            c2 -> "c2"
            d2 -> "d2"
            e2 -> "e2"
            f2 -> "f2"
            g2 -> "g2"
            h2 -> "h2"
            a3 -> "a3"
            b3 -> "b3"
            c3 -> "c3"
            d3 -> "d3"
            e3 -> "e3"
            f3 -> "f3"
            g3 -> "g3"
            h3 -> "h3"
            a4 -> "a4"
            b4 -> "b4"
            c4 -> "c4"
            d4 -> "d4"
            e4 -> "e4"
            f4 -> "f4"
            g4 -> "g4"
            h4 -> "h4"
            a5 -> "a5"
            b5 -> "b5"
            c5 -> "c5"
            d5 -> "d5"
            e5 -> "e5"
            f5 -> "f5"
            g5 -> "g5"
            h5 -> "h5"
            a6 -> "a6"
            b6 -> "b6"
            c6 -> "c6"
            d6 -> "d6"
            e6 -> "e6"
            f6 -> "f6"
            g6 -> "g6"
            h6 -> "h6"
            a7 -> "a7"
            b7 -> "b7"
            c7 -> "c7"
            d7 -> "d7"
            e7 -> "e7"
            f7 -> "f7"
            g7 -> "g7"
            h7 -> "h7"
            a8 -> "a8"
            b8 -> "b8"
            c8 -> "c8"
            d8 -> "d8"
            e8 -> "e8"
            f8 -> "f8"
            g8 -> "g8"
            h8 -> "h8"
            else -> throw IllegalArgumentException("Invalid square")
        }

        operator fun get(idx: Int): String = when (idx) {
            0 -> "a1"
            1 -> "b1"
            2 -> "c1"
            3 -> "d1"
            4 -> "e1"
            5 -> "f1"
            6 -> "g1"
            7 -> "h1"
            8 -> "a2"
            9 -> "b2"
            10 -> "c2"
            11 -> "d2"
            12 -> "e2"
            13 -> "f2"
            14 -> "g2"
            15 -> "h2"
            16 -> "a3"
            17 -> "b3"
            18 -> "c3"
            19 -> "d3"
            20 -> "e3"
            21 -> "f3"
            22 -> "g3"
            23 -> "h3"
            24 -> "a4"
            25 -> "b4"
            26 -> "c4"
            27 -> "d4"
            28 -> "e4"
            29 -> "f4"
            30 -> "g4"
            31 -> "h4"
            32 -> "a5"
            33 -> "b5"
            34 -> "c5"
            35 -> "d5"
            36 -> "e5"
            37 -> "f5"
            38 -> "g5"
            39 -> "h5"
            40 -> "a6"
            41 -> "b6"
            42 -> "c6"
            43 -> "d6"
            44 -> "e6"
            45 -> "f6"
            46 -> "g6"
            47 -> "h6"
            48 -> "a7"
            49 -> "b7"
            50 -> "c7"
            51 -> "d7"
            52 -> "e7"
            53 -> "f7"
            54 -> "g7"
            55 -> "h7"
            56 -> "a8"
            57 -> "b8"
            58 -> "c8"
            59 -> "d8"
            60 -> "e8"
            61 -> "f8"
            62 -> "g8"
            63 -> "h8"
            else -> throw IllegalArgumentException("Invalid square $idx")
        }

        operator fun get(byte: Byte): String = when (byte) {
            0.toByte() -> "a1"
            1.toByte() -> "b1"
            2.toByte() -> "c1"
            3.toByte() -> "d1"
            4.toByte() -> "e1"
            5.toByte() -> "f1"
            6.toByte() -> "g1"
            7.toByte() -> "h1"
            8.toByte() -> "a2"
            9.toByte() -> "b2"
            10.toByte() -> "c2"
            11.toByte() -> "d2"
            12.toByte() -> "e2"
            13.toByte() -> "f2"
            14.toByte() -> "g2"
            15.toByte() -> "h2"
            16.toByte() -> "a3"
            17.toByte() -> "b3"
            18.toByte() -> "c3"
            19.toByte() -> "d3"
            20.toByte() -> "e3"
            21.toByte() -> "f3"
            22.toByte() -> "g3"
            23.toByte() -> "h3"
            24.toByte() -> "a4"
            25.toByte() -> "b4"
            26.toByte() -> "c4"
            27.toByte() -> "d4"
            28.toByte() -> "e4"
            29.toByte() -> "f4"
            30.toByte() -> "g4"
            31.toByte() -> "h4"
            32.toByte() -> "a5"
            33.toByte() -> "b5"
            34.toByte() -> "c5"
            35.toByte() -> "d5"
            36.toByte() -> "e5"
            37.toByte() -> "f5"
            38.toByte() -> "g5"
            39.toByte() -> "h5"
            40.toByte() -> "a6"
            41.toByte() -> "b6"
            42.toByte() -> "c6"
            43.toByte() -> "d6"
            44.toByte() -> "e6"
            45.toByte() -> "f6"
            46.toByte() -> "g6"
            47.toByte() -> "h6"
            48.toByte() -> "a7"
            49.toByte() -> "b7"
            50.toByte() -> "c7"
            51.toByte() -> "d7"
            52.toByte() -> "e7"
            53.toByte() -> "f7"
            54.toByte() -> "g7"
            55.toByte() -> "h7"
            56.toByte() -> "a8"
            57.toByte() -> "b8"
            58.toByte() -> "c8"
            59.toByte() -> "d8"
            60.toByte() -> "e8"
            61.toByte() -> "f8"
            62.toByte() -> "g8"
            63.toByte() -> "h8"
            else -> throw IllegalArgumentException("Invalid square $byte")
        }

    }

    operator fun get(byte: Byte): ULong = when (byte) {
        0.toByte() -> a1
        1.toByte() -> b1
        2.toByte() -> c1
        3.toByte() -> d1
        4.toByte() -> e1
        5.toByte() -> f1
        6.toByte() -> g1
        7.toByte() -> h1
        8.toByte() -> a2
        9.toByte() -> b2
        10.toByte() -> c2
        11.toByte() -> d2
        12.toByte() -> e2
        13.toByte() -> f2
        14.toByte() -> g2
        15.toByte() -> h2
        16.toByte() -> a3
        17.toByte() -> b3
        18.toByte() -> c3
        19.toByte() -> d3
        20.toByte() -> e3
        21.toByte() -> f3
        22.toByte() -> g3
        23.toByte() -> h3
        24.toByte() -> a4
        25.toByte() -> b4
        26.toByte() -> c4
        27.toByte() -> d4
        28.toByte() -> e4
        29.toByte() -> f4
        30.toByte() -> g4
        31.toByte() -> h4
        32.toByte() -> a5
        33.toByte() -> b5
        34.toByte() -> c5
        35.toByte() -> d5
        36.toByte() -> e5
        37.toByte() -> f5
        38.toByte() -> g5
        39.toByte() -> h5
        40.toByte() -> a6
        41.toByte() -> b6
        42.toByte() -> c6
        43.toByte() -> d6
        44.toByte() -> e6
        45.toByte() -> f6
        46.toByte() -> g6
        47.toByte() -> h6
        48.toByte() -> a7
        49.toByte() -> b7
        50.toByte() -> c7
        51.toByte() -> d7
        52.toByte() -> e7
        53.toByte() -> f7
        54.toByte() -> g7
        55.toByte() -> h7
        56.toByte() -> a8
        57.toByte() -> b8
        58.toByte() -> c8
        59.toByte() -> d8
        60.toByte() -> e8
        61.toByte() -> f8
        62.toByte() -> g8
        63.toByte() -> h8
        else -> throw IllegalArgumentException("Invalid square $byte")
    }

    operator fun get(idx: Int): ULong = when (idx) {
        0 -> a1
        1 -> b1
        2 -> c1
        3 -> d1
        4 -> e1
        5 -> f1
        6 -> g1
        7 -> h1
        8 -> a2
        9 -> b2
        10 -> c2
        11 -> d2
        12 -> e2
        13 -> f2
        14 -> g2
        15 -> h2
        16 -> a3
        17 -> b3
        18 -> c3
        19 -> d3
        20 -> e3
        21 -> f3
        22 -> g3
        23 -> h3
        24 -> a4
        25 -> b4
        26 -> c4
        27 -> d4
        28 -> e4
        29 -> f4
        30 -> g4
        31 -> h4
        32 -> a5
        33 -> b5
        34 -> c5
        35 -> d5
        36 -> e5
        37 -> f5
        38 -> g5
        39 -> h5
        40 -> a6
        41 -> b6
        42 -> c6
        43 -> d6
        44 -> e6
        45 -> f6
        46 -> g6
        47 -> h6
        48 -> a7
        49 -> b7
        50 -> c7
        51 -> d7
        52 -> e7
        53 -> f7
        54 -> g7
        55 -> h7
        56 -> a8
        57 -> b8
        58 -> c8
        59 -> d8
        60 -> e8
        61 -> f8
        62 -> g8
        63 -> h8
        else -> throw IllegalArgumentException("Invalid square index: $idx")
    }

    operator fun get(s: String): ULong = when (s) {
        "a1" -> a1
        "b1" -> b1
        "c1" -> c1
        "d1" -> d1
        "e1" -> e1
        "f1" -> f1
        "g1" -> g1
        "h1" -> h1
        "a2" -> a2
        "b2" -> b2
        "c2" -> c2
        "d2" -> d2
        "e2" -> e2
        "f2" -> f2
        "g2" -> g2
        "h2" -> h2
        "a3" -> a3
        "b3" -> b3
        "c3" -> c3
        "d3" -> d3
        "e3" -> e3
        "f3" -> f3
        "g3" -> g3
        "h3" -> h3
        "a4" -> a4
        "b4" -> b4
        "c4" -> c4
        "d4" -> d4
        "e4" -> e4
        "f4" -> f4
        "g4" -> g4
        "h4" -> h4
        "a5" -> a5
        "b5" -> b5
        "c5" -> c5
        "d5" -> d5
        "e5" -> e5
        "f5" -> f5
        "g5" -> g5
        "h5" -> h5
        "a6" -> a6
        "b6" -> b6
        "c6" -> c6
        "d6" -> d6
        "e6" -> e6
        "f6" -> f6
        "g6" -> g6
        "h6" -> h6
        "a7" -> a7
        "b7" -> b7
        "c7" -> c7
        "d7" -> d7
        "e7" -> e7
        "f7" -> f7
        "g7" -> g7
        "h7" -> h7
        "a8" -> a8
        "b8" -> b8
        "c8" -> c8
        "d8" -> d8
        "e8" -> e8
        "f8" -> f8
        "g8" -> g8
        "h8" -> h8
        else -> throw IllegalArgumentException("Invalid square: $s")
    }

    operator fun get(square: Square): ULong = when (square) {
        Square.a1 -> a1
        Square.a2 -> a2
        Square.a3 -> a3
        Square.a4 -> a4
        Square.a5 -> a5
        Square.a6 -> a6
        Square.a7 -> a7
        Square.a8 -> a8
        Square.b1 -> b1
        Square.b2 -> b2
        Square.b3 -> b3
        Square.b4 -> b4
        Square.b5 -> b5
        Square.b6 -> b6
        Square.b7 -> b7
        Square.b8 -> b8
        Square.c1 -> c1
        Square.c2 -> c2
        Square.c3 -> c3
        Square.c4 -> c4
        Square.c5 -> c5
        Square.c6 -> c6
        Square.c7 -> c7
        Square.c8 -> c8
        Square.d1 -> d1
        Square.d2 -> d2
        Square.d3 -> d3
        Square.d4 -> d4
        Square.d5 -> d5
        Square.d6 -> d6
        Square.d7 -> d7
        Square.d8 -> d8
        Square.e1 -> e1
        Square.e2 -> e2
        Square.e3 -> e3
        Square.e4 -> e4
        Square.e5 -> e5
        Square.e6 -> e6
        Square.e7 -> e7
        Square.e8 -> e8
        Square.f1 -> f1
        Square.f2 -> f2
        Square.f3 -> f3
        Square.f4 -> f4
        Square.f5 -> f5
        Square.f6 -> f6
        Square.f7 -> f7
        Square.f8 -> f8
        Square.g1 -> g1
        Square.g2 -> g2
        Square.g3 -> g3
        Square.g4 -> g4
        Square.g5 -> g5
        Square.g6 -> g6
        Square.g7 -> g7
        Square.g8 -> g8
        Square.h1 -> h1
        Square.h2 -> h2
        Square.h3 -> h3
        Square.h4 -> h4
        Square.h5 -> h5
        Square.h6 -> h6
        Square.h7 -> h7
        Square.h8 -> h8
    }

    const val a1 = 0x1UL
    const val b1 = 0x2UL
    const val c1 = 0x4UL
    const val d1 = 0x8UL
    const val e1 = 0x10UL
    const val f1 = 0x20UL
    const val g1 = 0x40UL
    const val h1 = 0x80UL
    const val a2 = 0x100UL
    const val b2 = 0x200UL
    const val c2 = 0x400UL
    const val d2 = 0x800UL
    const val e2 = 0x1000UL
    const val f2 = 0x2000UL
    const val g2 = 0x4000UL
    const val h2 = 0x8000UL
    const val a3 = 0x10000UL
    const val b3 = 0x20000UL
    const val c3 = 0x40000UL
    const val d3 = 0x80000UL
    const val e3 = 0x100000UL
    const val f3 = 0x200000UL
    const val g3 = 0x400000UL
    const val h3 = 0x800000UL
    const val a4 = 0x1000000UL
    const val b4 = 0x2000000UL
    const val c4 = 0x4000000UL
    const val d4 = 0x8000000UL
    const val e4 = 0x10000000UL
    const val f4 = 0x20000000UL
    const val g4 = 0x40000000UL
    const val h4 = 0x80000000UL
    const val a5 = 0x100000000UL
    const val b5 = 0x200000000UL
    const val c5 = 0x400000000UL
    const val d5 = 0x800000000UL
    const val e5 = 0x1000000000UL
    const val f5 = 0x2000000000UL
    const val g5 = 0x4000000000UL
    const val h5 = 0x8000000000UL
    const val a6 = 0x10000000000UL
    const val b6 = 0x20000000000UL
    const val c6 = 0x40000000000UL
    const val d6 = 0x80000000000UL
    const val e6 = 0x100000000000UL
    const val f6 = 0x200000000000UL
    const val g6 = 0x400000000000UL
    const val h6 = 0x800000000000UL
    const val a7 = 0x1000000000000UL
    const val b7 = 0x2000000000000UL
    const val c7 = 0x4000000000000UL
    const val d7 = 0x8000000000000UL
    const val e7 = 0x10000000000000UL
    const val f7 = 0x20000000000000UL
    const val g7 = 0x40000000000000UL
    const val h7 = 0x80000000000000UL
    const val a8 = 0x100000000000000UL
    const val b8 = 0x200000000000000UL
    const val c8 = 0x400000000000000UL
    const val d8 = 0x800000000000000UL
    const val e8 = 0x1000000000000000UL
    const val f8 = 0x2000000000000000UL
    const val g8 = 0x4000000000000000UL
    const val h8 = 0x8000000000000000UL
}