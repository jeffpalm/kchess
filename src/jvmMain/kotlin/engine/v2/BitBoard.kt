package engine.v2

class BitBoard {
    var _P: ULong = Constants.StartPosition.P
    var _N: ULong = Constants.StartPosition.N
    var _B: ULong = Constants.StartPosition.B
    var _R: ULong = Constants.StartPosition.R
    var _Q: ULong = Constants.StartPosition.Q
    var _K: ULong = Constants.StartPosition.K
    var _p: ULong = Constants.StartPosition.p
    var _n: ULong = Constants.StartPosition.n
    var _b: ULong = Constants.StartPosition.b
    var _r: ULong = Constants.StartPosition.r
    var _q: ULong = Constants.StartPosition.q
    var _k: ULong = Constants.StartPosition.k

    fun occupied(color: PieceColor? = null): ULong {
        return when (color) {
            PieceColor.WHITE -> _P or _N or _B or _R or _Q or _K
            PieceColor.BLACK -> _p or _n or _b or _r or _q or _k
            else -> _P or _N or _B or _R or _Q or _K or _p or _n or _b or _r or _q or _k
        }
    }

    fun empty(): ULong {
        return 0xffffffffffffffffUL xor occupied()
    }

    fun flipVertical(_x: ULong): ULong {
        var x = _x
        val k1 = 0x00FF00FF00FF00FFUL
        val k2 = 0x0000FFFF0000FFFFUL

        x = x.shr(8).and(k1).or(x.and(k1).shl(8))
        x = x.shr(16).and(k2).or(x.and(k2).shl(16))
        x = x.shr(32).or(x.shl(32))

        return x
    }

    fun mirrorHorizontal(_x: ULong): ULong {
        var x = _x
        val k1 = 0x5555555555555555UL
        val k2 = 0x3333333333333333UL
        val k4 = 0X0F0F0F0F0F0F0F0FUL

        x = x.shr(1).and(k1).plus(x.and(k1).times(2.toULong()))
        x = x.shr(2).and(k2).plus(x.and(k2).times(4.toULong()))
        x = x.shr(4).and(k4).plus(x.and(k4).times(16.toULong()))

        return x
    }

    fun flipDiagA1H8(_x: ULong): ULong {
        var x = _x
        var t: ULong
        val k1 = 0X5500550055005500UL
        val k2 = 0X3333000033330000UL
        val k4 = 0X0F0F0F0F00000000UL

        t = k4.and(x.xor(x.shl(28)))
        x = x.xor(t.xor(t.shr(28)))
        t = k2.and(x.xor(x.shl(14)))
        x = x.xor(t.xor(t.shr(14)))
        t = k1.and(x.xor(x.shl(7)))
        x = x.xor(t.xor(t.shr(7)))

        return x
    }

    fun flipDiagA8H1(_x: ULong): ULong {
        var x = _x
        var t: ULong
        val k1 = 0XAA00AA00AA00AA00UL
        val k2 = 0XCCCC0000CCCC0000UL
        val k4 = 0XF0F0F0F00F0F0F0FUL

        t = x.xor(x.shl(36))
        x = x.xor(k4.and(t.xor(x.shr(36))))
        t = k2.and(x.xor(x.shl(18)))
        x = x.xor(t.xor(t.shr(18)))
        t = k1.and(x.xor(x.shl(9)))
        x = x.xor(t.xor(t.shr(9)))

        return x
    }

    fun rotate180(x: ULong): ULong {
        return mirrorHorizontal(flipVertical(x))
    }

    fun rotate90(x: ULong, clockWise: Boolean = true): ULong {
        return if (clockWise) flipVertical(flipDiagA1H8(x)) else flipDiagA1H8(flipVertical(x))
    }
}