import kotlin.test.Test
import kotlin.test.assertEquals

class Day03Tests {
    @Test
    fun solveSample1Test() {
        val lines = """
        00100
        11110
        10110
        10111
        10101
        01111
        00111
        11100
        10000
        11001
        00010
        01010
    """.trimIndent().split("\n")
        val day03 = Day03(lines)
        val actual = day03.solvePart1()
        val expected = 198L
        assertEquals(expected, actual)
    }

    @Test
    fun solveSample2Test() {
        val lines = """
        00100
        11110
        10110
        10111
        10101
        01111
        00111
        11100
        10000
        11001
        00010
        01010
    """.trimIndent().split("\n")
        val day03 = Day03(lines)
        val actual = day03.solvePart2()
        val expected = 230L
        assertEquals(expected, actual)
    }
}