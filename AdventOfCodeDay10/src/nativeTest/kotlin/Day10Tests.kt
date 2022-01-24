import kotlin.test.Test
import kotlin.test.assertEquals

class Day10Tests {
    @Test
    fun solveSample1Test() {
        val lines = """
            [({(<(())[]>[[{[]{<()<>>
            [(()[<>])]({[<{<<[]>>(
            {([(<{}[<>[]}>{[]{[(<()>
            (((({<>}<{<{<>}{[]{[]{}
            [[<[([]))<([[{}[[()]]]
            [{[{({}]{}}([{[{{{}}([]
            {<[[]]>}<{[{[{[]{()[[[]
            [<(<(<(<{}))><([]([]()
            <{([([[(<>()){}]>(<<{{
            <{([{{}}[<[[[<>{}]]]>[]]
        """.trimIndent().split("\n")

        val day10 = Day10(lines)
        val actual = day10.solvePart1()
        val expected = 26397
        assertEquals(expected, actual)
    }

    @Test
    fun solveSample2Test() {
        val lines = """
            [({(<(())[]>[[{[]{<()<>>
            [(()[<>])]({[<{<<[]>>(
            {([(<{}[<>[]}>{[]{[(<()>
            (((({<>}<{<{<>}{[]{[]{}
            [[<[([]))<([[{}[[()]]]
            [{[{({}]{}}([{[{{{}}([]
            {<[[]]>}<{[{[{[]{()[[[]
            [<(<(<(<{}))><([]([]()
            <{([([[(<>()){}]>(<<{{
            <{([{{}}[<[[[<>{}]]]>[]]
        """.trimIndent().split("\n")

        val day10 = Day10(lines)
        val actual = day10.solvePart2()
        val expected = 288957L
        assertEquals(expected, actual)
    }
}