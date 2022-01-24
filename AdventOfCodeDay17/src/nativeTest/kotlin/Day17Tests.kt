import kotlin.test.Test
import kotlin.test.assertEquals

class Day17Tests {
    @Test
    fun solveSample1Test(){
        val lines = """
            target area: x=20..30, y=-10..-5
        """.trimIndent().split("\n")
        val day17 = Day17(lines)
        val actual = day17.solvePart1()
        val expected = 45
        assertEquals(expected, actual)
    }

    @Test
    fun solveSample2Test(){
        val lines = """
            target area: x=20..30, y=-10..-5
        """.trimIndent().split("\n")
        val day17 = Day17(lines)
        val actual = day17.solvePart2()
        val expected = 112
        assertEquals(expected, actual)
    }
}