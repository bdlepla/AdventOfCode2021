
typealias Point = Pair<Int, Int>

class Matrix(sx: Int, sy: Int) {
    private val data = CharArray(sx*sy) { ' ' }
    private val xStride = 1
    private val yStride = sx

    private val rangeX = 0 until sx
    private val rangeY = 0 until sy

    private val offsetX = rangeX.first
    private val offsetY = rangeY.first

    val height = sy
    val width = sx

    private fun getDataIndex(x: Int, y: Int) =
        (x-offsetX)*xStride + (y-offsetY)*yStride

    private fun contains(x:Int, y:Int) = x in rangeX && y in rangeY
    operator fun contains(t:Point) = contains(t.first, t.second)

    operator fun get(x: Int, y: Int) : Char {
        val dx = x / width
        val rx = x % width
        val dy = y / height
        val ry = y % height

        var ret = ' '
        if (contains(rx, ry)) {
            ret = data[getDataIndex(rx, ry)]+dx+dy
        }

        return ret
    }
    operator fun get(t:Point) = get(t.first, t.second)
    operator fun set(x: Int, y: Int, c: Char) {
        data[getDataIndex(x, y)] = c
    }
    operator fun set(pt:Point, c:Char) = this.set(pt.first, pt.second, c)

    private fun allPoints() = sequence{
        yieldAll(rangeY.flatMap{y -> rangeX.map{x -> Pair(x,y)}})
    }

    fun allPointValues() = allPoints().map {this[it]}
    fun allPointsAndValues() = allPoints().map{it to this[it]}

    // print x, y grid for given z
    fun print(): String {
        val ret = mutableListOf<String>()

        for (y in rangeY) {
            var line = String()
            for (x in rangeX) {
                val v = get(x, y)
                val c = v.toString()
                line += c
            }
            ret.add(line)
        }

        return ret.joinToString("\n")
    }

    companion object {

        fun create(lines: List<String>) : Matrix {
            val sx = lines[0].length
            val sy = lines.size
            val matrix = Matrix(sx, sy)
            lines.forEachIndexed {y, line ->
                line.forEachIndexed {x, c ->
                    matrix[x,y] = c
                }
            }

            return matrix
        }
    }
}