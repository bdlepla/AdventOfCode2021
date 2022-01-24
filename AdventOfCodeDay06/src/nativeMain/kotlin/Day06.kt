class Day06(private val lines:List<String>) {
    fun solvePart1():Long = simulate(80)
    fun solvePart1a():Long = simulateAlt(80)
    fun solvePart2():Long = simulate(256)
    fun solvePart2a():Long = simulateAlt(256)

    private fun simulateAlt(days:Int):Long {
        val fishPerDay = LongArray(9)
            .apply {
                lines.first().split(",")
                    .map { it.toInt() }.forEach { this[it] += 1L }
            }
        repeat(days) {
            fishPerDay.rotateLeftInPlace()
            fishPerDay[6] += fishPerDay[8]
        }
        return fishPerDay.sum()
    }

    fun simulate(days:Int):Long {
        val initial = lines
            .first()
            .split(",")
            .map{it.toInt()}
            .groupBy{it}
            .map{kv -> kv.key to kv.value.count().toLong()}
            .toMap()
        return doSimulation(days, initial).values.sum()
    }

    private tailrec fun doSimulation(days:Int, fishMap:Map<Int,Long>):Map<Int,Long> {
        if (days == 0) return fishMap
        //println("")
        //println("$days left")
        //println("fishMap is $fishMap")
        val newFishCount = fishMap.getOrElse(0){ 0L }
        //println("newFishCount is $newFishCount")
        val newFish =
            if (newFishCount > 0) listOf(8 to newFishCount).toMap()
            else emptyMap()
        //println("NewFish is $newFish")
        val updatedFish = fishMap
            .map{(if (it.key == 0) 6 else it.key-1) to
                    (if (it.key == 7 || it.key == 0) fishMap.getOrElse(0){0} + fishMap.getOrElse(7){0}
                        else it.value)
            }
            .toMap()
        //println("updatedFish is $updatedFish")
        val keys = updatedFish.keys.toSet() + newFish.keys.toSet()
        val newFishMap = keys.associateWith { (updatedFish.getOrElse(it) { 0 } + newFish.getOrElse(it) { 0 }) }
        //println("newFishMap is $newFishMap")

        return doSimulation(days-1, newFishMap)
    }

    private fun LongArray.rotateLeftInPlace() {
        val leftMost = first()
        this.copyInto(this, startIndex = 1)
        this[this.lastIndex] = leftMost
    }
}