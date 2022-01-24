import kotlin.test.Test
import kotlin.test.assertEquals

class Day02Tests {

    @Test
    fun solveSample1Test() {
        val lines = """
            forward 5
            down 5
            forward 8
            up 3
            down 8
            forward 2
        """.trimIndent().split("\n")

        val day02 = Day02(lines)
        val actual = day02.solvePart1()
        val expected = 150L
        assertEquals(expected, actual)
    }

    @Test
    fun solveSample2Test() {
        val lines = """
            forward 5
            down 5
            forward 8
            up 3
            down 8
            forward 2
        """.trimIndent().split("\n")

        val day02 = Day02(lines)
        val actual = day02.solvePart2()
        val expected = 900L
        assertEquals(expected, actual)
    }
}