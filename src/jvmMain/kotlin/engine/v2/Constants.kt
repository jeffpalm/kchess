package engine.v2

object Constants {
    val fenPieces = listOf('P', 'p', 'N', 'n', 'B', 'b', 'R', 'r', 'Q', 'q', 'K', 'k')

    object StartPosition {
        const val P = 0xff00UL
        const val R = 0x81UL
        const val N = 0x42UL
        const val B = 0x24UL
        const val Q = 0x8UL
        const val K = 0x10UL

        // Black Pieces
        const val p = 0xff000000000000UL
        const val r = 0x8100000000000000UL
        const val n = 0x4200000000000000UL
        const val b = 0x2400000000000000UL
        const val q = 0x800000000000000UL
        const val k = 0x1000000000000000UL
    }

    val boardRep = mapOf<Byte, Char?>(
        0.toByte() to null,
        1.toByte() to null,
        2.toByte() to null,
        3.toByte() to null,
        4.toByte() to null,
        5.toByte() to null,
        6.toByte() to null,
        7.toByte() to null,
        8.toByte() to null,
        9.toByte() to null,
        10.toByte() to null,
        11.toByte() to null,
        12.toByte() to null,
        13.toByte() to null,
        14.toByte() to null,
        15.toByte() to null,
        16.toByte() to null,
        17.toByte() to null,
        18.toByte() to null,
        19.toByte() to null,
        20.toByte() to null,
        21.toByte() to null,
        22.toByte() to null,
        23.toByte() to null,
        24.toByte() to null,
        25.toByte() to null,
        26.toByte() to null,
        27.toByte() to null,
        28.toByte() to null,
        29.toByte() to null,
        30.toByte() to null,
        31.toByte() to null,
        32.toByte() to null,
        33.toByte() to null,
        34.toByte() to null,
        35.toByte() to null,
        36.toByte() to null,
        37.toByte() to null,
        38.toByte() to null,
        39.toByte() to null,
        40.toByte() to null,
        41.toByte() to null,
        42.toByte() to null,
        43.toByte() to null,
        44.toByte() to null,
        45.toByte() to null,
        46.toByte() to null,
        47.toByte() to null,
        48.toByte() to null,
        49.toByte() to null,
        50.toByte() to null,
        51.toByte() to null,
        52.toByte() to null,
        53.toByte() to null,
        54.toByte() to null,
        55.toByte() to null,
        56.toByte() to null,
        57.toByte() to null,
        58.toByte() to null,
        59.toByte() to null,
        60.toByte() to null,
        61.toByte() to null,
        62.toByte() to null,
        63.toByte() to null
    )

    object Square {
        operator fun get(word: ULong): String {
            return when (word) {
                0x1UL -> "a1"
                0x2UL -> "b1"
                0x4UL -> "c1"
                0x8UL -> "d1"
                0x10UL -> "e1"
                0x20UL -> "f1"
                0x40UL -> "g1"
                0x80UL -> "h1"
                0x100UL -> "a2"
                0x200UL -> "b2"
                0x400UL -> "c2"
                0x800UL -> "d2"
                0x1000UL -> "e2"
                0x2000UL -> "f2"
                0x4000UL -> "g2"
                0x8000UL -> "h2"
                0x10000UL -> "a3"
                0x20000UL -> "b3"
                0x40000UL -> "c3"
                0x80000UL -> "d3"
                0x100000UL -> "e3"
                0x200000UL -> "f3"
                0x400000UL -> "g3"
                0x800000UL -> "h3"
                0x1000000UL -> "a4"
                0x2000000UL -> "b4"
                0x4000000UL -> "c4"
                0x8000000UL -> "d4"
                0x10000000UL -> "e4"
                0x20000000UL -> "f4"
                0x40000000UL -> "g4"
                0x80000000UL -> "h4"
                0x100000000UL -> "a5"
                0x200000000UL -> "b5"
                0x400000000UL -> "c5"
                0x800000000UL -> "d5"
                0x1000000000UL -> "e5"
                0x2000000000UL -> "f5"
                0x4000000000UL -> "g5"
                0x8000000000UL -> "h5"
                0x10000000000UL -> "a6"
                0x20000000000UL -> "b6"
                0x40000000000UL -> "c6"
                0x80000000000UL -> "d6"
                0x100000000000UL -> "e6"
                0x200000000000UL -> "f6"
                0x400000000000UL -> "g6"
                0x800000000000UL -> "h6"
                0x1000000000000UL -> "a7"
                0x2000000000000UL -> "b7"
                0x4000000000000UL -> "c7"
                0x8000000000000UL -> "d7"
                0x10000000000000UL -> "e7"
                0x20000000000000UL -> "f7"
                0x40000000000000UL -> "g7"
                0x80000000000000UL -> "h7"
                0x100000000000000UL -> "a8"
                0x200000000000000UL -> "b8"
                0x400000000000000UL -> "c8"
                0x800000000000000UL -> "d8"
                0x1000000000000000UL -> "e8"
                0x2000000000000000UL -> "f8"
                0x4000000000000000UL -> "g8"
                0x8000000000000000UL -> "h8"
                else -> throw IllegalArgumentException("Invalid square")
            }
        }

        operator fun get(i: Int): ULong {
            return when (i) {
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
                else -> throw IllegalArgumentException("Invalid square index: $i")
            }
        }

        operator fun get(s: String): ULong {
            return when (s) {
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
}