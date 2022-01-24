class Player(private val playerNumber:Int,
             startingPosition:Int, // 1 based (1 - 10)
             private val dice:Dice) {
    var runningScore = 0
    var currentPosition = startingPosition - 1 // 0 based (0 - 9) so mod works
    fun playTurn():Int {
        val rolls = dice.roll(3)
        val moveSpaces = rolls.sum()
        currentPosition = (currentPosition + moveSpaces).mod(10)
        runningScore += currentPosition + 1 // running score based on 1 -base (1 - 10)
        //val rollString = rolls.joinToString("+")
        //println("Player $playerNumber rolls $rollString and moves to space ${currentPosition+1} for a total score of $runningScore.")
        return score()
    }

    fun score() = runningScore
}