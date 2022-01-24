import kotlin.test.Test
import kotlin.test.assertEquals

class Day01Tests {
    @Test
    fun sample01Test() {
        val lines = """
            199
            200
            208
            210
            200
            207
            240
            269
            260
            263
        """.trimIndent().split("\n")

        val day01 = Day01(lines)
        val actual = day01.solvePart1()
        val expected = 7
        assertEquals(expected, actual)
    }

    @Test
    fun sample02Test() {
        val lines = """
            199
            200
            208
            210
            200
            207
            240
            269
            260
            263
        """.trimIndent().split("\n")

        val day01 = Day01(lines)
        val actual = day01.solvePart2()
        val expected = 5
        assertEquals(expected, actual)
    }
}