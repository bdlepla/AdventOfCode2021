sealed class BITSPacket(val version: Int) {
    abstract val value: Long

    companion object {
        fun of(input: Iterator<Char>): BITSPacket {
            val version = input.nextInt(3)
            return when (val packetType = input.nextInt(3)) {
                4 -> BITSLiteral.of(version, input)
                else -> BITSOperator.of(version, packetType, input)
            }
        }
    }

    abstract fun allVersions(): List<Int>
}

private class BITSLiteral(version: Int, override val value: Long) : BITSPacket(version) {
    override fun allVersions(): List<Int> = listOf(version)

    companion object {
        fun of(version: Int, input: Iterator<Char>): BITSLiteral =
            BITSLiteral(version, parseLiteralValue(input))

        private fun parseLiteralValue(input: Iterator<Char>): Long =
            input.nextUntilFirst(5) {
                it.startsWith('0')
            }.joinToString("") { it.drop(1) }.toLong(2)
    }
}

private class BITSOperator(version: Int, type: Int, val subPackets: List<BITSPacket>) : BITSPacket(version) {
    override fun allVersions(): List<Int> =
        listOf(version) + subPackets.flatMap { it.allVersions() }

    override val value: Long =
        when (type) {
            0 -> subPackets.sumOf{it.value}
            1 -> subPackets.productOf{it.value}
            2 -> subPackets.minOf{it.value}
            3 -> subPackets.maxOf{it.value}
            5 -> if (subPackets[0].value > subPackets[1].value) 1 else 0
            6 -> if (subPackets[0].value < subPackets[1].value) 1 else 0
            7 -> if (subPackets[0].value == subPackets[1].value) 1 else 0
            else -> -1L
        }

    companion object {
        fun of(version: Int, type: Int, input: Iterator<Char>): BITSOperator {
            return when (input.nextInt(1)) { // Length Type
                0 -> {
                    val subPacketLength = input.nextInt(15)
                    val subPacketIterator = input.next(subPacketLength).iterator()
                    val subPackets = subPacketIterator.executeUntilEmpty { of(it) }
                    BITSOperator(version, type, subPackets)
                }
                1 -> {
                    val numberOfPackets = input.nextInt(11)
                    val subPackets = (1..numberOfPackets).map { of(input) }
                    BITSOperator(version, type, subPackets)
                }
                else -> error("Invalid Operator length type")
            }
        }
    }
}