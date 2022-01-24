class Matrix(sx: Int, sy: Int) {
    private val data = BooleanArray(sx*sy) { false }
    private val xStride = 1
    private val yStride = sx

    internal val rangeX = 0 until sx
    internal val rangeY = 0 until sy

    private val offsetX = rangeX.first
    private val offsetY = rangeY.first

    val height = sy
    val width = sx

    private fun getDataIndex(x: Int, y: Int) =
        (x-offsetX)*xStride + (y-offsetY)*yStride

    private fun contains(x:Int, y:Int) = x in rangeX && y in rangeY
    operator fun contains(t:Point) = contains(t.first, t.second)

    operator fun get(x: Int, y: Int) : Boolean {
        var ret = false
        if (contains(x, y)) {
            ret = data[getDataIndex(x, y)]
        }
        return ret
    }
    operator fun get(t:Point) = get(t.first, t.second)
    operator fun set(x: Int, y: Int, v: Boolean) {
        data[getDataIndex(x, y)] = v
    }
    operator fun set(pt:Point, v:Boolean) = this.set(pt.first, pt.second, v)

    internal fun allPoints() = sequence{
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
                val c = if (v) '#' else '.'
                line += c
            }
            ret.add(line)
        }
        ret.add("End Print Matrix")
        return ret.joinToString("\n")
    }

    companion object {

        fun create(points: List<Point>) : Matrix {
            val sx = points.maxOf { it.first }+1
            val sy = points.maxOf { it.second }+1
            val matrix = Matrix(sx, sy)
            points.forEach { matrix[it] = true}
            return matrix
        }
    }
}