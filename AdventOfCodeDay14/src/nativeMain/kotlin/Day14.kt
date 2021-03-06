class Day14(lines:List<String>) {
    private val lastChar = lines.first().last()
    private val template: Map<String, Long> = parseTemplate(lines.first())
    private val rules: Map<String, Char> = parseRules(lines)

    private fun parseTemplate(input: String): Map<String, Long> =
        input
            .windowed(2)
            .groupingBy { it }
            .eachCount().mapValues { it.value.toLong() }

    private fun parseRules(input: List<String>): Map<String, Char> =
        input.drop(2).associate {
            it.substring(0..1) to it[6]
        }

    fun solvePart1() = solve(10)

    fun solvePart2() = solve(40)

    private fun solve(iterations: Int): Long =
        (0 until iterations)
            .fold(template) { polymer, _ -> polymer.react() }
            .byCharFrequency()
            .values
            .sorted()
            .let { it.last() - it.first() }

    private fun Map<String, Long>.react(): Map<String, Long> =
        buildMap {
            this@react.forEach { (pair, count) ->
                val inserted = rules.getValue(pair)
                plus("${pair.first()}$inserted", count)
                plus("$inserted${pair.last()}", count)
            }
        }


    private fun <T> MutableMap<T, Long>.plus(key: T, amount: Long) {
        this[key] = this.getOrDefault(key, 0L) + amount
    }

    private fun <T> Map<T, Long>.getOrDefault(key:T, amount: Long) =
        this.getOrElse(key){amount}

    private fun Map<String, Long>.byCharFrequency(): Map<Char, Long> =
        this
            .map { it.key.first() to it.value }
            .groupBy({ it.first }, { it.second })
            .mapValues { it.value.sum() + if (it.key == lastChar) 1 else 0 }

}