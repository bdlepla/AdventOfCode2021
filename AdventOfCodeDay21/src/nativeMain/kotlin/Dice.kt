class Dice {
    private var nextRoll = 1
    private var numRolls = 0

    fun numberOfRolls() = numRolls

    fun roll(num:Int):List<Int> =
        (1..num).map {roll()}

    private fun roll():Int {
        val ret = nextRoll
        nextRoll += 1
        if (nextRoll > 100) nextRoll = 1
        numRolls++
        return ret
    }
}