fun Iterator<Char>.next(size: Int): String =
    (1..size).map { next() }.joinToString("")

fun Iterator<Char>.nextInt(size: Int): Int =
    next(size).toInt(2)

fun Iterator<Char>.nextUntilFirst(size: Int, stopCondition: (String) -> Boolean): List<String> {
    val output = mutableListOf<String>()
    do {
        val readValue = next(size)
        output.add(readValue)
    } while (!stopCondition(readValue))
    return output
}


fun <T> Iterator<Char>.executeUntilEmpty(function: (Iterator<Char>) -> T): List<T> {
    val output = mutableListOf<T>()
    while (this.hasNext()) {
        output.add(function(this))
    }
    return output
}

fun <T> Iterable<T>.productOf(selector: (T) -> kotlin.Long):Long =
    this.fold(1L){acc, l -> acc * selector(l)}
