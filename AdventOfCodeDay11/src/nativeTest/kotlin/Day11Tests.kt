import kotlin.test.Test
import kotlin.test.assertEquals

class Day11Tests {
    @Test
    fun solveSample1Test() {
        val lines = """
            5483143223
            2745854711
            5264556173
            6141336146
            6357385478
            4167524645
            2176841721
            6882881134
            4846848554
            5283751526
        """.trimIndent().split("\n")

        val day11 = Day11(lines)
        val actual = day11.solvePart1()
        val expected = 1656
        assertEquals(expected, actual)
    }

    @Test
    fun solveSample2Test() {
        val lines = """
            5483143223
            2745854711
            5264556173
            6141336146
            6357385478
            4167524645
            2176841721
            6882881134
            4846848554
            5283751526
        """.trimIndent().split("\n")

        val day11 = Day11(lines)
        val actual = day11.solvePart2()
        val expected = 195
        assertEquals(expected, actual)
    }
}