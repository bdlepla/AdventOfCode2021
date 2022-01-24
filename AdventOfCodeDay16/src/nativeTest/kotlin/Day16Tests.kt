import kotlin.test.Test
import kotlin.test.assertEquals

class Day16Tests {

    @Test
    fun solveSample1Test() {
        val lines = """
           D2FE28
           38006F45291200
           EE00D40C823060
           8A004A801A8002F478
           620080001611562C8802118E34
           C0015000016115A2E0802F182340
           A0016C880162017C3686B18A3D4780
        """.trimIndent().split("\n")

        val day16 = Day16(lines)
        val actual = day16.solvePart1()
        val expected = 111
        assertEquals(expected, actual)
    }

    @Test
    fun solveSample2Test() {
        val lines = """
            C200B40A82
            04005AC33890
            880086C3E88112
            CE00C43D881120
            D8005AC2A8F0
            F600BC2D8F
            9C005AC2F8F0
            9C0141080250320F1802104A08
        """.trimIndent().split("\n")

        val day16 = Day16(lines)
        val actual = day16.solvePart2()
        val expected = 75L
        assertEquals(expected, actual)
    }
}