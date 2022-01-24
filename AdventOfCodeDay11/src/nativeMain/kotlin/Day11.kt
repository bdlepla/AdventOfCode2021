class Day11(private val lines:List<String>) {

    fun solvePart1():Int {
        val cavern = Matrix.create(lines)
        return (0 until 100).sumOf { countFlashesWhenStepping(cavern) }
    }

    fun solvePart2():Int {
        val cavern = Matrix.create(lines)
        val allPointsNumber = cavern.allPoints().count()
        var steps = 1
        while (true) {
            if (allPointsNumber == countFlashesWhenStepping(cavern)) {
                return steps
            }
            steps += 1
        }
    }

    private fun countFlashesWhenStepping(cavern:Matrix):Int {
        //println("Start Step")
        //println("----------")
        //println(cavern.print())
        //println("----------")
        //println("")
        // increment all per step
        cavern.allPoints().forEach { pt -> cavern[pt] += 1 }

        // perform flashing; points only flash once per step
        // 10 indicates just flashed and must be handled (increment neighbors including diagonals)
        // >10 has already flashed and does not need to be activated this step
        //    just counted
        val queue = Queue(cavern.allPoints().filter { cavern[it] == 10 })
        val visited = emptySet<Point>().toMutableSet()
        while (queue.isNotEmpty()) {
            //println("queue = ${queue.asSequence().joinToString()}")
            val point = queue.dequeue()
            if (point !in visited) {
                visited.add(point)
                cavern[point] += 1 // increment to 10 so that we don't flash it again
                val neighbors = point.validNeighbors(cavern)
                neighbors.forEach { pt -> cavern[pt] += 1 }
                queue.enqueueAll(neighbors.filter { pt -> cavern[pt] == 10 })
            }
        }

        // find all that flashed this step
        val pointsToReset = cavern.allPoints().filter { pt -> cavern[pt] > 9 }.toList()

        // reset flashed to 0
        pointsToReset.forEach { pt -> cavern[pt] = 0 }

        // return count of flashed this step
        //println("${pointsToReset.count()} flashed this step")
        return pointsToReset.count()
    }

    private fun Point.neighbors() = listOf (
        Point(this.first-1, this.second-1),
        Point(this.first, this.second-1),
        Point(this.first+1, this.second-1),
        Point(this.first-1, this.second),
        Point(this.first+1, this.second),
        Point(this.first-1, this.second+1),
        Point(this.first, this.second+1),
        Point(this.first+1, this.second+1))

    private fun Point.validNeighbors(cavern:Matrix) = this.neighbors().filter{it in cavern}
}