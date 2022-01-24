import kotlin.system.measureTimeMicros

fun main() {
    val input = generateSequence(::readLine){readLine()}
        .joinToString("\n")
    val lines = input.split("\n")
    val count = lines.count()
    println("$count lines")

    val day03 = Day03(lines)

    val time1 = measureTimeMicros {
        val result1 = day03.solvePart1()
        println("Result #1 = $result1")
    }
    println("Results #1 in $time1 ms")

    val time2 = measureTimeMicros {
         val result2 = day03.solvePart2()
         println("Result #2 = $result2")
     }
     println("Results #2 in $time2 ms")

    println("")


    //val time2 = measureTimeMicros {
    //    val result2 = day03.solvePart2()
    //    println("Result #2 = $result2")
    //}
    //println("Result #2 in $time2 ms")

    /*
     val time2a = measureTimeMicros {
         val result2a = day03.solvePart2a()
         println("Result #2a = $result2a")
     }
     println("Result #2a in $time2a ms")
     */
}