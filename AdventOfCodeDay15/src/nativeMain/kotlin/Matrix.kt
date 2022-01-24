
typealias Point = Pair<Int, Int>

class Matrix(sx: Int, sy: Int, val vw:Int, val vh:Int) {
    private val data = IntArray(sx*sy) { 0 }
    private val xStride = 1
    private val yStride = sx

    private val rangeX = 0 until sx*vw
    private val rangeY = 0 until sy*vh

    private val offsetX = rangeX.first
    private val offsetY = rangeY.first
    val lastPoint:Point by lazy { Point(sx*vw-1, sy*vh-1) }

    val height = sy
    val width = sx

    private fun getDataIndex(x: Int, y: Int) =
        (x-offsetX)*xStride + (y-offsetY)*yStride

    private fun contains(x:Int, y:Int) = x in rangeX && y in rangeY
    operator fun contains(t:Point) = contains(t.first, t.second)

    operator fun get(x: Int, y: Int) : Int {
        val dx = x / width
        val rx = x % width
        val dy = y / height
        val ry = y % height

        var ret = 0
        if (contains(rx, ry)) {
            ret = data[getDataIndex(rx, ry)]+dx+dy
            if (ret > 9) ret -= 9
        }

        return ret
    }
    operator fun get(t:Point) = get(t.first, t.second)
    operator fun set(x: Int, y: Int, v: Int) {
        data[getDataIndex(x, y)] = v
    }
    operator fun set(pt:Point, v:Int) = this.set(pt.first, pt.second, v)

    private fun allPoints() = sequence{
        yieldAll(rangeY.flatMap{y -> rangeX.map{x -> Pair(x,y)}})
    }

    fun allPointValues() = allPoints().map {this[it]}

    // print x, y grid for given z
    fun print(): String {
        val ret = mutableListOf<String>()
        ret.add("Start Print Matrix")
        for (y in rangeY) {
            var line = String()
            for (x in rangeX) {
                val v = get(x, y)
                val c = v.toString()
                line += c
            }
            ret.add(line)
        }
        ret.add("End Print Matrix")
        return ret.joinToString("\n")
    }

    companion object {

        fun create(lines: List<String>, vWidth:Int=1, vHeight:Int=1) : Matrix {
            val sx = lines[0].length
            val sy = lines.size
            val matrix = Matrix(sx, sy, vWidth, vHeight)
            lines.forEachIndexed {y, line ->
                line.forEachIndexed {x, c ->
                    matrix[x,y] = c.toString().toInt()
                }
            }

            return matrix
        }
    }
}