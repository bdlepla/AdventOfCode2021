import kotlin.math.absoluteValue

class Day07(private val lines:List<String>) {
    private fun createCrabs() = lines.first().split(",").map{it.toInt()}

    fun solvePart1() = solve{it}
    fun solvePart2() = solve{it.seriesAdd()}

    private fun solve(positionValue:(Int) -> Int):Int {
        val crabs = createCrabs()
        return crabs.toIntRange()
            .map { pos ->
                crabs
                    .sumOf { crab -> positionValue((crab - pos).absoluteValue) }
            }
            .minOf{it}
    }
}

private fun Iterable<Int>.toIntRange() =
    this.minOf{it}..this.maxOf{it}

private fun Int.seriesAdd() = (this * (this + 1))/2