class Day20(lines:List<String>) {
    val imageEnhanceString = lines.takeWhile{it.isNotEmpty()}.joinToString("")
    val image = Image.of(lines.dropWhile{it.isNotEmpty()}.drop(1))
    fun solvePart1() = solve(2).countLights()
    fun solvePart2() = solve(50).countLights()

    fun solve(cycles:Int) =
        (1..cycles).fold(image){acc,_ -> acc.transform(imageEnhanceString)}

}