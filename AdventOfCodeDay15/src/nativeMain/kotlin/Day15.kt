class Day15(private val lines:List<String>) {

    fun solvePart1() = Matrix.create(lines).traverse()

    fun solvePart2() = Matrix.create(lines, 5, 5).traverse()

    private fun Matrix.traverse():Int {
        val startPoint = Point(0,0)
        val destPoint = this.lastPoint
        val visited = mutableSetOf<Point>()
        val queue = PriorityQueue<Point>().also{ it.insert(startPoint,0) }

        while (queue.isNotEmpty()) {
            val (point, sum) = queue.pullWithPriority()
            if (point == destPoint) return sum
            if (point in visited) continue
            visited.add(point)

            point
                .getNeighbors()
                .filter{ it in this }
                .forEach{ pt -> queue.insert(pt, sum+this[pt]) }
        }
        return 0
    }

    private fun Point.getNeighbors() =
        listOf(
            Point(this.first-1, this.second), // left
            Point(this.first+1, this.second), // right
            Point(this.first, this.second-1), // up
            Point(this.first, this.second+1)  // down
        )
}