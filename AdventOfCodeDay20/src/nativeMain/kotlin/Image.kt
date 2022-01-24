typealias Point = Pair<Int,Int>

class Image(private val defaultSpace:Boolean) {
    private val points = mutableMapOf<Point, Boolean>()
    fun countLights() = points.values.count{it}
    private fun extents():Pair<IntRange,IntRange> {
        val keys = points.keys
        val minX = keys.minOf{it.first}
        val maxX = keys.maxOf{it.first}
        val minY = keys.minOf{it.second}
        val maxY = keys.maxOf{it.second}
        return minX..maxX to minY..maxY
    }

    fun transform(imageEnhance:String):Image {
        val (xExt, yExt) = extents()
        val newXExt = xExt.inflate(1)
        val newYExt = yExt.inflate(1)
        val ret = Image(imageEnhance[0]=='#')
        newYExt.flatMap { y -> newXExt.map { x -> Point(x, y) } }
            .map{Pair(it, isEnhancedPointLit(it, imageEnhance))}
            .onEach{ret.points[it.first] = it.second}

        return ret
    }

    private fun IntRange.inflate(num:Int):IntRange {
        val min = this.first-num
        val max = this.last+num
        return min..max
    }

    private fun isEnhancedPointLit(point:Point, imageEnhance:String):Boolean {
        val neighbors = getNeighbors(point)
        val neighborValues = neighbors.map{points.getLight(it)}
        val binaryString = neighborValues
            .joinToString("") { if (it) "1" else "0" }
        val index = binaryString.toInt(2)
        val imageLight = imageEnhance[index]
        return '#' == imageLight
    }

    private fun getNeighbors(point:Point):List<Point> {
        val x = point.first
        val y = point.second
        return listOf(
            Point(x-1, y-1), Point(x, y-1), Point(x+1, y-1),
            Point(x-1, y),   Point(x, y),   Point(x+1, y),
            Point(x-1, y+1), Point(x, y+1), Point(x+1, y+1)
        )
    }

    private fun Map<Point,Boolean>.getLight(point:Point) =
        this.getOrElse(point){defaultSpace}

    companion object {
        fun of(lines:List<String>, defaultSpace:Boolean=false):Image {
            val ret = Image(defaultSpace)
            lines.forEachIndexed { y, line ->
                line.forEachIndexed { x, c ->
                    ret.points[Point(x,y)] = c == '#'
                }
            }
            return ret
        }
    }
}


