import kotlin.test.Test
import kotlin.test.assertEquals

class Day23Tests {
    @Test
    fun solveSample1Test() {
        val lines = listOf(
            "#############",
            "#...........#",
            "###B#C#B#D###",
            "  #A#D#C#A#  ",
            "  #########  "
        )

        val day23 = Day23(lines)
        val actual = day23.data.print()
        val expected = lines.joinToString("\n")
        assertEquals(expected, actual)
    }

    @Test
    fun solveSample1Step1Test() {
        val lines = listOf(
            "#############",
            "#...........#",
            "###B#C#B#D###",
            "  #A#D#C#A#  ",
            "  #########  "
        )

        val day23 = Day23(lines)
        day23.performStep()
        val actual = day23.data.print()
        val expected = listOf(
            "#############",
            "#...B.......#",
            "###B#C#.#D###",
            "  #A#D#C#A#  ",
            "  #########  "
        ).joinToString("\n")
        assertEquals(expected, actual)
    }
}