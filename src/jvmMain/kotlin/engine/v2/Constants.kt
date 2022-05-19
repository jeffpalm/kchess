package engine.v2

object Constants {
    val fenPieces = listOf('P', 'p', 'N', 'n', 'B', 'b', 'R', 'r', 'Q', 'q', 'K', 'k')
    val whitePawns = listOf('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H')
    val blackPawns = listOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h')

    object StartPosition {
        // White Pieces
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

}