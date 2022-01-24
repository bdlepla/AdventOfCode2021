class Burrow(lines:List<String>) {
    val data = parseData(lines)
    val validSpaces = data
        .allPointsAndValues()
        .filter{it.second !in " #"}
        .map{it.first}

    fun print() = data.print()
    fun performStep() = {


    }

    fun parseData(lines: List<String>): Matrix = Matrix.create(lines)
    
}