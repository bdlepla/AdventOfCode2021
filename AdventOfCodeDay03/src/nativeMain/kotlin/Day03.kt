class Day03(private val lines:List<String>) {
    fun solvePart1():Long {
        val linesCount = lines.count()
        val halfCount = linesCount/2

        val gammaString = lines[0].indices.map { idx ->
            val onesCount = lines.count{it[idx] == '1'}
            val onesGreater = onesCount > halfCount
            if (onesGreater) '1' else '0'
        }.joinToString("")

        val epsilonString = gammaString
            .map{ if (it == '1') '0' else '1'}
            .joinToString("")

        val gamma = gammaString.toLong(2)
        val epsilon = epsilonString.toLong(2)
        return gamma * epsilon
    }

    fun solvePart2(): Long {
        val oxygen = findMost(lines, 0)
        println("Oxygen = $oxygen")
        val co2 = findLeast(lines, 0)
        println("CO2 = $co2")
        return oxygen * co2
    }

    private fun findMost(l:List<String>, idx:Int):Long {
        if (l.count() == 1) return l.first().toLong(2)
        val onesCount = l.count{it[idx] == '1'}
        val zeroCount = l.count() - onesCount
        val onesGreater = onesCount >= zeroCount
        val which = if (onesGreater) '1' else '0'
        val newList = l.filter{it[idx] == which}
        //println(newList.joinToString(","))
        return findMost(newList, idx+1)
    }

    private fun findLeast(l:List<String>, idx:Int):Long {
        if (l.count() == 1) return l.first().toLong(2)
        val onesCount = l.count{it[idx] == '1'}
        val zeroCount = l.count() - onesCount
        val zerosLesser = zeroCount <= onesCount
        val which = if (zerosLesser) '0' else '1'
        val newList = l.filter{it[idx] == which}
        //println(newList.joinToString(","))
        return findLeast(newList, idx+1)
    }
}