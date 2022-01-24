import kotlin.test.Test
import kotlin.test.assertEquals

class Day20Tests {
    val lines = """
            ..#.#..#####.#.#.#.###.##.....###.##.#..###.####..#####..#....#..#..##..##
            #..######.###...####..#..#####..##..#.#####...##.#.#..#.##..#.#......#.###
            .######.###.####...#.##.##..#..#..#####.....#.#....###..#.##......#.....#.
            .#..#..##..#...##.######.####.####.#.#...#.......#..#.#.#...####.##.#.....
            .#..#...##.#.##..#...##.#.##..###.#......#.#.......#.#.#.####.###.##...#..
            ...####.#..#..#.##.#....##..#.####....##...##..#...#......#.#.......#.....
            ..##..####..#...#.#.#...##..#.#..###..#####........#..####......#..#

            #..#.
            #....
            ##..#
            ..#..
            ..###
        """.trimIndent().split("\n")
    @Test
    fun solveSample1Test() {
        val day20 = Day20a(lines)
        val actual = day20.solvePart1()
        val expected = 35
        assertEquals(expected, actual)
    }

    @Test
    fun solveSample2Test() {
        val day20 = Day20a(lines)
        val actual = day20.solvePart2()
        val expected = 3351
        assertEquals(expected, actual)
    }
}