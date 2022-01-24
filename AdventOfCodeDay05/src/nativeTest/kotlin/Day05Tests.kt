import kotlin.test.Test
import kotlin.test.assertEquals

class Day05Tests {
    @Test
    fun solveSample1Test() {
        val lines = """
            0,9 -> 5,9
            8,0 -> 0,8
            9,4 -> 3,4
            2,2 -> 2,1
            7,0 -> 7,4
            6,4 -> 2,0
            0,9 -> 2,9
            3,4 -> 1,4
            0,0 -> 8,8
            5,5 -> 8,2
        """.trimIndent().split("\n")

        val day05 = Day05(lines)
        val actual = day05.solvePart1()
        val expected = 5
        assertEquals(expected, actual)
    }

    @Test
    fun solveSample2Test() {
        val lines = """
            0,9 -> 5,9
            8,0 -> 0,8
            9,4 -> 3,4
            2,2 -> 2,1
            7,0 -> 7,4
            6,4 -> 2,0
            0,9 -> 2,9
            3,4 -> 1,4
            0,0 -> 8,8
            5,5 -> 8,2
        """.trimIndent().split("\n")

        val day05 = Day05(lines)
        val actual = day05.solvePart2()
        val expected = 12
        assertEquals(expected, actual)
    }
}