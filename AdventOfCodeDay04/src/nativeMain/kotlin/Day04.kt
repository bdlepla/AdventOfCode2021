class Day04(private val lines:List<String>) {
    private val bingoNumbers = createNumbers(lines.first())

    private fun createNumbers(line: String)=line.split(",").map { it.toInt() }

    private fun createBingoCards(lines: List<String>): List<BingoCard> {
        val input = lines.joinToString("\n").split("\n\n")
        return input.map { BingoCard.create(it.split("\n")) }
    }

    fun solvePart1():Long {
        val filteredLines = lines.dropWhile{it.isNotBlank()}.dropWhile{it.isBlank()}
        val bingoCards = createBingoCards(filteredLines)

        // loop through bingo numbers
        //  apply number to each card
        //  after apply, does the card have a winner?
        //  if so, then the results
        val winner = bingoNumbers.asSequence().firstNotNullOf{ number ->
            //println("playing bingo number $number")
            bingoCards.firstOrNull { card ->card.play(number)}
                ?.let {Pair(number, it.sumOfUnselected())}
        }
        return winner.first.toLong() * winner.second
    }

    fun solvePart2():Long {
        val filteredLines = lines.dropWhile{it.isNotBlank()}.dropWhile{it.isBlank()}
        val bingoCards = createBingoCards(filteredLines)
        // loop through bingo numbers
        //  apply number to each card
        //  after apply, does the card have a winner?
        //  if so, then the results
        val winner = bingoNumbers.asSequence().mapNotNull{ number ->
            val bingoCardInPlay = bingoCards.filter{!it.won}
            //println("playing bingo number $number")
            val anyWinners = bingoCardInPlay
                .mapNotNull { card ->
                    if (card.play(number) && bingoCardInPlay.count() == 1) {
                        val sum = card.sumOfUnselected()
                        //println("We have a winner $number * $sum")
                        Pair(number, sum)
                    } else null
                }
            if (anyWinners.isNotEmpty()) anyWinners.first() else null
        }.first()

        return winner.first.toLong() * winner.second
    }
}