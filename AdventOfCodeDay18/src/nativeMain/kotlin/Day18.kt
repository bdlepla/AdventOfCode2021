class Day18(private val lines:List<String>) {
    fun solvePart1() = add(lines).magnitude()
    fun solvePart2() = generateAllPairs(lines).maxOf{add(it).magnitude()}

    companion object {
        fun generateAllPairs(lines:List<String>):Sequence<Pair<String, String>> =
            sequence {
                (0..lines.size -2).forEach{i1 ->
                    val first = lines.drop(i1).first()
                    lines.drop(i1+1).forEach{second ->
                        yield(Pair(first, second))
                        yield(Pair(second, first))
                    }
                }
            }
        fun add(p:Pair<String, String>) = add(listOf(p.first, p.second))
        fun add(lines: List<String>): SnailfishPair =
            lines.map { SnailfishPair(it) }.reduce { acc, p ->
                val newAcc = (acc + p)
                //println("")
                //println(acc)
                //println("plus")
                //println(p)
                //println("equals")
                //println(newAcc)
                newAcc
            }
    }
}