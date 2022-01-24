import kotlin.system.measureTimeMicros

fun main() {
    val lines = readAllLinesFromStdInput()
    val count = lines.count()

    println("$count lines")
    val day05 = Day05(lines)

    val time1 = measureTimeMicros {
        val result1 = day05.solvePart1()
        println("Result #1 = $result1")
    }
    println("Results #1 in $time1 ms")

    val time2 = measureTimeMicros {
        val result2 = day05.solvePart2()
        println("Result #2 = $result2")
    }
    println("Results #2 in $time2 ms")
}

fun readAllLinesFromStdInput():List<String> =
    buildString()
    {
        while (true) {
            val line = readLine() ?: break
            append(line)
        }
    }.split("\n")
