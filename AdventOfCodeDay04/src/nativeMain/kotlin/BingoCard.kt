class BingoCard(sizeX: Int, private val sizeY: Int) {
    private val data = Array(sizeX * sizeY) {BingoSquare()}
    private val xStride = 1
    private val yStride = sizeX

    internal val rangeX = 0 until sizeX
    internal val rangeY = 0 until sizeY

    private val offsetX = rangeX.first
    private val offsetY = rangeY.first

    val lastX = rangeX.last
    val lastY = rangeY.last

    var won = false

    private fun getDataIndex(x: Int, y: Int) = (x - offsetX) * xStride + (y - offsetY) * yStride
    private fun getXAndY(idx:Int) = Pair(idx % yStride, idx / yStride)


    private fun contains(x: Int, y: Int) = x in rangeX && y in rangeY

    operator fun get(x: Int, y: Int): BingoSquare = data[getDataIndex(x, y)]
    operator fun get(t: Pair<Int, Int>) = get(t.first, t.second)
    operator fun set(x: Int, y: Int, v: BingoSquare) {
        data[getDataIndex(x, y)] = v
    }

    private fun selected() = data.filter{ it.selected }
    private fun unselected() = data.filter{ !it.selected }

    fun sumOfUnselected() = unselected().sumOf{it.number}
    fun play(number: Int):Boolean {
        val (col, row) = findNumber(number)
        if (row == -1) return false
        val square = this[col, row]
        this[col, row] = square.copy(selected = true)
        //println(selected().map{it.number}.joinToString())
        won = rowIsAWinner(row) || colIsAWinner(col)
        return won
    }

    private fun rowIsAWinner(y:Int) = rangeX.all{ x-> this[x, y].selected}
    private fun colIsAWinner(x:Int) = rangeY.all{ y -> this[x,y].selected}
    private fun findNumber(n:Int):Pair<Int, Int> {
        val idx = data.indexOfFirst { it.number == n }
        return if (idx == -1) Pair(-1, -1)
        else getXAndY(idx)
    }

    fun print() = rangeY.joinToString("\n")

    companion object {
        fun create(lines: List<String>) : BingoCard {
            val sy = lines.count()
            val regex = "\\s+".toRegex()
            val sx = lines[0].split(regex).count()
            val matrix = BingoCard(sx, sy)
            val yOffset = matrix.rangeY.first
            val xOffset = matrix.rangeX.first

            lines.forEachIndexed { y, s ->
                //println(s)
                val row = s.trim().split(regex)
                //println("row = '${row.joinToString()}'")
                row.forEachIndexed { x, num ->
                    val v = num.toInt()
                    matrix[x+xOffset, y+yOffset] = BingoSquare(v)
                }
            }
            return matrix
        }
    }
}




