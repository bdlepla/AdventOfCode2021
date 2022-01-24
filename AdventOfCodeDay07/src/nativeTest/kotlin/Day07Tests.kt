import kotlin.test.Test
import kotlin.test.assertEquals

class Day07Tests {
    @Test
    fun solveSample1Test() {
        val lines = """
            16,1,2,0,4,2,7,1,2,14
        """.trimIndent().split("\n")

        val day07 = Day07(lines)
        val actual = day07.solvePart1()
        val expected = 37
        assertEquals(expected, actual)
    }

    @Test
    fun solveSample2Test() {
        val lines = """
            16,1,2,0,4,2,7,1,2,14
        """.trimIndent().split("\n")

        val day07 = Day07(lines)
        val actual = day07.solvePart2()
        val expected = 168
        assertEquals(expected, actual)
    }
}