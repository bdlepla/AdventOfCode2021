class Day09(lines:List<String>) {
    private val data = Matrix.create(lines)

    fun solvePart1() = generateLowPointValues(data).sumOf{it+1}
    fun solvePart2() = generateBasins(data)
        .map{it.count()}
        .sortedDescending()
        .take(3).product()

    private fun generateBasins(data: Matrix): Sequence<Set<Pair<Int, Int>>> =
        generateLowPoints(data)
            .map { data.findBasin(it) }
            //.onEach {println("Found basic of size ${it.count()}")}

    private fun generateLowPoints(data:Matrix) =
        data.allPoints()
            .filter{pt -> pt.validNeighborsValues().all{ data[pt] < it } }

    private fun Point.validNeighborsValues() =
        neighbors().filter{it in data}.map{data[it]}

    private fun generateLowPointValues(data:Matrix) =
        generateLowPoints(data).map{data[it]}

    private fun Sequence<Int>.product() = this.reduce{a,b->a*b}
}
