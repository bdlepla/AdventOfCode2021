import kotlin.test.Test
import kotlin.test.assertEquals

class Day12Tests {

    @Test
    fun solveSample1Part1Test() {
        val lines = """
            start-A
            start-b
            A-c
            A-b
            b-d
            A-end
            b-end
        """.trimIndent().split("\n")

        val day12 = Day12(lines)
        val actual = day12.solvePart1()
        val expected = 10
        assertEquals(expected, actual )
    }

    @Test
    fun solveSample2Part1Test() {
        val lines = """
            dc-end
            HN-start
            start-kj
            dc-start
            dc-HN
            LN-dc
            HN-end
            kj-sa
            kj-HN
            kj-dc
        """.trimIndent().split("\n")

        val day12 = Day12(lines)
        val actual = day12.solvePart1()
        val expected = 19
        assertEquals(expected, actual )
    }

    @Test
    fun solveSample3Part1Test() {
        val lines = """
            fs-end
            he-DX
            fs-he
            start-DX
            pj-DX
            end-zg
            zg-sl
            zg-pj
            pj-he
            RW-he
            fs-DX
            pj-RW
            zg-RW
            start-pj
            he-WI
            zg-he
            pj-fs
            start-RW
        """.trimIndent().split("\n")

        val day12 = Day12(lines)
        val actual = day12.solvePart1()
        val expected = 226
        assertEquals(expected, actual )
    }

    @Test
    fun solveSample1Part2Test() {
        val lines = """
            start-A
            start-b
            A-c
            A-b
            b-d
            A-end
            b-end
        """.trimIndent().split("\n")

        val day12 = Day12(lines)
        val actual = day12.solvePart2()
        val expected = 36
        assertEquals(expected, actual )
    }

    @Test
    fun solveSample2Part2Test() {
        val lines = """
            dc-end
            HN-start
            start-kj
            dc-start
            dc-HN
            LN-dc
            HN-end
            kj-sa
            kj-HN
            kj-dc
        """.trimIndent().split("\n")

        val day12 = Day12(lines)
        val actual = day12.solvePart2()
        val expected = 103
        assertEquals(expected, actual )
    }

    @Test
    fun solveSample3Part2Test() {
        val lines = """
            fs-end
            he-DX
            fs-he
            start-DX
            pj-DX
            end-zg
            zg-sl
            zg-pj
            pj-he
            RW-he
            fs-DX
            pj-RW
            zg-RW
            start-pj
            he-WI
            zg-he
            pj-fs
            start-RW
        """.trimIndent().split("\n")

        val day12 = Day12(lines)
        val actual = day12.solvePart2()
        val expected = 3509
        assertEquals(expected, actual )
    }
}

/*

 */