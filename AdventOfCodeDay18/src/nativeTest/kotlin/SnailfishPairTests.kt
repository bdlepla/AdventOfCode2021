import kotlin.test.Test
import kotlin.test.assertEquals

class SnailfishPairTests {
    @Test
    fun basicSnailfishPairPart1Test() {
        val lines = """
            [1,2]
            [[1,2],3]
            [9,[8,7]]
            [[1,9],[8,5]]
            [[[[1,2],[3,4]],[[5,6],[7,8]]],9]
            [[[9,[3,8]],[[0,9],6]],[[[3,7],[4,9]],3]]
            [[[[1,3],[5,3]],[[1,3],[8,7]]],[[[4,9],[6,9]],[[8,2],[7,3]]]]
        """.trimIndent().split("\n")
        lines.forEach{expected ->
            print("Investigating $expected")
            val sfPair = SnailfishPair(expected)
            val actual = sfPair.toString()
            assertEquals(expected, actual)
            println(" -> Passed")
        }
    }

    @Test
    fun snailfishAdditionTest() {
        val line1 = "[1,2]"
        val sf1 = SnailfishPair(line1)
        val line2 = "[[3,4],5]"
        val sf2 = SnailfishPair(line2)
        val sf3 = sf1 + sf2
        val actual = sf3.toString()
        val expected = "[[1,2],[[3,4],5]]"
        assertEquals(expected, actual)
    }

    @Test
    fun snailfishExplode1Test() {
        val sf = SnailfishPair("[[[[[9,8],1],2],3],4]")
        sf.explode()
        val actual = sf.toString()
        val expected = "[[[[0,9],2],3],4]"
        assertEquals(expected, actual)
    }

    @Test
    fun snailfishExplode2Test() {
        val sf = SnailfishPair("[7,[6,[5,[4,[3,2]]]]]")
        sf.explode()
        val actual = sf.toString()
        val expected = "[7,[6,[5,[7,0]]]]"
        assertEquals(expected, actual)
    }

    @Test
    fun snailfishExplode3Test() {
        val sf = SnailfishPair("[[6,[5,[4,[3,2]]]],1]")
        sf.explode()
        val actual = sf.toString()
        val expected = "[[6,[5,[7,0]]],3]"
        assertEquals(expected, actual)
    }

    @Test
    fun snailfishExplode4Test() {
        val sf = SnailfishPair("[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]")
        sf.explode()
        val actual = sf.toString()
        val expected = "[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]"
        assertEquals(expected, actual)
    }

    @Test
    fun snailfishExplode5Test() {
        val sf = SnailfishPair("[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]")
        sf.explode()
        val actual = sf.toString()
        val expected = "[[3,[2,[8,0]]],[9,[5,[7,0]]]]"
        assertEquals(expected, actual)
    }

    @Test
    fun snailfishSplit1Test() {
        val sf = SnailfishPair("[[[[0,7],4],[15,[0,13]]],[1,1]]")
        sf.split()
        val actual = sf.toString()
        val expected = "[[[[0,7],4],[[7,8],[0,13]]],[1,1]]"
        assertEquals(expected, actual)
    }

    @Test
    fun snailfishSplit2Test() {
        val sf = SnailfishPair("[[[[0,7],4],[[7,8],[0,13]]],[1,1]]")
        sf.split()
        val actual = sf.toString()
        val expected = "[[[[0,7],4],[[7,8],[0,[6,7]]]],[1,1]]"
        assertEquals(expected, actual)
    }

    @Test
    fun snailfishReduce1Test() {
        val sf = SnailfishPair("[[[[[4,3],4],4],[7,[[8,4],9]]],[1,1]]")
        sf.reduce()
        val actual = sf.toString()
        val expected = "[[[[0,7],4],[[7,8],[6,0]]],[8,1]]"
        assertEquals(expected, actual)
    }

    @Test
    fun snailfishReduce2Test() {
        val sf = SnailfishPair("[[[[[1,1],[2,2]],[3,3]],[4,4]],[5,5]]")
        sf.reduce()
        val actual = sf.toString()
        val expected = "[[[[3,0],[5,3]],[4,4]],[5,5]]"
        assertEquals(expected, actual)
    }


    @Test
    fun snailfishAddlines1Test() {
        val lines = """
            [1,1]
            [2,2]
            [3,3]
            [4,4]
        """.trimIndent().split("\n")
        val sf = Day18.add(lines)
        val actual = sf.toString()
        val expected = "[[[[1,1],[2,2]],[3,3]],[4,4]]"
        assertEquals(expected, actual)
    }

    @Test
    fun snailfishAddlines2Test() {
        val lines = """
            [1,1]
            [2,2]
            [3,3]
            [4,4]
            [5,5]
        """.trimIndent().split("\n")
        val sf = Day18.add(lines)
        val actual = sf.toString()
        val expected = "[[[[3,0],[5,3]],[4,4]],[5,5]]"
        assertEquals(expected, actual)
    }

    @Test
    fun snailfishAddlines3Test() {
        val lines = """
            [1,1]
            [2,2]
            [3,3]
            [4,4]
            [5,5]
            [6,6]
        """.trimIndent().split("\n")
        val sf = Day18.add(lines)
        val actual = sf.toString()
        val expected = "[[[[5,0],[7,4]],[5,5]],[6,6]]"
        assertEquals(expected, actual)
    }

    @Test
    fun snailfishAddlines4Test() {
        val lines = """
            [[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]
            [7,[[[3,7],[4,3]],[[6,3],[8,8]]]]
            [[2,[[0,8],[3,4]]],[[[6,7],1],[7,[1,6]]]]
            [[[[2,4],7],[6,[0,5]]],[[[6,8],[2,8]],[[2,1],[4,5]]]]
            [7,[5,[[3,8],[1,4]]]]
            [[2,[2,2]],[8,[8,1]]]
            [2,9]
            [1,[[[9,3],9],[[9,0],[0,7]]]]
            [[[5,[7,4]],7],1]
            [[[[4,2],2],6],[8,7]]
        """.trimIndent().split("\n")
        val sf = Day18.add(lines)
        val actual = sf.toString()
        val expected = "[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]"
        assertEquals(expected, actual)
    }

    @Test
    fun snailfishMagnitude1Test() {
        val sf = SnailfishPair("[[1,2],[[3,4],5]]")
        val actual = sf.magnitude()
        val expected = 143L
        assertEquals(expected, actual)
    }

    @Test
    fun snailfishMagnitude2Test() {
        val sf = SnailfishPair("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]")
        val actual = sf.magnitude()
        val expected = 1384L
        assertEquals(expected, actual)
    }

    @Test
    fun snailfishMagnitude3Test() {
        val sf = SnailfishPair("[[[[1,1],[2,2]],[3,3]],[4,4]]")
        val actual = sf.magnitude()
        val expected = 445L
        assertEquals(expected, actual)
    }

    @Test
    fun snailfishMagnitude4Test() {
        val sf = SnailfishPair("[[[[3,0],[5,3]],[4,4]],[5,5]]")
        val actual = sf.magnitude()
        val expected = 791L
        assertEquals(expected, actual)
    }

    @Test
    fun snailfishMagnitude5Test() {
        val sf = SnailfishPair("[[[[5,0],[7,4]],[5,5]],[6,6]]")
        val actual = sf.magnitude()
        val expected = 1137L
        assertEquals(expected, actual)
    }

    @Test
    fun snailfishMagnitude6Test() {
        val sf = SnailfishPair("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]")
        val actual = sf.magnitude()
        val expected = 3488L
        assertEquals(expected, actual)
    }


}