import kotlin.test.Test
import kotlin.test.assertEquals

class Day06Tests {

    @Test
    fun solveSample1Test() {
        val lines = """
        3,4,3,1,2
        """.trimIndent().split("\n")

        val day06 = Day06(lines)
        val actual = day06.solvePart1()
        val expected = 5934L
        assertEquals(expected, actual)
    }

    @Test
    fun solveSample2Test() {
        val lines = """
        3,4,3,1,2
        """.trimIndent().split("\n")

        val day06 = Day06(lines)
        val actual = day06.solvePart2()
        val expected = 26_984_457_539L
        assertEquals(expected, actual)
    }
}