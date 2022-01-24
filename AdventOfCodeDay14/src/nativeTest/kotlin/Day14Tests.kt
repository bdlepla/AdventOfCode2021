import kotlin.test.Test
import kotlin.test.assertEquals

class Day14Tests {

    @Test
    fun solvePart1Test() {
        val lines = """
            NNCB

            CH -> B
            HH -> N
            CB -> H
            NH -> C
            HB -> C
            HC -> B
            HN -> C
            NN -> C
            BH -> H
            NC -> B
            NB -> B
            BN -> B
            BB -> N
            BC -> B
            CC -> N
            CN -> C
        """.trimIndent().split("\n")

        val day14 = Day14(lines)
        val actual = day14.solvePart1()
        val expected = 1588L
        assertEquals(expected, actual)
    }

    @Test
    fun solvePart2Test() {
        val lines = """
            NNCB

            CH -> B
            HH -> N
            CB -> H
            NH -> C
            HB -> C
            HC -> B
            HN -> C
            NN -> C
            BH -> H
            NC -> B
            NB -> B
            BN -> B
            BB -> N
            BC -> B
            CC -> N
            CN -> C
        """.trimIndent().split("\n")

        val day14 = Day14(lines)
        val actual = day14.solvePart2()
        val expected = 2_188_189_693_529
        assertEquals(expected, actual)
    }
}

