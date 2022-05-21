package engine.v2.adapters

import engine.v2.Sets

object Magic {
    private val noSoRanks = listOf(
        Sets.RANK8,
        Sets.RANK7,
        Sets.RANK6,
        Sets.RANK5
    )
    private val soNoRanks = listOf(
        Sets.RANK1,
        Sets.RANK2,
        Sets.RANK3,
        Sets.RANK4
    )
    private val weEaFiles = listOf(
        Sets.A_FILE,
        Sets.B_FILE,
        Sets.C_FILE,
        Sets.D_FILE
    )
    private val eaWeFiles = listOf(
        Sets.H_FILE,
        Sets.G_FILE,
        Sets.F_FILE,
        Sets.E_FILE
    )
    fun surround(word: ULong): ULong {
        var output: ULong = 0UL
        var i = 0
        while(output or word != output) {
            output = output or noSoRanks[i] or soNoRanks[i] or weEaFiles[i] or eaWeFiles[i]
            i++
        }
        return output
    }

    fun border(word: ULong): ULong {
        var output: ULong = 0UL
        var i = 0
        var layer: ULong = noSoRanks[i] or soNoRanks[i] or weEaFiles[i] or eaWeFiles[i]
        do {
            output = output or layer
            i++
            layer = noSoRanks[i] or soNoRanks[i] or weEaFiles[i] or eaWeFiles[i]
        } while (layer or word != layer)
        return output
    }
}