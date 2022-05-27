package engine.move

import engine.Direction
import engine.Piece
import engine.Square

object Magic {
    object Attack {
        operator fun get(square: Square, piece: Char): ULong = when (piece) {
            Piece.bKing, Piece.wKing -> King[square]
            Piece.bQueen, Piece.wQueen -> Queen[square]
            Piece.bRook, Piece.wRook -> Rook[square]
            Piece.bBishop, Piece.wBishop -> Bishop[square]
            Piece.bKnight, Piece.wKnight -> Knight[square]
            Piece.wPawn -> WhitePawn[square]
            Piece.bPawn -> BlackPawn[square]
            else -> throw IllegalArgumentException("Magic.get() does not support piece $piece")
        }

        object King {
            operator fun get(square: Square): ULong = when (square) {
                Square.a1 -> a1
                Square.b1 -> b1
                Square.c1 -> c1
                Square.d1 -> d1
                Square.e1 -> e1
                Square.f1 -> f1
                Square.g1 -> g1
                Square.h1 -> h1
                Square.a2 -> a2
                Square.b2 -> b2
                Square.c2 -> c2
                Square.d2 -> d2
                Square.e2 -> e2
                Square.f2 -> f2
                Square.g2 -> g2
                Square.h2 -> h2
                Square.a3 -> a3
                Square.b3 -> b3
                Square.c3 -> c3
                Square.d3 -> d3
                Square.e3 -> e3
                Square.f3 -> f3
                Square.g3 -> g3
                Square.h3 -> h3
                Square.a4 -> a4
                Square.b4 -> b4
                Square.c4 -> c4
                Square.d4 -> d4
                Square.e4 -> e4
                Square.f4 -> f4
                Square.g4 -> g4
                Square.h4 -> h4
                Square.a5 -> a5
                Square.b5 -> b5
                Square.c5 -> c5
                Square.d5 -> d5
                Square.e5 -> e5
                Square.f5 -> f5
                Square.g5 -> g5
                Square.h5 -> h5
                Square.a6 -> a6
                Square.b6 -> b6
                Square.c6 -> c6
                Square.d6 -> d6
                Square.e6 -> e6
                Square.f6 -> f6
                Square.g6 -> g6
                Square.h6 -> h6
                Square.a7 -> a7
                Square.b7 -> b7
                Square.c7 -> c7
                Square.d7 -> d7
                Square.e7 -> e7
                Square.f7 -> f7
                Square.g7 -> g7
                Square.h7 -> h7
                Square.a8 -> a8
                Square.b8 -> b8
                Square.c8 -> c8
                Square.d8 -> d8
                Square.e8 -> e8
                Square.f8 -> f8
                Square.g8 -> g8
                Square.h8 -> h8
            }

            const val a1 = 0x302UL
            const val b1 = 0x705UL
            const val c1 = 0xe0aUL
            const val d1 = 0x1c14UL
            const val e1 = 0x3828UL
            const val f1 = 0x7050UL
            const val g1 = 0xe0a0UL
            const val h1 = 0xc040UL
            const val a2 = 0x30203UL
            const val b2 = 0x70507UL
            const val c2 = 0xe0a0eUL
            const val d2 = 0x1c141cUL
            const val e2 = 0x382838UL
            const val f2 = 0x705070UL
            const val g2 = 0xe0a0e0UL
            const val h2 = 0xc040c0UL
            const val a3 = 0x3020300UL
            const val b3 = 0x7050700UL
            const val c3 = 0xe0a0e00UL
            const val d3 = 0x1c141c00UL
            const val e3 = 0x38283800UL
            const val f3 = 0x70507000UL
            const val g3 = 0xe0a0e000UL
            const val h3 = 0xc040c000UL
            const val a4 = 0x302030000UL
            const val b4 = 0x705070000UL
            const val c4 = 0xe0a0e0000UL
            const val d4 = 0x1c141c0000UL
            const val e4 = 0x3828380000UL
            const val f4 = 0x7050700000UL
            const val g4 = 0xe0a0e00000UL
            const val h4 = 0xc040c00000UL
            const val a5 = 0x30203000000UL
            const val b5 = 0x70507000000UL
            const val c5 = 0xe0a0e000000UL
            const val d5 = 0x1c141c000000UL
            const val e5 = 0x382838000000UL
            const val f5 = 0x705070000000UL
            const val g5 = 0xe0a0e0000000UL
            const val h5 = 0xc040c0000000UL
            const val a6 = 0x3020300000000UL
            const val b6 = 0x7050700000000UL
            const val c6 = 0xe0a0e00000000UL
            const val d6 = 0x1c141c00000000UL
            const val e6 = 0x38283800000000UL
            const val f6 = 0x70507000000000UL
            const val g6 = 0xe0a0e000000000UL
            const val h6 = 0xc040c000000000UL
            const val a7 = 0x302030000000000UL
            const val b7 = 0x705070000000000UL
            const val c7 = 0xe0a0e0000000000UL
            const val d7 = 0x1c141c0000000000UL
            const val e7 = 0x3828380000000000UL
            const val f7 = 0x7050700000000000UL
            const val g7 = 0xe0a0e00000000000UL
            const val h7 = 0xc040c00000000000UL
            const val a8 = 0x203000000000000UL
            const val b8 = 0x507000000000000UL
            const val c8 = 0xa0e000000000000UL
            const val d8 = 0x141c000000000000UL
            const val e8 = 0x2838000000000000UL
            const val f8 = 0x5070000000000000UL
            const val g8 = 0xa0e0000000000000UL
            const val h8 = 0x40c0000000000000UL
        }

        object Bishop {
            operator fun get(square: Square): ULong = when (square) {
                Square.a1 -> a1
                Square.b1 -> b1
                Square.c1 -> c1
                Square.d1 -> d1
                Square.e1 -> e1
                Square.f1 -> f1
                Square.g1 -> g1
                Square.h1 -> h1
                Square.a2 -> a2
                Square.b2 -> b2
                Square.c2 -> c2
                Square.d2 -> d2
                Square.e2 -> e2
                Square.f2 -> f2
                Square.g2 -> g2
                Square.h2 -> h2
                Square.a3 -> a3
                Square.b3 -> b3
                Square.c3 -> c3
                Square.d3 -> d3
                Square.e3 -> e3
                Square.f3 -> f3
                Square.g3 -> g3
                Square.h3 -> h3
                Square.a4 -> a4
                Square.b4 -> b4
                Square.c4 -> c4
                Square.d4 -> d4
                Square.e4 -> e4
                Square.f4 -> f4
                Square.g4 -> g4
                Square.h4 -> h4
                Square.a5 -> a5
                Square.b5 -> b5
                Square.c5 -> c5
                Square.d5 -> d5
                Square.e5 -> e5
                Square.f5 -> f5
                Square.g5 -> g5
                Square.h5 -> h5
                Square.a6 -> a6
                Square.b6 -> b6
                Square.c6 -> c6
                Square.d6 -> d6
                Square.e6 -> e6
                Square.f6 -> f6
                Square.g6 -> g6
                Square.h6 -> h6
                Square.a7 -> a7
                Square.b7 -> b7
                Square.c7 -> c7
                Square.d7 -> d7
                Square.e7 -> e7
                Square.f7 -> f7
                Square.g7 -> g7
                Square.h7 -> h7
                Square.a8 -> a8
                Square.b8 -> b8
                Square.c8 -> c8
                Square.d8 -> d8
                Square.e8 -> e8
                Square.f8 -> f8
                Square.g8 -> g8
                Square.h8 -> h8
            }


            const val a1 = 0x8040201008040200UL
            const val b1 = 0x80402010080500UL
            const val c1 = 0x804020110a00UL
            const val d1 = 0x8041221400UL
            const val e1 = 0x182442800UL
            const val f1 = 0x10204885000UL
            const val g1 = 0x102040810a000UL
            const val h1 = 0x102040810204000UL
            const val a2 = 0x4020100804020002UL
            const val b2 = 0x8040201008050005UL
            const val c2 = 0x804020110a000aUL
            const val d2 = 0x804122140014UL
            const val e2 = 0x18244280028UL
            const val f2 = 0x1020488500050UL
            const val g2 = 0x102040810a000a0UL
            const val h2 = 0x204081020400040UL
            const val a3 = 0x2010080402000204UL
            const val b3 = 0x4020100805000508UL
            const val c3 = 0x804020110a000a11UL
            const val d3 = 0x80412214001422UL
            const val e3 = 0x1824428002844UL
            const val f3 = 0x102048850005088UL
            const val g3 = 0x2040810a000a010UL
            const val h3 = 0x408102040004020UL
            const val a4 = 0x1008040200020408UL
            const val b4 = 0x2010080500050810UL
            const val c4 = 0x4020110a000a1120UL
            const val d4 = 0x8041221400142241UL
            const val e4 = 0x182442800284482UL
            const val f4 = 0x204885000508804UL
            const val g4 = 0x40810a000a01008UL
            const val h4 = 0x810204000402010UL
            const val a5 = 0x804020002040810UL
            const val b5 = 0x1008050005081020UL
            const val c5 = 0x20110a000a112040UL
            const val d5 = 0x4122140014224180UL
            const val e5 = 0x8244280028448201UL
            const val f5 = 0x488500050880402UL
            const val g5 = 0x810a000a0100804UL
            const val h5 = 0x1020400040201008UL
            const val a6 = 0x402000204081020UL
            const val b6 = 0x805000508102040UL
            const val c6 = 0x110a000a11204080UL
            const val d6 = 0x2214001422418000UL
            const val e6 = 0x4428002844820100UL
            const val f6 = 0x8850005088040201UL
            const val g6 = 0x10a000a010080402UL
            const val h6 = 0x2040004020100804UL
            const val a7 = 0x200020408102040UL
            const val b7 = 0x500050810204080UL
            const val c7 = 0xa000a1120408000UL
            const val d7 = 0x1400142241800000UL
            const val e7 = 0x2800284482010000UL
            const val f7 = 0x5000508804020100UL
            const val g7 = 0xa000a01008040201UL
            const val h7 = 0x4000402010080402UL
            const val a8 = 0x2040810204080UL
            const val b8 = 0x5081020408000UL
            const val c8 = 0xa112040800000UL
            const val d8 = 0x14224180000000UL
            const val e8 = 0x28448201000000UL
            const val f8 = 0x50880402010000UL
            const val g8 = 0xa0100804020100UL
            const val h8 = 0x40201008040201UL
        }

        object Rook {
            operator fun get(square: Square): ULong = when (square) {
                Square.a1 -> a1
                Square.b1 -> b1
                Square.c1 -> c1
                Square.d1 -> d1
                Square.e1 -> e1
                Square.f1 -> f1
                Square.g1 -> g1
                Square.h1 -> h1
                Square.a2 -> a2
                Square.b2 -> b2
                Square.c2 -> c2
                Square.d2 -> d2
                Square.e2 -> e2
                Square.f2 -> f2
                Square.g2 -> g2
                Square.h2 -> h2
                Square.a3 -> a3
                Square.b3 -> b3
                Square.c3 -> c3
                Square.d3 -> d3
                Square.e3 -> e3
                Square.f3 -> f3
                Square.g3 -> g3
                Square.h3 -> h3
                Square.a4 -> a4
                Square.b4 -> b4
                Square.c4 -> c4
                Square.d4 -> d4
                Square.e4 -> e4
                Square.f4 -> f4
                Square.g4 -> g4
                Square.h4 -> h4
                Square.a5 -> a5
                Square.b5 -> b5
                Square.c5 -> c5
                Square.d5 -> d5
                Square.e5 -> e5
                Square.f5 -> f5
                Square.g5 -> g5
                Square.h5 -> h5
                Square.a6 -> a6
                Square.b6 -> b6
                Square.c6 -> c6
                Square.d6 -> d6
                Square.e6 -> e6
                Square.f6 -> f6
                Square.g6 -> g6
                Square.h6 -> h6
                Square.a7 -> a7
                Square.b7 -> b7
                Square.c7 -> c7
                Square.d7 -> d7
                Square.e7 -> e7
                Square.f7 -> f7
                Square.g7 -> g7
                Square.h7 -> h7
                Square.a8 -> a8
                Square.b8 -> b8
                Square.c8 -> c8
                Square.d8 -> d8
                Square.e8 -> e8
                Square.f8 -> f8
                Square.g8 -> g8
                Square.h8 -> h8
            }

            const val a1 = 0x1010101010101feUL
            const val b1 = 0x2020202020202fdUL
            const val c1 = 0x4040404040404fbUL
            const val d1 = 0x8080808080808f7UL
            const val e1 = 0x10101010101010efUL
            const val f1 = 0x20202020202020dfUL
            const val g1 = 0x40404040404040bfUL
            const val h1 = 0x808080808080807fUL
            const val a2 = 0x10101010101fe01UL
            const val b2 = 0x20202020202fd02UL
            const val c2 = 0x40404040404fb04UL
            const val d2 = 0x80808080808f708UL
            const val e2 = 0x101010101010ef10UL
            const val f2 = 0x202020202020df20UL
            const val g2 = 0x404040404040bf40UL
            const val h2 = 0x8080808080807f80UL
            const val a3 = 0x101010101fe0101UL
            const val b3 = 0x202020202fd0202UL
            const val c3 = 0x404040404fb0404UL
            const val d3 = 0x808080808f70808UL
            const val e3 = 0x1010101010ef1010UL
            const val f3 = 0x2020202020df2020UL
            const val g3 = 0x4040404040bf4040UL
            const val h3 = 0x80808080807f8080UL
            const val a4 = 0x1010101fe010101UL
            const val b4 = 0x2020202fd020202UL
            const val c4 = 0x4040404fb040404UL
            const val d4 = 0x8080808f7080808UL
            const val e4 = 0x10101010ef101010UL
            const val f4 = 0x20202020df202020UL
            const val g4 = 0x40404040bf404040UL
            const val h4 = 0x808080807f808080UL
            const val a5 = 0x10101fe01010101UL
            const val b5 = 0x20202fd02020202UL
            const val c5 = 0x40404fb04040404UL
            const val d5 = 0x80808f708080808UL
            const val e5 = 0x101010ef10101010UL
            const val f5 = 0x202020df20202020UL
            const val g5 = 0x404040bf40404040UL
            const val h5 = 0x8080807f80808080UL
            const val a6 = 0x101fe0101010101UL
            const val b6 = 0x202fd0202020202UL
            const val c6 = 0x404fb0404040404UL
            const val d6 = 0x808f70808080808UL
            const val e6 = 0x1010ef1010101010UL
            const val f6 = 0x2020df2020202020UL
            const val g6 = 0x4040bf4040404040UL
            const val h6 = 0x80807f8080808080UL
            const val a7 = 0x1fe010101010101UL
            const val b7 = 0x2fd020202020202UL
            const val c7 = 0x4fb040404040404UL
            const val d7 = 0x8f7080808080808UL
            const val e7 = 0x10ef101010101010UL
            const val f7 = 0x20df202020202020UL
            const val g7 = 0x40bf404040404040UL
            const val h7 = 0x807f808080808080UL
            const val a8 = 0xfe01010101010101UL
            const val b8 = 0xfd02020202020202UL
            const val c8 = 0xfb04040404040404UL
            const val d8 = 0xf708080808080808UL
            const val e8 = 0xef10101010101010UL
            const val f8 = 0xdf20202020202020UL
            const val g8 = 0xbf40404040404040UL
            const val h8 = 0x7f80808080808080UL
        }

        object Queen {
            operator fun get(square: Square): ULong = when (square) {
                Square.a1 -> a1
                Square.b1 -> b1
                Square.c1 -> c1
                Square.d1 -> d1
                Square.e1 -> e1
                Square.f1 -> f1
                Square.g1 -> g1
                Square.h1 -> h1
                Square.a2 -> a2
                Square.b2 -> b2
                Square.c2 -> c2
                Square.d2 -> d2
                Square.e2 -> e2
                Square.f2 -> f2
                Square.g2 -> g2
                Square.h2 -> h2
                Square.a3 -> a3
                Square.b3 -> b3
                Square.c3 -> c3
                Square.d3 -> d3
                Square.e3 -> e3
                Square.f3 -> f3
                Square.g3 -> g3
                Square.h3 -> h3
                Square.a4 -> a4
                Square.b4 -> b4
                Square.c4 -> c4
                Square.d4 -> d4
                Square.e4 -> e4
                Square.f4 -> f4
                Square.g4 -> g4
                Square.h4 -> h4
                Square.a5 -> a5
                Square.b5 -> b5
                Square.c5 -> c5
                Square.d5 -> d5
                Square.e5 -> e5
                Square.f5 -> f5
                Square.g5 -> g5
                Square.h5 -> h5
                Square.a6 -> a6
                Square.b6 -> b6
                Square.c6 -> c6
                Square.d6 -> d6
                Square.e6 -> e6
                Square.f6 -> f6
                Square.g6 -> g6
                Square.h6 -> h6
                Square.a7 -> a7
                Square.b7 -> b7
                Square.c7 -> c7
                Square.d7 -> d7
                Square.e7 -> e7
                Square.f7 -> f7
                Square.g7 -> g7
                Square.h7 -> h7
                Square.a8 -> a8
                Square.b8 -> b8
                Square.c8 -> c8
                Square.d8 -> d8
                Square.e8 -> e8
                Square.f8 -> f8
                Square.g8 -> g8
                Square.h8 -> h8
            }

            const val a1 = 0x81412111090503feUL
            const val b1 = 0x2824222120a07fdUL
            const val c1 = 0x404844424150efbUL
            const val d1 = 0x8080888492a1cf7UL
            const val e1 = 0x10101011925438efUL
            const val f1 = 0x2020212224a870dfUL
            const val g1 = 0x404142444850e0bfUL
            const val h1 = 0x8182848890a0c07fUL
            const val a2 = 0x412111090503fe03UL
            const val b2 = 0x824222120a07fd07UL
            const val c2 = 0x4844424150efb0eUL
            const val d2 = 0x80888492a1cf71cUL
            const val e2 = 0x101011925438ef38UL
            const val f2 = 0x20212224a870df70UL
            const val g2 = 0x4142444850e0bfe0UL
            const val h2 = 0x82848890a0c07fc0UL
            const val a3 = 0x2111090503fe0305UL
            const val b3 = 0x4222120a07fd070aUL
            const val c3 = 0x844424150efb0e15UL
            const val d3 = 0x888492a1cf71c2aUL
            const val e3 = 0x1011925438ef3854UL
            const val f3 = 0x212224a870df70a8UL
            const val g3 = 0x42444850e0bfe050UL
            const val h3 = 0x848890a0c07fc0a0UL
            const val a4 = 0x11090503fe030509UL
            const val b4 = 0x22120a07fd070a12UL
            const val c4 = 0x4424150efb0e1524UL
            const val d4 = 0x88492a1cf71c2a49UL
            const val e4 = 0x11925438ef385492UL
            const val f4 = 0x2224a870df70a824UL
            const val g4 = 0x444850e0bfe05048UL
            const val h4 = 0x8890a0c07fc0a090UL
            const val a5 = 0x90503fe03050911UL
            const val b5 = 0x120a07fd070a1222UL
            const val c5 = 0x24150efb0e152444UL
            const val d5 = 0x492a1cf71c2a4988UL
            const val e5 = 0x925438ef38549211UL
            const val f5 = 0x24a870df70a82422UL
            const val g5 = 0x4850e0bfe0504844UL
            const val h5 = 0x90a0c07fc0a09088UL
            const val a6 = 0x503fe0305091121UL
            const val b6 = 0xa07fd070a122242UL
            const val c6 = 0x150efb0e15244484UL
            const val d6 = 0x2a1cf71c2a498808UL
            const val e6 = 0x5438ef3854921110UL
            const val f6 = 0xa870df70a8242221UL
            const val g6 = 0x50e0bfe050484442UL
            const val h6 = 0xa0c07fc0a0908884UL
            const val a7 = 0x3fe030509112141UL
            const val b7 = 0x7fd070a12224282UL
            const val c7 = 0xefb0e1524448404UL
            const val d7 = 0x1cf71c2a49880808UL
            const val e7 = 0x38ef385492111010UL
            const val f7 = 0x70df70a824222120UL
            const val g7 = 0xe0bfe05048444241UL
            const val h7 = 0xc07fc0a090888482UL
            const val a8 = 0xfe03050911214181UL
            const val b8 = 0xfd070a1222428202UL
            const val c8 = 0xfb0e152444840404UL
            const val d8 = 0xf71c2a4988080808UL
            const val e8 = 0xef38549211101010UL
            const val f8 = 0xdf70a82422212020UL
            const val g8 = 0xbfe0504844424140UL
            const val h8 = 0x7fc0a09088848281UL
        }

        object Knight {
            operator fun get(square: Square): ULong = when (square) {
                Square.a1 -> a1
                Square.b1 -> b1
                Square.c1 -> c1
                Square.d1 -> d1
                Square.e1 -> e1
                Square.f1 -> f1
                Square.g1 -> g1
                Square.h1 -> h1
                Square.a2 -> a2
                Square.b2 -> b2
                Square.c2 -> c2
                Square.d2 -> d2
                Square.e2 -> e2
                Square.f2 -> f2
                Square.g2 -> g2
                Square.h2 -> h2
                Square.a3 -> a3
                Square.b3 -> b3
                Square.c3 -> c3
                Square.d3 -> d3
                Square.e3 -> e3
                Square.f3 -> f3
                Square.g3 -> g3
                Square.h3 -> h3
                Square.a4 -> a4
                Square.b4 -> b4
                Square.c4 -> c4
                Square.d4 -> d4
                Square.e4 -> e4
                Square.f4 -> f4
                Square.g4 -> g4
                Square.h4 -> h4
                Square.a5 -> a5
                Square.b5 -> b5
                Square.c5 -> c5
                Square.d5 -> d5
                Square.e5 -> e5
                Square.f5 -> f5
                Square.g5 -> g5
                Square.h5 -> h5
                Square.a6 -> a6
                Square.b6 -> b6
                Square.c6 -> c6
                Square.d6 -> d6
                Square.e6 -> e6
                Square.f6 -> f6
                Square.g6 -> g6
                Square.h6 -> h6
                Square.a7 -> a7
                Square.b7 -> b7
                Square.c7 -> c7
                Square.d7 -> d7
                Square.e7 -> e7
                Square.f7 -> f7
                Square.g7 -> g7
                Square.h7 -> h7
                Square.a8 -> a8
                Square.b8 -> b8
                Square.c8 -> c8
                Square.d8 -> d8
                Square.e8 -> e8
                Square.f8 -> f8
                Square.g8 -> g8
                Square.h8 -> h8
            }

            const val a1 = 0x20400UL
            const val b1 = 0x50800UL
            const val c1 = 0xa1100UL
            const val d1 = 0x142200UL
            const val e1 = 0x284400UL
            const val f1 = 0x508800UL
            const val g1 = 0xa01000UL
            const val h1 = 0x402000UL
            const val a2 = 0x2040004UL
            const val b2 = 0x5080008UL
            const val c2 = 0xa110011UL
            const val d2 = 0x14220022UL
            const val e2 = 0x28440044UL
            const val f2 = 0x50880088UL
            const val g2 = 0xa0100010UL
            const val h2 = 0x40200020UL
            const val a3 = 0x204000402UL
            const val b3 = 0x508000805UL
            const val c3 = 0xa1100110aUL
            const val d3 = 0x1422002214UL
            const val e3 = 0x2844004428UL
            const val f3 = 0x5088008850UL
            const val g3 = 0xa0100010a0UL
            const val h3 = 0x4020002040UL
            const val a4 = 0x20400040200UL
            const val b4 = 0x50800080500UL
            const val c4 = 0xa1100110a00UL
            const val d4 = 0x142200221400UL
            const val e4 = 0x284400442800UL
            const val f4 = 0x508800885000UL
            const val g4 = 0xa0100010a000UL
            const val h4 = 0x402000204000UL
            const val a5 = 0x2040004020000UL
            const val b5 = 0x5080008050000UL
            const val c5 = 0xa1100110a0000UL
            const val d5 = 0x14220022140000UL
            const val e5 = 0x28440044280000UL
            const val f5 = 0x50880088500000UL
            const val g5 = 0xa0100010a00000UL
            const val h5 = 0x40200020400000UL
            const val a6 = 0x204000402000000UL
            const val b6 = 0x508000805000000UL
            const val c6 = 0xa1100110a000000UL
            const val d6 = 0x1422002214000000UL
            const val e6 = 0x2844004428000000UL
            const val f6 = 0x5088008850000000UL
            const val g6 = 0xa0100010a0000000UL
            const val h6 = 0x4020002040000000UL
            const val a7 = 0x400040200000000UL
            const val b7 = 0x800080500000000UL
            const val c7 = 0x1100110a00000000UL
            const val d7 = 0x2200221400000000UL
            const val e7 = 0x4400442800000000UL
            const val f7 = 0x8800885000000000UL
            const val g7 = 0x100010a000000000UL
            const val h7 = 0x2000204000000000UL
            const val a8 = 0x4020000000000UL
            const val b8 = 0x8050000000000UL
            const val c8 = 0x110a0000000000UL
            const val d8 = 0x22140000000000UL
            const val e8 = 0x44280000000000UL
            const val f8 = 0x88500000000000UL
            const val g8 = 0x10a00000000000UL
            const val h8 = 0x20400000000000UL
        }

        object WhitePawn {
            operator fun get(square: Square): ULong = when (square) {
                Square.a1 -> a1
                Square.b1 -> b1
                Square.c1 -> c1
                Square.d1 -> d1
                Square.e1 -> e1
                Square.f1 -> f1
                Square.g1 -> g1
                Square.h1 -> h1
                Square.a2 -> a2
                Square.b2 -> b2
                Square.c2 -> c2
                Square.d2 -> d2
                Square.e2 -> e2
                Square.f2 -> f2
                Square.g2 -> g2
                Square.h2 -> h2
                Square.a3 -> a3
                Square.b3 -> b3
                Square.c3 -> c3
                Square.d3 -> d3
                Square.e3 -> e3
                Square.f3 -> f3
                Square.g3 -> g3
                Square.h3 -> h3
                Square.a4 -> a4
                Square.b4 -> b4
                Square.c4 -> c4
                Square.d4 -> d4
                Square.e4 -> e4
                Square.f4 -> f4
                Square.g4 -> g4
                Square.h4 -> h4
                Square.a5 -> a5
                Square.b5 -> b5
                Square.c5 -> c5
                Square.d5 -> d5
                Square.e5 -> e5
                Square.f5 -> f5
                Square.g5 -> g5
                Square.h5 -> h5
                Square.a6 -> a6
                Square.b6 -> b6
                Square.c6 -> c6
                Square.d6 -> d6
                Square.e6 -> e6
                Square.f6 -> f6
                Square.g6 -> g6
                Square.h6 -> h6
                Square.a7 -> a7
                Square.b7 -> b7
                Square.c7 -> c7
                Square.d7 -> d7
                Square.e7 -> e7
                Square.f7 -> f7
                Square.g7 -> g7
                Square.h7 -> h7
                Square.a8 -> a8
                Square.b8 -> b8
                Square.c8 -> c8
                Square.d8 -> d8
                Square.e8 -> e8
                Square.f8 -> f8
                Square.g8 -> g8
                Square.h8 -> h8
            }


            const val a1 = 0x200UL
            const val b1 = 0x500UL
            const val c1 = 0xa00UL
            const val d1 = 0x1400UL
            const val e1 = 0x2800UL
            const val f1 = 0x5000UL
            const val g1 = 0xa000UL
            const val h1 = 0x4000UL
            const val a2 = 0x20000UL
            const val b2 = 0x50000UL
            const val c2 = 0xa0000UL
            const val d2 = 0x140000UL
            const val e2 = 0x280000UL
            const val f2 = 0x500000UL
            const val g2 = 0xa00000UL
            const val h2 = 0x400000UL
            const val a3 = 0x2000000UL
            const val b3 = 0x5000000UL
            const val c3 = 0xa000000UL
            const val d3 = 0x14000000UL
            const val e3 = 0x28000000UL
            const val f3 = 0x50000000UL
            const val g3 = 0xa0000000UL
            const val h3 = 0x40000000UL
            const val a4 = 0x200000000UL
            const val b4 = 0x500000000UL
            const val c4 = 0xa00000000UL
            const val d4 = 0x1400000000UL
            const val e4 = 0x2800000000UL
            const val f4 = 0x5000000000UL
            const val g4 = 0xa000000000UL
            const val h4 = 0x4000000000UL
            const val a5 = 0x20000000000UL
            const val b5 = 0x50000000000UL
            const val c5 = 0xa0000000000UL
            const val d5 = 0x140000000000UL
            const val e5 = 0x280000000000UL
            const val f5 = 0x500000000000UL
            const val g5 = 0xa00000000000UL
            const val h5 = 0x400000000000UL
            const val a6 = 0x2000000000000UL
            const val b6 = 0x5000000000000UL
            const val c6 = 0xa000000000000UL
            const val d6 = 0x14000000000000UL
            const val e6 = 0x28000000000000UL
            const val f6 = 0x50000000000000UL
            const val g6 = 0xa0000000000000UL
            const val h6 = 0x40000000000000UL
            const val a7 = 0x200000000000000UL
            const val b7 = 0x500000000000000UL
            const val c7 = 0xa00000000000000UL
            const val d7 = 0x1400000000000000UL
            const val e7 = 0x2800000000000000UL
            const val f7 = 0x5000000000000000UL
            const val g7 = 0xa000000000000000UL
            const val h7 = 0x4000000000000000UL
            const val a8 = 0x0UL
            const val b8 = 0x0UL
            const val c8 = 0x0UL
            const val d8 = 0x0UL
            const val e8 = 0x0UL
            const val f8 = 0x0UL
            const val g8 = 0x0UL
            const val h8 = 0x0UL
        }

        object BlackPawn {
            operator fun get(square: Square): ULong = when (square) {
                Square.a1 -> a1
                Square.b1 -> b1
                Square.c1 -> c1
                Square.d1 -> d1
                Square.e1 -> e1
                Square.f1 -> f1
                Square.g1 -> g1
                Square.h1 -> h1
                Square.a2 -> a2
                Square.b2 -> b2
                Square.c2 -> c2
                Square.d2 -> d2
                Square.e2 -> e2
                Square.f2 -> f2
                Square.g2 -> g2
                Square.h2 -> h2
                Square.a3 -> a3
                Square.b3 -> b3
                Square.c3 -> c3
                Square.d3 -> d3
                Square.e3 -> e3
                Square.f3 -> f3
                Square.g3 -> g3
                Square.h3 -> h3
                Square.a4 -> a4
                Square.b4 -> b4
                Square.c4 -> c4
                Square.d4 -> d4
                Square.e4 -> e4
                Square.f4 -> f4
                Square.g4 -> g4
                Square.h4 -> h4
                Square.a5 -> a5
                Square.b5 -> b5
                Square.c5 -> c5
                Square.d5 -> d5
                Square.e5 -> e5
                Square.f5 -> f5
                Square.g5 -> g5
                Square.h5 -> h5
                Square.a6 -> a6
                Square.b6 -> b6
                Square.c6 -> c6
                Square.d6 -> d6
                Square.e6 -> e6
                Square.f6 -> f6
                Square.g6 -> g6
                Square.h6 -> h6
                Square.a7 -> a7
                Square.b7 -> b7
                Square.c7 -> c7
                Square.d7 -> d7
                Square.e7 -> e7
                Square.f7 -> f7
                Square.g7 -> g7
                Square.h7 -> h7
                Square.a8 -> a8
                Square.b8 -> b8
                Square.c8 -> c8
                Square.d8 -> d8
                Square.e8 -> e8
                Square.f8 -> f8
                Square.g8 -> g8
                Square.h8 -> h8
            }

            const val a1 = 0x0UL
            const val b1 = 0x0UL
            const val c1 = 0x0UL
            const val d1 = 0x0UL
            const val e1 = 0x0UL
            const val f1 = 0x0UL
            const val g1 = 0x0UL
            const val h1 = 0x0UL
            const val a2 = 0x2UL
            const val b2 = 0x5UL
            const val c2 = 0xaUL
            const val d2 = 0x14UL
            const val e2 = 0x28UL
            const val f2 = 0x50UL
            const val g2 = 0xa0UL
            const val h2 = 0x40UL
            const val a3 = 0x200UL
            const val b3 = 0x500UL
            const val c3 = 0xa00UL
            const val d3 = 0x1400UL
            const val e3 = 0x2800UL
            const val f3 = 0x5000UL
            const val g3 = 0xa000UL
            const val h3 = 0x4000UL
            const val a4 = 0x20000UL
            const val b4 = 0x50000UL
            const val c4 = 0xa0000UL
            const val d4 = 0x140000UL
            const val e4 = 0x280000UL
            const val f4 = 0x500000UL
            const val g4 = 0xa00000UL
            const val h4 = 0x400000UL
            const val a5 = 0x2000000UL
            const val b5 = 0x5000000UL
            const val c5 = 0xa000000UL
            const val d5 = 0x14000000UL
            const val e5 = 0x28000000UL
            const val f5 = 0x50000000UL
            const val g5 = 0xa0000000UL
            const val h5 = 0x40000000UL
            const val a6 = 0x200000000UL
            const val b6 = 0x500000000UL
            const val c6 = 0xa00000000UL
            const val d6 = 0x1400000000UL
            const val e6 = 0x2800000000UL
            const val f6 = 0x5000000000UL
            const val g6 = 0xa000000000UL
            const val h6 = 0x4000000000UL
            const val a7 = 0x20000000000UL
            const val b7 = 0x50000000000UL
            const val c7 = 0xa0000000000UL
            const val d7 = 0x140000000000UL
            const val e7 = 0x280000000000UL
            const val f7 = 0x500000000000UL
            const val g7 = 0xa00000000000UL
            const val h7 = 0x400000000000UL
            const val a8 = 0x2000000000000UL
            const val b8 = 0x5000000000000UL
            const val c8 = 0xa000000000000UL
            const val d8 = 0x14000000000000UL
            const val e8 = 0x28000000000000UL
            const val f8 = 0x50000000000000UL
            const val g8 = 0xa0000000000000UL
            const val h8 = 0x40000000000000UL
        }
    }


    object Ray {
        operator fun get(square: Square, direction: Direction): ULong = when (direction) {
            Direction.N -> North[square]
            Direction.NE -> NorthEast[square]
            Direction.E -> East[square]
            Direction.SE -> SouthEast[square]
            Direction.S -> South[square]
            Direction.SW -> SouthWest[square]
            Direction.W -> West[square]
            Direction.NW -> NorthWest[square]
            else -> throw IllegalArgumentException("Invalid direction: $direction")
        }

        object North {
            operator fun get(square: Square): ULong = when (square) {
                Square.a1 -> a1
                Square.b1 -> b1
                Square.c1 -> c1
                Square.d1 -> d1
                Square.e1 -> e1
                Square.f1 -> f1
                Square.g1 -> g1
                Square.h1 -> h1
                Square.a2 -> a2
                Square.b2 -> b2
                Square.c2 -> c2
                Square.d2 -> d2
                Square.e2 -> e2
                Square.f2 -> f2
                Square.g2 -> g2
                Square.h2 -> h2
                Square.a3 -> a3
                Square.b3 -> b3
                Square.c3 -> c3
                Square.d3 -> d3
                Square.e3 -> e3
                Square.f3 -> f3
                Square.g3 -> g3
                Square.h3 -> h3
                Square.a4 -> a4
                Square.b4 -> b4
                Square.c4 -> c4
                Square.d4 -> d4
                Square.e4 -> e4
                Square.f4 -> f4
                Square.g4 -> g4
                Square.h4 -> h4
                Square.a5 -> a5
                Square.b5 -> b5
                Square.c5 -> c5
                Square.d5 -> d5
                Square.e5 -> e5
                Square.f5 -> f5
                Square.g5 -> g5
                Square.h5 -> h5
                Square.a6 -> a6
                Square.b6 -> b6
                Square.c6 -> c6
                Square.d6 -> d6
                Square.e6 -> e6
                Square.f6 -> f6
                Square.g6 -> g6
                Square.h6 -> h6
                Square.a7 -> a7
                Square.b7 -> b7
                Square.c7 -> c7
                Square.d7 -> d7
                Square.e7 -> e7
                Square.f7 -> f7
                Square.g7 -> g7
                Square.h7 -> h7
                Square.a8 -> a8
                Square.b8 -> b8
                Square.c8 -> c8
                Square.d8 -> d8
                Square.e8 -> e8
                Square.f8 -> f8
                Square.g8 -> g8
                Square.h8 -> h8
            }

            const val a1 = 0x101010101010100UL
            const val b1 = 0x202020202020200UL
            const val c1 = 0x404040404040400UL
            const val d1 = 0x808080808080800UL
            const val e1 = 0x1010101010101000UL
            const val f1 = 0x2020202020202000UL
            const val g1 = 0x4040404040404000UL
            const val h1 = 0x8080808080808000UL
            const val a2 = 0x101010101010000UL
            const val b2 = 0x202020202020000UL
            const val c2 = 0x404040404040000UL
            const val d2 = 0x808080808080000UL
            const val e2 = 0x1010101010100000UL
            const val f2 = 0x2020202020200000UL
            const val g2 = 0x4040404040400000UL
            const val h2 = 0x8080808080800000UL
            const val a3 = 0x101010101000000UL
            const val b3 = 0x202020202000000UL
            const val c3 = 0x404040404000000UL
            const val d3 = 0x808080808000000UL
            const val e3 = 0x1010101010000000UL
            const val f3 = 0x2020202020000000UL
            const val g3 = 0x4040404040000000UL
            const val h3 = 0x8080808080000000UL
            const val a4 = 0x101010100000000UL
            const val b4 = 0x202020200000000UL
            const val c4 = 0x404040400000000UL
            const val d4 = 0x808080800000000UL
            const val e4 = 0x1010101000000000UL
            const val f4 = 0x2020202000000000UL
            const val g4 = 0x4040404000000000UL
            const val h4 = 0x8080808000000000UL
            const val a5 = 0x101010000000000UL
            const val b5 = 0x202020000000000UL
            const val c5 = 0x404040000000000UL
            const val d5 = 0x808080000000000UL
            const val e5 = 0x1010100000000000UL
            const val f5 = 0x2020200000000000UL
            const val g5 = 0x4040400000000000UL
            const val h5 = 0x8080800000000000UL
            const val a6 = 0x101000000000000UL
            const val b6 = 0x202000000000000UL
            const val c6 = 0x404000000000000UL
            const val d6 = 0x808000000000000UL
            const val e6 = 0x1010000000000000UL
            const val f6 = 0x2020000000000000UL
            const val g6 = 0x4040000000000000UL
            const val h6 = 0x8080000000000000UL
            const val a7 = 0x100000000000000UL
            const val b7 = 0x200000000000000UL
            const val c7 = 0x400000000000000UL
            const val d7 = 0x800000000000000UL
            const val e7 = 0x1000000000000000UL
            const val f7 = 0x2000000000000000UL
            const val g7 = 0x4000000000000000UL
            const val h7 = 0x8000000000000000UL
            const val a8 = 0x0UL
            const val b8 = 0x0UL
            const val c8 = 0x0UL
            const val d8 = 0x0UL
            const val e8 = 0x0UL
            const val f8 = 0x0UL
            const val g8 = 0x0UL
            const val h8 = 0x0UL
        }

        object NorthEast {
            operator fun get(square: Square): ULong = when (square) {
                Square.a1 -> a1
                Square.b1 -> b1
                Square.c1 -> c1
                Square.d1 -> d1
                Square.e1 -> e1
                Square.f1 -> f1
                Square.g1 -> g1
                Square.h1 -> h1
                Square.a2 -> a2
                Square.b2 -> b2
                Square.c2 -> c2
                Square.d2 -> d2
                Square.e2 -> e2
                Square.f2 -> f2
                Square.g2 -> g2
                Square.h2 -> h2
                Square.a3 -> a3
                Square.b3 -> b3
                Square.c3 -> c3
                Square.d3 -> d3
                Square.e3 -> e3
                Square.f3 -> f3
                Square.g3 -> g3
                Square.h3 -> h3
                Square.a4 -> a4
                Square.b4 -> b4
                Square.c4 -> c4
                Square.d4 -> d4
                Square.e4 -> e4
                Square.f4 -> f4
                Square.g4 -> g4
                Square.h4 -> h4
                Square.a5 -> a5
                Square.b5 -> b5
                Square.c5 -> c5
                Square.d5 -> d5
                Square.e5 -> e5
                Square.f5 -> f5
                Square.g5 -> g5
                Square.h5 -> h5
                Square.a6 -> a6
                Square.b6 -> b6
                Square.c6 -> c6
                Square.d6 -> d6
                Square.e6 -> e6
                Square.f6 -> f6
                Square.g6 -> g6
                Square.h6 -> h6
                Square.a7 -> a7
                Square.b7 -> b7
                Square.c7 -> c7
                Square.d7 -> d7
                Square.e7 -> e7
                Square.f7 -> f7
                Square.g7 -> g7
                Square.h7 -> h7
                Square.a8 -> a8
                Square.b8 -> b8
                Square.c8 -> c8
                Square.d8 -> d8
                Square.e8 -> e8
                Square.f8 -> f8
                Square.g8 -> g8
                Square.h8 -> h8
            }

            const val a1 = 0x8040201008040200UL
            const val b1 = 0x80402010080400UL
            const val c1 = 0x804020100800UL
            const val d1 = 0x8040201000UL
            const val e1 = 0x80402000UL
            const val f1 = 0x804000UL
            const val g1 = 0x8000UL
            const val h1 = 0x0UL
            const val a2 = 0x4020100804020000UL
            const val b2 = 0x8040201008040000UL
            const val c2 = 0x80402010080000UL
            const val d2 = 0x804020100000UL
            const val e2 = 0x8040200000UL
            const val f2 = 0x80400000UL
            const val g2 = 0x800000UL
            const val h2 = 0x0UL
            const val a3 = 0x2010080402000000UL
            const val b3 = 0x4020100804000000UL
            const val c3 = 0x8040201008000000UL
            const val d3 = 0x80402010000000UL
            const val e3 = 0x804020000000UL
            const val f3 = 0x8040000000UL
            const val g3 = 0x80000000UL
            const val h3 = 0x0UL
            const val a4 = 0x1008040200000000UL
            const val b4 = 0x2010080400000000UL
            const val c4 = 0x4020100800000000UL
            const val d4 = 0x8040201000000000UL
            const val e4 = 0x80402000000000UL
            const val f4 = 0x804000000000UL
            const val g4 = 0x8000000000UL
            const val h4 = 0x0UL
            const val a5 = 0x804020000000000UL
            const val b5 = 0x1008040000000000UL
            const val c5 = 0x2010080000000000UL
            const val d5 = 0x4020100000000000UL
            const val e5 = 0x8040200000000000UL
            const val f5 = 0x80400000000000UL
            const val g5 = 0x800000000000UL
            const val h5 = 0x0UL
            const val a6 = 0x402000000000000UL
            const val b6 = 0x804000000000000UL
            const val c6 = 0x1008000000000000UL
            const val d6 = 0x2010000000000000UL
            const val e6 = 0x4020000000000000UL
            const val f6 = 0x8040000000000000UL
            const val g6 = 0x80000000000000UL
            const val h6 = 0x0UL
            const val a7 = 0x200000000000000UL
            const val b7 = 0x400000000000000UL
            const val c7 = 0x800000000000000UL
            const val d7 = 0x1000000000000000UL
            const val e7 = 0x2000000000000000UL
            const val f7 = 0x4000000000000000UL
            const val g7 = 0x8000000000000000UL
            const val h7 = 0x0UL
            const val a8 = 0x0UL
            const val b8 = 0x0UL
            const val c8 = 0x0UL
            const val d8 = 0x0UL
            const val e8 = 0x0UL
            const val f8 = 0x0UL
            const val g8 = 0x0UL
            const val h8 = 0x0UL
        }

        object East {
            operator fun get(square: Square): ULong = when (square) {
                Square.a1 -> a1
                Square.b1 -> b1
                Square.c1 -> c1
                Square.d1 -> d1
                Square.e1 -> e1
                Square.f1 -> f1
                Square.g1 -> g1
                Square.h1 -> h1
                Square.a2 -> a2
                Square.b2 -> b2
                Square.c2 -> c2
                Square.d2 -> d2
                Square.e2 -> e2
                Square.f2 -> f2
                Square.g2 -> g2
                Square.h2 -> h2
                Square.a3 -> a3
                Square.b3 -> b3
                Square.c3 -> c3
                Square.d3 -> d3
                Square.e3 -> e3
                Square.f3 -> f3
                Square.g3 -> g3
                Square.h3 -> h3
                Square.a4 -> a4
                Square.b4 -> b4
                Square.c4 -> c4
                Square.d4 -> d4
                Square.e4 -> e4
                Square.f4 -> f4
                Square.g4 -> g4
                Square.h4 -> h4
                Square.a5 -> a5
                Square.b5 -> b5
                Square.c5 -> c5
                Square.d5 -> d5
                Square.e5 -> e5
                Square.f5 -> f5
                Square.g5 -> g5
                Square.h5 -> h5
                Square.a6 -> a6
                Square.b6 -> b6
                Square.c6 -> c6
                Square.d6 -> d6
                Square.e6 -> e6
                Square.f6 -> f6
                Square.g6 -> g6
                Square.h6 -> h6
                Square.a7 -> a7
                Square.b7 -> b7
                Square.c7 -> c7
                Square.d7 -> d7
                Square.e7 -> e7
                Square.f7 -> f7
                Square.g7 -> g7
                Square.h7 -> h7
                Square.a8 -> a8
                Square.b8 -> b8
                Square.c8 -> c8
                Square.d8 -> d8
                Square.e8 -> e8
                Square.f8 -> f8
                Square.g8 -> g8
                Square.h8 -> h8
            }


            const val a1 = 0xfeUL
            const val b1 = 0xfcUL
            const val c1 = 0xf8UL
            const val d1 = 0xf0UL
            const val e1 = 0xe0UL
            const val f1 = 0xc0UL
            const val g1 = 0x80UL
            const val h1 = 0x0UL
            const val a2 = 0xfe00UL
            const val b2 = 0xfc00UL
            const val c2 = 0xf800UL
            const val d2 = 0xf000UL
            const val e2 = 0xe000UL
            const val f2 = 0xc000UL
            const val g2 = 0x8000UL
            const val h2 = 0x0UL
            const val a3 = 0xfe0000UL
            const val b3 = 0xfc0000UL
            const val c3 = 0xf80000UL
            const val d3 = 0xf00000UL
            const val e3 = 0xe00000UL
            const val f3 = 0xc00000UL
            const val g3 = 0x800000UL
            const val h3 = 0x0UL
            const val a4 = 0xfe000000UL
            const val b4 = 0xfc000000UL
            const val c4 = 0xf8000000UL
            const val d4 = 0xf0000000UL
            const val e4 = 0xe0000000UL
            const val f4 = 0xc0000000UL
            const val g4 = 0x80000000UL
            const val h4 = 0x0UL
            const val a5 = 0xfe00000000UL
            const val b5 = 0xfc00000000UL
            const val c5 = 0xf800000000UL
            const val d5 = 0xf000000000UL
            const val e5 = 0xe000000000UL
            const val f5 = 0xc000000000UL
            const val g5 = 0x8000000000UL
            const val h5 = 0x0UL
            const val a6 = 0xfe0000000000UL
            const val b6 = 0xfc0000000000UL
            const val c6 = 0xf80000000000UL
            const val d6 = 0xf00000000000UL
            const val e6 = 0xe00000000000UL
            const val f6 = 0xc00000000000UL
            const val g6 = 0x800000000000UL
            const val h6 = 0x0UL
            const val a7 = 0xfe000000000000UL
            const val b7 = 0xfc000000000000UL
            const val c7 = 0xf8000000000000UL
            const val d7 = 0xf0000000000000UL
            const val e7 = 0xe0000000000000UL
            const val f7 = 0xc0000000000000UL
            const val g7 = 0x80000000000000UL
            const val h7 = 0x0UL
            const val a8 = 0xfe00000000000000UL
            const val b8 = 0xfc00000000000000UL
            const val c8 = 0xf800000000000000UL
            const val d8 = 0xf000000000000000UL
            const val e8 = 0xe000000000000000UL
            const val f8 = 0xc000000000000000UL
            const val g8 = 0x8000000000000000UL
            const val h8 = 0x0UL
        }

        object SouthEast {
            operator fun get(square: Square): ULong = when (square) {
                Square.a1 -> a1
                Square.b1 -> b1
                Square.c1 -> c1
                Square.d1 -> d1
                Square.e1 -> e1
                Square.f1 -> f1
                Square.g1 -> g1
                Square.h1 -> h1
                Square.a2 -> a2
                Square.b2 -> b2
                Square.c2 -> c2
                Square.d2 -> d2
                Square.e2 -> e2
                Square.f2 -> f2
                Square.g2 -> g2
                Square.h2 -> h2
                Square.a3 -> a3
                Square.b3 -> b3
                Square.c3 -> c3
                Square.d3 -> d3
                Square.e3 -> e3
                Square.f3 -> f3
                Square.g3 -> g3
                Square.h3 -> h3
                Square.a4 -> a4
                Square.b4 -> b4
                Square.c4 -> c4
                Square.d4 -> d4
                Square.e4 -> e4
                Square.f4 -> f4
                Square.g4 -> g4
                Square.h4 -> h4
                Square.a5 -> a5
                Square.b5 -> b5
                Square.c5 -> c5
                Square.d5 -> d5
                Square.e5 -> e5
                Square.f5 -> f5
                Square.g5 -> g5
                Square.h5 -> h5
                Square.a6 -> a6
                Square.b6 -> b6
                Square.c6 -> c6
                Square.d6 -> d6
                Square.e6 -> e6
                Square.f6 -> f6
                Square.g6 -> g6
                Square.h6 -> h6
                Square.a7 -> a7
                Square.b7 -> b7
                Square.c7 -> c7
                Square.d7 -> d7
                Square.e7 -> e7
                Square.f7 -> f7
                Square.g7 -> g7
                Square.h7 -> h7
                Square.a8 -> a8
                Square.b8 -> b8
                Square.c8 -> c8
                Square.d8 -> d8
                Square.e8 -> e8
                Square.f8 -> f8
                Square.g8 -> g8
                Square.h8 -> h8
            }


            const val a1 = 0x0UL
            const val b1 = 0x0UL
            const val c1 = 0x0UL
            const val d1 = 0x0UL
            const val e1 = 0x0UL
            const val f1 = 0x0UL
            const val g1 = 0x0UL
            const val h1 = 0x0UL
            const val a2 = 0x2UL
            const val b2 = 0x4UL
            const val c2 = 0x8UL
            const val d2 = 0x10UL
            const val e2 = 0x20UL
            const val f2 = 0x40UL
            const val g2 = 0x80UL
            const val h2 = 0x0UL
            const val a3 = 0x204UL
            const val b3 = 0x408UL
            const val c3 = 0x810UL
            const val d3 = 0x1020UL
            const val e3 = 0x2040UL
            const val f3 = 0x4080UL
            const val g3 = 0x8000UL
            const val h3 = 0x0UL
            const val a4 = 0x20408UL
            const val b4 = 0x40810UL
            const val c4 = 0x81020UL
            const val d4 = 0x102040UL
            const val e4 = 0x204080UL
            const val f4 = 0x408000UL
            const val g4 = 0x800000UL
            const val h4 = 0x0UL
            const val a5 = 0x2040810UL
            const val b5 = 0x4081020UL
            const val c5 = 0x8102040UL
            const val d5 = 0x10204080UL
            const val e5 = 0x20408000UL
            const val f5 = 0x40800000UL
            const val g5 = 0x80000000UL
            const val h5 = 0x0UL
            const val a6 = 0x204081020UL
            const val b6 = 0x408102040UL
            const val c6 = 0x810204080UL
            const val d6 = 0x1020408000UL
            const val e6 = 0x2040800000UL
            const val f6 = 0x4080000000UL
            const val g6 = 0x8000000000UL
            const val h6 = 0x0UL
            const val a7 = 0x20408102040UL
            const val b7 = 0x40810204080UL
            const val c7 = 0x81020408000UL
            const val d7 = 0x102040800000UL
            const val e7 = 0x204080000000UL
            const val f7 = 0x408000000000UL
            const val g7 = 0x800000000000UL
            const val h7 = 0x0UL
            const val a8 = 0x2040810204080UL
            const val b8 = 0x4081020408000UL
            const val c8 = 0x8102040800000UL
            const val d8 = 0x10204080000000UL
            const val e8 = 0x20408000000000UL
            const val f8 = 0x40800000000000UL
            const val g8 = 0x80000000000000UL
            const val h8 = 0x0UL
        }

        object South {
            operator fun get(square: Square): ULong = when (square) {
                Square.a1 -> a1
                Square.b1 -> b1
                Square.c1 -> c1
                Square.d1 -> d1
                Square.e1 -> e1
                Square.f1 -> f1
                Square.g1 -> g1
                Square.h1 -> h1
                Square.a2 -> a2
                Square.b2 -> b2
                Square.c2 -> c2
                Square.d2 -> d2
                Square.e2 -> e2
                Square.f2 -> f2
                Square.g2 -> g2
                Square.h2 -> h2
                Square.a3 -> a3
                Square.b3 -> b3
                Square.c3 -> c3
                Square.d3 -> d3
                Square.e3 -> e3
                Square.f3 -> f3
                Square.g3 -> g3
                Square.h3 -> h3
                Square.a4 -> a4
                Square.b4 -> b4
                Square.c4 -> c4
                Square.d4 -> d4
                Square.e4 -> e4
                Square.f4 -> f4
                Square.g4 -> g4
                Square.h4 -> h4
                Square.a5 -> a5
                Square.b5 -> b5
                Square.c5 -> c5
                Square.d5 -> d5
                Square.e5 -> e5
                Square.f5 -> f5
                Square.g5 -> g5
                Square.h5 -> h5
                Square.a6 -> a6
                Square.b6 -> b6
                Square.c6 -> c6
                Square.d6 -> d6
                Square.e6 -> e6
                Square.f6 -> f6
                Square.g6 -> g6
                Square.h6 -> h6
                Square.a7 -> a7
                Square.b7 -> b7
                Square.c7 -> c7
                Square.d7 -> d7
                Square.e7 -> e7
                Square.f7 -> f7
                Square.g7 -> g7
                Square.h7 -> h7
                Square.a8 -> a8
                Square.b8 -> b8
                Square.c8 -> c8
                Square.d8 -> d8
                Square.e8 -> e8
                Square.f8 -> f8
                Square.g8 -> g8
                Square.h8 -> h8
            }

            const val a1 = 0x0UL
            const val b1 = 0x0UL
            const val c1 = 0x0UL
            const val d1 = 0x0UL
            const val e1 = 0x0UL
            const val f1 = 0x0UL
            const val g1 = 0x0UL
            const val h1 = 0x0UL
            const val a2 = 0x1UL
            const val b2 = 0x2UL
            const val c2 = 0x4UL
            const val d2 = 0x8UL
            const val e2 = 0x10UL
            const val f2 = 0x20UL
            const val g2 = 0x40UL
            const val h2 = 0x80UL
            const val a3 = 0x101UL
            const val b3 = 0x202UL
            const val c3 = 0x404UL
            const val d3 = 0x808UL
            const val e3 = 0x1010UL
            const val f3 = 0x2020UL
            const val g3 = 0x4040UL
            const val h3 = 0x8080UL
            const val a4 = 0x10101UL
            const val b4 = 0x20202UL
            const val c4 = 0x40404UL
            const val d4 = 0x80808UL
            const val e4 = 0x101010UL
            const val f4 = 0x202020UL
            const val g4 = 0x404040UL
            const val h4 = 0x808080UL
            const val a5 = 0x1010101UL
            const val b5 = 0x2020202UL
            const val c5 = 0x4040404UL
            const val d5 = 0x8080808UL
            const val e5 = 0x10101010UL
            const val f5 = 0x20202020UL
            const val g5 = 0x40404040UL
            const val h5 = 0x80808080UL
            const val a6 = 0x101010101UL
            const val b6 = 0x202020202UL
            const val c6 = 0x404040404UL
            const val d6 = 0x808080808UL
            const val e6 = 0x1010101010UL
            const val f6 = 0x2020202020UL
            const val g6 = 0x4040404040UL
            const val h6 = 0x8080808080UL
            const val a7 = 0x10101010101UL
            const val b7 = 0x20202020202UL
            const val c7 = 0x40404040404UL
            const val d7 = 0x80808080808UL
            const val e7 = 0x101010101010UL
            const val f7 = 0x202020202020UL
            const val g7 = 0x404040404040UL
            const val h7 = 0x808080808080UL
            const val a8 = 0x1010101010101UL
            const val b8 = 0x2020202020202UL
            const val c8 = 0x4040404040404UL
            const val d8 = 0x8080808080808UL
            const val e8 = 0x10101010101010UL
            const val f8 = 0x20202020202020UL
            const val g8 = 0x40404040404040UL
            const val h8 = 0x80808080808080UL
        }

        object SouthWest {
            operator fun get(square: Square): ULong = when (square) {
                Square.a1 -> a1
                Square.b1 -> b1
                Square.c1 -> c1
                Square.d1 -> d1
                Square.e1 -> e1
                Square.f1 -> f1
                Square.g1 -> g1
                Square.h1 -> h1
                Square.a2 -> a2
                Square.b2 -> b2
                Square.c2 -> c2
                Square.d2 -> d2
                Square.e2 -> e2
                Square.f2 -> f2
                Square.g2 -> g2
                Square.h2 -> h2
                Square.a3 -> a3
                Square.b3 -> b3
                Square.c3 -> c3
                Square.d3 -> d3
                Square.e3 -> e3
                Square.f3 -> f3
                Square.g3 -> g3
                Square.h3 -> h3
                Square.a4 -> a4
                Square.b4 -> b4
                Square.c4 -> c4
                Square.d4 -> d4
                Square.e4 -> e4
                Square.f4 -> f4
                Square.g4 -> g4
                Square.h4 -> h4
                Square.a5 -> a5
                Square.b5 -> b5
                Square.c5 -> c5
                Square.d5 -> d5
                Square.e5 -> e5
                Square.f5 -> f5
                Square.g5 -> g5
                Square.h5 -> h5
                Square.a6 -> a6
                Square.b6 -> b6
                Square.c6 -> c6
                Square.d6 -> d6
                Square.e6 -> e6
                Square.f6 -> f6
                Square.g6 -> g6
                Square.h6 -> h6
                Square.a7 -> a7
                Square.b7 -> b7
                Square.c7 -> c7
                Square.d7 -> d7
                Square.e7 -> e7
                Square.f7 -> f7
                Square.g7 -> g7
                Square.h7 -> h7
                Square.a8 -> a8
                Square.b8 -> b8
                Square.c8 -> c8
                Square.d8 -> d8
                Square.e8 -> e8
                Square.f8 -> f8
                Square.g8 -> g8
                Square.h8 -> h8
            }

            const val a1 = 0x0UL
            const val b1 = 0x0UL
            const val c1 = 0x0UL
            const val d1 = 0x0UL
            const val e1 = 0x0UL
            const val f1 = 0x0UL
            const val g1 = 0x0UL
            const val h1 = 0x0UL
            const val a2 = 0x0UL
            const val b2 = 0x1UL
            const val c2 = 0x2UL
            const val d2 = 0x4UL
            const val e2 = 0x8UL
            const val f2 = 0x10UL
            const val g2 = 0x20UL
            const val h2 = 0x40UL
            const val a3 = 0x0UL
            const val b3 = 0x100UL
            const val c3 = 0x201UL
            const val d3 = 0x402UL
            const val e3 = 0x804UL
            const val f3 = 0x1008UL
            const val g3 = 0x2010UL
            const val h3 = 0x4020UL
            const val a4 = 0x0UL
            const val b4 = 0x10000UL
            const val c4 = 0x20100UL
            const val d4 = 0x40201UL
            const val e4 = 0x80402UL
            const val f4 = 0x100804UL
            const val g4 = 0x201008UL
            const val h4 = 0x402010UL
            const val a5 = 0x0UL
            const val b5 = 0x1000000UL
            const val c5 = 0x2010000UL
            const val d5 = 0x4020100UL
            const val e5 = 0x8040201UL
            const val f5 = 0x10080402UL
            const val g5 = 0x20100804UL
            const val h5 = 0x40201008UL
            const val a6 = 0x0UL
            const val b6 = 0x100000000UL
            const val c6 = 0x201000000UL
            const val d6 = 0x402010000UL
            const val e6 = 0x804020100UL
            const val f6 = 0x1008040201UL
            const val g6 = 0x2010080402UL
            const val h6 = 0x4020100804UL
            const val a7 = 0x0UL
            const val b7 = 0x10000000000UL
            const val c7 = 0x20100000000UL
            const val d7 = 0x40201000000UL
            const val e7 = 0x80402010000UL
            const val f7 = 0x100804020100UL
            const val g7 = 0x201008040201UL
            const val h7 = 0x402010080402UL
            const val a8 = 0x0UL
            const val b8 = 0x1000000000000UL
            const val c8 = 0x2010000000000UL
            const val d8 = 0x4020100000000UL
            const val e8 = 0x8040201000000UL
            const val f8 = 0x10080402010000UL
            const val g8 = 0x20100804020100UL
            const val h8 = 0x40201008040201UL
        }

        object West {
            operator fun get(square: Square): ULong = when (square) {
                Square.a1 -> a1
                Square.b1 -> b1
                Square.c1 -> c1
                Square.d1 -> d1
                Square.e1 -> e1
                Square.f1 -> f1
                Square.g1 -> g1
                Square.h1 -> h1
                Square.a2 -> a2
                Square.b2 -> b2
                Square.c2 -> c2
                Square.d2 -> d2
                Square.e2 -> e2
                Square.f2 -> f2
                Square.g2 -> g2
                Square.h2 -> h2
                Square.a3 -> a3
                Square.b3 -> b3
                Square.c3 -> c3
                Square.d3 -> d3
                Square.e3 -> e3
                Square.f3 -> f3
                Square.g3 -> g3
                Square.h3 -> h3
                Square.a4 -> a4
                Square.b4 -> b4
                Square.c4 -> c4
                Square.d4 -> d4
                Square.e4 -> e4
                Square.f4 -> f4
                Square.g4 -> g4
                Square.h4 -> h4
                Square.a5 -> a5
                Square.b5 -> b5
                Square.c5 -> c5
                Square.d5 -> d5
                Square.e5 -> e5
                Square.f5 -> f5
                Square.g5 -> g5
                Square.h5 -> h5
                Square.a6 -> a6
                Square.b6 -> b6
                Square.c6 -> c6
                Square.d6 -> d6
                Square.e6 -> e6
                Square.f6 -> f6
                Square.g6 -> g6
                Square.h6 -> h6
                Square.a7 -> a7
                Square.b7 -> b7
                Square.c7 -> c7
                Square.d7 -> d7
                Square.e7 -> e7
                Square.f7 -> f7
                Square.g7 -> g7
                Square.h7 -> h7
                Square.a8 -> a8
                Square.b8 -> b8
                Square.c8 -> c8
                Square.d8 -> d8
                Square.e8 -> e8
                Square.f8 -> f8
                Square.g8 -> g8
                Square.h8 -> h8
            }

            const val a1 = 0x0UL
            const val b1 = 0x1UL
            const val c1 = 0x3UL
            const val d1 = 0x7UL
            const val e1 = 0xfUL
            const val f1 = 0x1fUL
            const val g1 = 0x3fUL
            const val h1 = 0x7fUL
            const val a2 = 0x0UL
            const val b2 = 0x100UL
            const val c2 = 0x300UL
            const val d2 = 0x700UL
            const val e2 = 0xf00UL
            const val f2 = 0x1f00UL
            const val g2 = 0x3f00UL
            const val h2 = 0x7f00UL
            const val a3 = 0x0UL
            const val b3 = 0x10000UL
            const val c3 = 0x30000UL
            const val d3 = 0x70000UL
            const val e3 = 0xf0000UL
            const val f3 = 0x1f0000UL
            const val g3 = 0x3f0000UL
            const val h3 = 0x7f0000UL
            const val a4 = 0x0UL
            const val b4 = 0x1000000UL
            const val c4 = 0x3000000UL
            const val d4 = 0x7000000UL
            const val e4 = 0xf000000UL
            const val f4 = 0x1f000000UL
            const val g4 = 0x3f000000UL
            const val h4 = 0x7f000000UL
            const val a5 = 0x0UL
            const val b5 = 0x100000000UL
            const val c5 = 0x300000000UL
            const val d5 = 0x700000000UL
            const val e5 = 0xf00000000UL
            const val f5 = 0x1f00000000UL
            const val g5 = 0x3f00000000UL
            const val h5 = 0x7f00000000UL
            const val a6 = 0x0UL
            const val b6 = 0x10000000000UL
            const val c6 = 0x30000000000UL
            const val d6 = 0x70000000000UL
            const val e6 = 0xf0000000000UL
            const val f6 = 0x1f0000000000UL
            const val g6 = 0x3f0000000000UL
            const val h6 = 0x7f0000000000UL
            const val a7 = 0x0UL
            const val b7 = 0x1000000000000UL
            const val c7 = 0x3000000000000UL
            const val d7 = 0x7000000000000UL
            const val e7 = 0xf000000000000UL
            const val f7 = 0x1f000000000000UL
            const val g7 = 0x3f000000000000UL
            const val h7 = 0x7f000000000000UL
            const val a8 = 0x0UL
            const val b8 = 0x100000000000000UL
            const val c8 = 0x300000000000000UL
            const val d8 = 0x700000000000000UL
            const val e8 = 0xf00000000000000UL
            const val f8 = 0x1f00000000000000UL
            const val g8 = 0x3f00000000000000UL
            const val h8 = 0x7f00000000000000UL
        }

        object NorthWest {
            operator fun get(square: Square): ULong = when (square) {
                Square.a1 -> a1
                Square.b1 -> b1
                Square.c1 -> c1
                Square.d1 -> d1
                Square.e1 -> e1
                Square.f1 -> f1
                Square.g1 -> g1
                Square.h1 -> h1
                Square.a2 -> a2
                Square.b2 -> b2
                Square.c2 -> c2
                Square.d2 -> d2
                Square.e2 -> e2
                Square.f2 -> f2
                Square.g2 -> g2
                Square.h2 -> h2
                Square.a3 -> a3
                Square.b3 -> b3
                Square.c3 -> c3
                Square.d3 -> d3
                Square.e3 -> e3
                Square.f3 -> f3
                Square.g3 -> g3
                Square.h3 -> h3
                Square.a4 -> a4
                Square.b4 -> b4
                Square.c4 -> c4
                Square.d4 -> d4
                Square.e4 -> e4
                Square.f4 -> f4
                Square.g4 -> g4
                Square.h4 -> h4
                Square.a5 -> a5
                Square.b5 -> b5
                Square.c5 -> c5
                Square.d5 -> d5
                Square.e5 -> e5
                Square.f5 -> f5
                Square.g5 -> g5
                Square.h5 -> h5
                Square.a6 -> a6
                Square.b6 -> b6
                Square.c6 -> c6
                Square.d6 -> d6
                Square.e6 -> e6
                Square.f6 -> f6
                Square.g6 -> g6
                Square.h6 -> h6
                Square.a7 -> a7
                Square.b7 -> b7
                Square.c7 -> c7
                Square.d7 -> d7
                Square.e7 -> e7
                Square.f7 -> f7
                Square.g7 -> g7
                Square.h7 -> h7
                Square.a8 -> a8
                Square.b8 -> b8
                Square.c8 -> c8
                Square.d8 -> d8
                Square.e8 -> e8
                Square.f8 -> f8
                Square.g8 -> g8
                Square.h8 -> h8
            }

            const val a1 = 0x0UL
            const val b1 = 0x100UL
            const val c1 = 0x10200UL
            const val d1 = 0x1020400UL
            const val e1 = 0x102040800UL
            const val f1 = 0x10204081000UL
            const val g1 = 0x1020408102000UL
            const val h1 = 0x102040810204000UL
            const val a2 = 0x0UL
            const val b2 = 0x10000UL
            const val c2 = 0x1020000UL
            const val d2 = 0x102040000UL
            const val e2 = 0x10204080000UL
            const val f2 = 0x1020408100000UL
            const val g2 = 0x102040810200000UL
            const val h2 = 0x204081020400000UL
            const val a3 = 0x0UL
            const val b3 = 0x1000000UL
            const val c3 = 0x102000000UL
            const val d3 = 0x10204000000UL
            const val e3 = 0x1020408000000UL
            const val f3 = 0x102040810000000UL
            const val g3 = 0x204081020000000UL
            const val h3 = 0x408102040000000UL
            const val a4 = 0x0UL
            const val b4 = 0x100000000UL
            const val c4 = 0x10200000000UL
            const val d4 = 0x1020400000000UL
            const val e4 = 0x102040800000000UL
            const val f4 = 0x204081000000000UL
            const val g4 = 0x408102000000000UL
            const val h4 = 0x810204000000000UL
            const val a5 = 0x0UL
            const val b5 = 0x10000000000UL
            const val c5 = 0x1020000000000UL
            const val d5 = 0x102040000000000UL
            const val e5 = 0x204080000000000UL
            const val f5 = 0x408100000000000UL
            const val g5 = 0x810200000000000UL
            const val h5 = 0x1020400000000000UL
            const val a6 = 0x0UL
            const val b6 = 0x1000000000000UL
            const val c6 = 0x102000000000000UL
            const val d6 = 0x204000000000000UL
            const val e6 = 0x408000000000000UL
            const val f6 = 0x810000000000000UL
            const val g6 = 0x1020000000000000UL
            const val h6 = 0x2040000000000000UL
            const val a7 = 0x0UL
            const val b7 = 0x100000000000000UL
            const val c7 = 0x200000000000000UL
            const val d7 = 0x400000000000000UL
            const val e7 = 0x800000000000000UL
            const val f7 = 0x1000000000000000UL
            const val g7 = 0x2000000000000000UL
            const val h7 = 0x4000000000000000UL
            const val a8 = 0x0UL
            const val b8 = 0x0UL
            const val c8 = 0x0UL
            const val d8 = 0x0UL
            const val e8 = 0x0UL
            const val f8 = 0x0UL
            const val g8 = 0x0UL
            const val h8 = 0x0UL
        }
    }

}