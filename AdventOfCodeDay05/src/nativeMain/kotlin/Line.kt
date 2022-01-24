import platform.posix.abs
import kotlin.math.min

typealias Point = Pair<Int, Int>
class Line(private val point1:Point, private val point2:Point) {
    override fun toString() = buildString{
        append(point1).append(",").append(point2)
    }
    fun isHorizontal() = point1.second == point2.second
    fun isVertical() = point1.first == point2.first
    fun isDiagonal() = abs(point1.first - point2.first) == abs(point1.second - point2.second)
    fun isValid(diagonalAllowed:Boolean=false) = isHorizontal() || isVertical()
            || (diagonalAllowed && isDiagonal())
    fun getAllPoints():Sequence<Point> = sequence {
        val points = when {
            isHorizontal() -> {
                val y = point1.second
                val (minx, maxX) = minMax(point1.first, point2.first)
                (minx..maxX).map { x -> Point(x, y) }
            }
            isVertical() -> {
                val x = point1.first
                val (minY, maxY) = minMax(point1.second, point2.second)
                (minY..maxY).map { y -> Point(x, y)}
            }
            else -> { // diagonal
                val x = point1.first
                val y = point1.second
                val xStep = if (point2.first >  point1.first) 1 else -1
                val yStep = if (point2.second > point1.second) 1 else -1
                val distance = abs(point2.second - point1.second)
                (0..distance).map{ step -> Point(x+xStep*step, y+yStep*step)}
            }
        }
        yieldAll(points)
    }

    private fun minMax(first:Int, second:Int) =
        if (first < second)  Pair(first, second)
        else Pair(second, first)

}