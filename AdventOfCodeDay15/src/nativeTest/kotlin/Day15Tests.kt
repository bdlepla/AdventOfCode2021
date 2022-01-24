import kotlin.test.Test
import kotlin.test.assertEquals

class Day15Tests {
    @Test
    fun solvePart1Sample() {
        val lines = """
            1163751742
            1381373672
            2136511328
            3694931569
            7463417111
            1319128137
            1359912421
            3125421639
            1293138521
            2311944581
        """.trimIndent().split("\n")

        val day15 = Day15(lines)
        val actual = day15.solvePart1()
        val expected = 40
        assertEquals(expected, actual)
    }

    @Test
    fun solvePart2Sample() {
        val lines = """
            1163751742
            1381373672
            2136511328
            3694931569
            7463417111
            1319128137
            1359912421
            3125421639
            1293138521
            2311944581
        """.trimIndent().split("\n")

        val day15 = Day15(lines)
        val actual = day15.solvePart2()
        val expected = 315
        assertEquals(expected, actual)
    }
}