import kotlin.test.Test
import kotlin.test.assertEquals

class Day09Tests {
    @Test
    fun solveSample1Test() {
        val lines = """
            2199943210
            3987894921
            9856789892
            8767896789
            9899965678
        """.trimIndent().split("\n")

        val day09 = Day09(lines)
        val actual = day09.solvePart1()
        val expected = 15
        assertEquals(expected, actual)
    }

    @Test
    fun solveSample2Test() {
        val lines = """
            2199943210
            3987894921
            9856789892
            8767896789
            9899965678
        """.trimIndent().split("\n")

        val day09 = Day09(lines)
        val actual = day09.solvePart2()
        val expected = 1134
        assertEquals(expected, actual)
    }
}