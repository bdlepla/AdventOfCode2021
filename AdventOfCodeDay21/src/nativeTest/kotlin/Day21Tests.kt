import kotlin.test.Test
import kotlin.test.assertEquals

class Day21Tests {
    val lines = """
        Player 1 starting position: 4
        Player 2 starting position: 8
    """.trimIndent().split("\n")

    @Test
    fun solveSample1Test() {
        val day21 = Day21(lines)
        val actual = day21.solvePart1()
        val expected = 739785L
        assertEquals(expected, actual)
    }

    @Test
    fun solveSample2Test() {
        val day21 = Day21(lines)
        val actual = day21.solvePart2()
        val expected = 444_356_092_776_315L
        assertEquals(expected, actual)
    }
}