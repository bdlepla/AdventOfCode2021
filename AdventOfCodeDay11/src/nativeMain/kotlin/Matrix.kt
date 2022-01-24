typealias Point = Pair<Int,Int>

class Matrix(sx: Int, sy: Int) {
    private val data = IntArray(sx*sy) { 0 }
    private val xStride = 1
    private val yStride = sx

    internal val rangeX = 0 until sx
    internal val rangeY = 0 until sy

    private val offsetX = rangeX.first
    private val offsetY = rangeY.first

    private fun getDataIndex(x: Int, y: Int) =
        (x-offsetX)*xStride + (y-offsetY)*yStride

    private fun contains(x:Int, y:Int) = x in rangeX && y in rangeY
    operator fun contains(t:Point) = contains(t.first, t.second)

    operator fun get(x: Int, y: Int) : Int {
        var ret = 0
        if (contains(x, y)) {
            ret = data[getDataIndex(x, y)]
        }
        return ret
    }
    operator fun get(t:Point) = get(t.first, t.second)
    operator fun set(x: Int, y: Int, v: Int) {
        data[getDataIndex(x, y)] = v
    }
    operator fun set(pt:Point, v:Int) = this.set(pt.first, pt.second, v)

    internal fun allPoints() = sequence{
        yieldAll(rangeY.flatMap{y -> rangeX.map{x -> Pair(x,y)}})
    }

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
            val sy = lines.count()
            val sx = lines[0].length
            val matrix = Matrix(sx, sy)
            val yOffset = matrix.rangeY.first
            val xOffset = matrix.rangeX.first
            lines.forEachIndexed { y, s ->
                s.forEachIndexed { x, c ->
                    matrix[x+xOffset, y+yOffset] = c.toString().toInt()
                }
            }
            return matrix
        }
    }
}