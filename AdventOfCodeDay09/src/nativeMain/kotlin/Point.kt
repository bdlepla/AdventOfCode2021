typealias Point = Pair<Int,Int>

fun Point.neighbors() = sequence{
    yield(Point(first+1, second))
    yield(Point(first-1, second))
    yield(Point(first, second-1))
    yield(Point(first, second+1))
}