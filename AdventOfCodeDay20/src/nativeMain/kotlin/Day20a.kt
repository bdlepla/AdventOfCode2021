data class Point2d(val x:Int, val y:Int)

class Day20a(lines:List<String>) {
    val input = lines.takeWhile{it.isNotEmpty()}.joinToString("")
    private val algorithmString: String = input.map { if (it == '#') '1' else '0' }.joinToString("")
    private val startingImage: List<String> = parseInput(lines)

    fun solvePart1(): Int =
        solve(2)

    fun solvePart2(): Int =
        solve(50)

    private fun parseInput(input: List<String>): List<String> =
        input.dropWhile{it.isNotEmpty()}.drop(1).map { row ->
            row.map { char ->
                if (char == '#') 1 else 0
            }.joinToString("")
        }

    private fun Point2d.surrounding(): List<Point2d> =
        listOf(
            Point2d(x - 1, y - 1), Point2d(x, y - 1), Point2d(x + 1, y - 1),
            Point2d(x - 1, y), this, Point2d(x + 1, y),
            Point2d(x - 1, y + 1), Point2d(x, y + 1), Point2d(x + 1, y + 1),
        )

    private operator fun List<String>.contains(pixel: Point2d): Boolean =
        pixel.y in this.indices && pixel.x in this.first().indices

    private fun List<String>.valueAt(pixel: Point2d, defaultValue: Char): Char =
        if (pixel in this) this[pixel.y][pixel.x] else defaultValue

    private fun List<String>.nextRound(defaultValue: Char): List<String> =
        (-1..this.size).map { y ->
            (-1..this.first().length).map { x ->
                algorithmString[
                        Point2d(x, y)
                            .surrounding()
                            .map { this.valueAt(it, defaultValue) }
                            .joinToString("")
                            .toInt(2)
                ]
            }.joinToString("")
        }

    private fun solve(rounds: Int): Int =
        (1 .. rounds).fold(startingImage to '0') { (image, defaultValue), _ ->
            image.nextRound(defaultValue) to if (defaultValue == '0') algorithmString.first() else algorithmString.last()
        }.first.sumOf { it.count { char -> char == '1' } }
}