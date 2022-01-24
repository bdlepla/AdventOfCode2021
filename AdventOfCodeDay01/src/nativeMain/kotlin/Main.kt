import platform.posix.timer_create
import kotlin.system.measureTimeMicros
import kotlin.system.measureTimeMillis

fun main() {
    val input = generateSequence(::readLine){readLine()}.joinToString("")
    val lines = input.split("\n")
    val count = lines.count()
    println("$count lines")
    
    val day01 = Day01(lines)
    
    val time1 = measureTimeMicros {
        val result1 = day01.solvePart1()
        println("Result #1 = $result1")
    }
    println("Results #1 in $time1 ms")
    println("")

    val time2 = measureTimeMicros {
        val result2 = day01.solvePart2()
        println("Result #2 = $result2")
    }
    println("Result #2 in $time2 ms")
}