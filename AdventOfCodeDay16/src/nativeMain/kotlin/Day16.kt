class Day16(private val lines:List<String>) {
    private fun binaryInputs() = lines.map{ hexToBinary(it).iterator()}

    private fun hexToBinary(input: String): List<Char> =
        input.map { it.digitToInt(16).toString(2).padStart(4, '0') }
            .flatMap { it.toList() }

    fun solvePart1() = binaryInputs()
        .map{BITSPacket.of(it)}
        .flatMap{it.allVersions()}.sumOf{it}

    fun solvePart2() = binaryInputs()
        .map{BITSPacket.of(it)}
        .sumOf{it.value}
}