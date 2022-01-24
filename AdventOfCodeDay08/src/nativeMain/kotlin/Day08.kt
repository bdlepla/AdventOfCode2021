class Day08(private val lines:List<String>) {
    fun solvePart1() =
    //.onEach{println(it.second)}
        //.onEach{println(it)}
        dataFromLines(lines)
            .flatMap { it.second.trim().split(" ") }
            .count { it.length in setOf(2, 3, 4, 7) }

    fun solvePart2():Int = dataFromLines(lines).sumOf { decodeLine(it) }

    private fun decodeLine(data:Pair<String,String>):Int {
        val decodedMap = decodeInput(data.first)
        return decodeOutput(decodedMap, data.second)
    }

    private fun decodeInput(input: String):Map<Char,Signal> {
        val inputByLength = input
            .split(" ")
            .groupBy{it.length}

        val segments =  inputByLength
            .map{p -> p.key to p.value.map{v -> v.toSet()}.reduce{a,b->a.intersect(b)}}
            .toMap()

        //println(input)
        val upperSegment = segments[3]!! - segments[2]!!
        //println("Upper is ${upperSegment.first()}")
        val bottomSegment = segments[5]!! - upperSegment - segments[4]!!
        //println("Bottom is ${bottomSegment.first()}")
        val middleSegment = segments[5]!! - upperSegment - bottomSegment
        //println("Middle is ${middleSegment.first()}")
        val upperRightSegment = segments[2]!! - segments[6]!!
        //println("UpperRight is ${upperRightSegment.first()}")
        val lowerRightSegment = segments[2]!! - upperRightSegment
        //println("LowerRight is ${lowerRightSegment.first()}")
        val upperLeftSegment = segments[6]!! - upperSegment - bottomSegment - lowerRightSegment
        //println("UpperLeft is ${upperLeftSegment.first()}")
        val lowerLeftSegment = segments[7]!! - segments[6]!! - segments[4]!!
        //println("LowerLeft is ${lowerLeftSegment.first()}")

        return mapOf(
            upperSegment.first() to Signal.UPPER ,
            middleSegment.first() to Signal.MIDDLE,
            bottomSegment.first() to Signal.BOTTOM,
            upperLeftSegment.first() to Signal.UPPERLEFT,
            lowerLeftSegment.first() to Signal.LOWERLEFT,
            upperRightSegment.first() to Signal.UPPERRIGHT,
            lowerRightSegment.first() to Signal.LOWERRIGHT
        )
    }

    private val signalsToDigit = mapOf(
        setOf(Signal.UPPER, Signal.UPPERLEFT, Signal.UPPERRIGHT, Signal.LOWERLEFT, Signal.LOWERRIGHT, Signal.BOTTOM) to '0',
        setOf(Signal.UPPER, Signal.MIDDLE, Signal.BOTTOM, Signal.UPPERRIGHT, Signal.LOWERLEFT) to '2',
        setOf(Signal.UPPER, Signal.MIDDLE, Signal.BOTTOM, Signal.UPPERRIGHT, Signal.LOWERRIGHT) to '3',
        setOf(Signal.UPPER, Signal.MIDDLE, Signal.BOTTOM, Signal.UPPERLEFT, Signal.LOWERRIGHT) to '5',
        setOf(Signal.UPPER, Signal.UPPERLEFT, Signal.MIDDLE, Signal.LOWERLEFT, Signal.LOWERRIGHT, Signal.BOTTOM) to '6',
        setOf(Signal.UPPER, Signal.UPPERLEFT, Signal.UPPERRIGHT, Signal.MIDDLE, Signal.LOWERRIGHT, Signal.BOTTOM) to '9'
    )

    private fun decodeOutput(input:Map<Char,Signal>, outputDigits:String):Int {
        //println(input)
        val outputs = outputDigits.split(" ")
        //println(outputs.joinToString(","))
        val digits:String = outputs.map { output ->
            when(output.length) {
                2 -> '1'
                3 -> '7'
                4 -> '4'
                7 -> '8'
                else ->  {
                    val signals = output.map{input[it]}.toSet()
                    //println(signals)
                    signalsToDigit[signals]!!
                }
            }
        }
            //.onEach { println(it) }
            .joinToString("")
        return digits.toInt()
    }

    private fun dataFromLines(lines:List<String>):List<Pair<String, String>> =
        lines.map{it.substringBefore(" | ") to it.substringAfter(" | ")}

}