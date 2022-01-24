private data class GameState(
    val player1: PlayerState,
    val player2: PlayerState,
    val player1Turn: Boolean = true
) {
    fun next(die: Int): GameState =
        GameState(
            if (player1Turn) player1.next(die) else player1,
            if (!player1Turn) player2.next(die) else player2,
            player1Turn = !player1Turn
        )

    fun isWinner(scoreNeeded: Int = 1000): Boolean =
        player1.score >= scoreNeeded || player2.score >= scoreNeeded

    fun minScore(): Int =
        minOf(player1.score, player2.score)
}

private data class PlayerState(val place: Int, val score: Int = 0) {
    fun next(die: Int): PlayerState {
        val nextPlace = (place + die - 1) % 10 + 1

        return PlayerState(
            place = nextPlace,
            score = score + nextPlace
        )
    }
}


class Day21(private val lines:List<String>) {
    fun solvePart1() = solve()
    fun solvePart2() = playQuantum().max()

    private fun solve():Long {
        val (p1Start, p2Start) = parseInput(lines)
        val dice = Dice()
        val player1 = Player(1, p1Start, dice)
        val player2 = Player(2, p2Start, dice)
        while (true) {
            if (player1.playTurn() > 999) break
            if (player2.playTurn() > 999) break
        }
        val losingScore = if (player1.score() > 999) player2.score() else player1.score()
        val numberDiceRolls = dice.numberOfRolls().toLong()
        val ret = numberDiceRolls * losingScore
        //println("losing score $losingScore * dice rolls $numberDiceRolls = $ret")
        return ret
    }

    private fun parseInput(lines:List<String>):Pair<Int,Int> {
        val start1 = lines[0].substringAfterLast(" ").toInt()
        val start2 = lines[1].substringAfterLast(" ").toInt()
        return start1 to start2
    }

    private val quantumDieFrequency: Map<Int, Long> =
        mapOf(3 to 1, 4 to 3, 5 to 6, 6 to 7, 7 to 6, 8 to 3, 9 to 1)

    private val stateMemory: MutableMap<GameState, WinCounts> = mutableMapOf()
    private val player1Start = parseInput(lines).first
    private val player2Start = parseInput(lines).second
    private val initialGameState = GameState(PlayerState(player1Start), PlayerState(player2Start))

    private fun playQuantum(state: GameState = initialGameState): WinCounts =
        when {
            state.isWinner(21) ->
                if (state.player1.score > state.player2.score) WinCounts(1, 0) else WinCounts(0, 1)
            state in stateMemory ->
                stateMemory.getValue(state)
            else ->
                quantumDieFrequency.map { (die, frequency) ->
                    playQuantum(state.next(die)) * frequency
                }.reduce { a, b -> a + b }.also { stateMemory[state] = it }
        }


    private class WinCounts(val player1: Long, val player2: Long) {
        operator fun plus(other: WinCounts): WinCounts =
            WinCounts(player1 + other.player1, player2 + other.player2)

        operator fun times(other: Long): WinCounts =
            WinCounts(player1 * other, player2 * other)

        fun max(): Long =
            maxOf(player1, player2)




    }
}