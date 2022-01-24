class Day10(private val lines:List<String>) {

    fun solvePart1() = lines.map { it.decode() }.sumOf { it.score() }

    fun solvePart2() = lines.filter { it.decode().score() == 0}
        .map {it.fixLine()}.sorted().middle()

    private fun <T> Iterable<T>.middle() = this.drop(this.count()/2).first()

    private fun String.fixLine():Long {
        val stack = Stack<Char>()
        this.forEach {
            if (it.isOpen()) {
                stack.push(it)
            } else {
                stack.pop()
            }
        }
        return stack.asSequence().map{it.provideClose()}.score2()
    }

    private fun Sequence<Char>.score2()=
        this.fold(0L){a,b -> a*5 + b.score2()}

    private fun Char.score2():Int =
        when (this) {
            ')' -> 1
            ']' -> 2
            '}' -> 3
            '>' -> 4
            else -> 0
        }

    private fun String.decode(): Char {
        val stack = Stack<Char>()
        this.forEach {
            if (it.isOpen()) {
                stack.push(it)
            } else {
                if (stack.isEmpty()) return it
                if (!it.matchesOpen(stack.peek())) return it
                stack.pop()
            }
        }
        return '0' // no error
    }

    private fun Char.provideClose() =
        when (this) {
            '{' -> '}'
            '[' -> ']'
            '(' -> ')'
            '<' -> '>'
            else -> '0'
        }

    private fun Char.matchesOpen(open: Char) = this == open.provideClose()

    private fun Char.isOpen() = this in setOf('(', '{', '<', '[')

    private fun Char.score() =
        when (this) {
            '}' -> 1197
            ']' -> 57
            '>' -> 25137
            ')' -> 3
            else -> 0
        }
}