import kotlin.test.Test
import kotlin.test.assertEquals

class Day13Tests {
    @Test
    fun solvePart1Test() {
        val lines = """
            6,10
            0,14
            9,10
            0,3
            10,4
            4,11
            6,0
            6,12
            4,1
            0,13
            10,12
            3,4
            3,0
            8,4
            1,10
            2,14
            8,10
            9,0

            fold along y=7
            fold along x=5
        """.trimIndent().split("\n")

        val day13 = Day13(lines)
        val actual = day13.solvePart1()
        val expected = 17
        assertEquals(expected, actual)
    }

    @Test
    fun solvePart2Test() {
        val lines = """
            6,10
            0,14
            9,10
            0,3
            10,4
            4,11
            6,0
            6,12
            4,1
            0,13
            10,12
            3,4
            3,0
            8,4
            1,10
            2,14
            8,10
            9,0

            fold along y=7
            fold along x=5
        """.trimIndent().split("\n")

        val day13 = Day13(lines)
        val actual = day13.solvePart2()
        val expected = """
            Start Print Matrix
            #####
            #...#
            #...#
            #...#
            #####
            .....
            .....
            End Print Matrix
        """.trimIndent()
        assertEquals(expected, actual)
    }
}