class Day01(lines:List<String>) {
    private val data = lines.map{it.toInt()}
    fun solvePart1() = countIncreasing(data)

    private fun countIncreasing(list:List<Int>) =
        list.zipWithNext().count {it.first < it.second}

    fun solvePart2():Int {
        val windowList = data.windowed(3, 1){it.sum()}
        //println(windowList)
        return countIncreasing(windowList)
    }
}