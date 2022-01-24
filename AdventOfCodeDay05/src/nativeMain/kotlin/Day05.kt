class Day05(private val lines:List<String>) {

    fun solvePart1():Int =
        lines
            .asSequence()
            .map(::createLine)
            .filter{it.isValid()}
            .flatMap{it.getAllPoints()}
            .groupingBy{it}
            .eachCount()
            .count{it.value > 1}

    fun solvePart2():Int =
        lines
            .asSequence()
            .map(::createLine)
            .filter{it.isValid(true)}
            //.onEach { println(it) }
            .flatMap{it.getAllPoints()}
            .groupingBy{it}
            .eachCount()
            .count{it.value > 1}

    private fun createLine(line:String):Line {
        val points = line.split(" -> ")
        val point1 = points[0].split(",")
        val x1 = point1[0].toInt()
        val y1 = point1[1].toInt()
        val point2 = points[1].split(",")
        val x2 = point2[0].toInt()
        val y2 = point2[1].toInt()
        return Line(Point(x1, y1), Point(x2, y2))
    }
}