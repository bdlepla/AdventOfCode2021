class Day02(lines:List<String>) {
    private val data = lines.map {
        //println(it)
        val parts = it.split(" ")
        val direction = parts[0]
        val amount = parts[1].toLong()
        when (direction) {
            "forward" -> Pair(amount, 0L)
            "down" -> Pair(0L, amount)
            else -> Pair(0L, -amount)
        }
    }

    fun solvePart1() = data.reduce{ x, y -> x + y }.multiply()
    fun solvePart2():Long {
        var aim = 0L
        return data.map {
            val forward = it.first
            val downOrUp = it.second
            aim += downOrUp
            if (forward != 0L) Pair(forward, aim * forward)
            else Pair(0L, 0L)
        }.reduce { x, y -> x + y }.multiply()
    }

    operator fun Pair<Long, Long>.plus(other:Pair<Long, Long>):Pair<Long,Long>
         = Pair(this.first+other.first, this.second+other.second)

    private fun Pair<Long, Long>.multiply() = this.first * this.second
}