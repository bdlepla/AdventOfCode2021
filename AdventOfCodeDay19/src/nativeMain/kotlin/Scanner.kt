import kotlin.math.absoluteValue

data class Point3d(val x: Int, val y: Int, val z: Int) {

    operator fun plus(other: Point3d): Point3d =
        Point3d(x + other.x, y + other.y, z + other.z)

    operator fun minus(other: Point3d): Point3d =
        Point3d(x - other.x, y - other.y, z - other.z)

    infix fun distanceTo(other: Point3d): Int =
        (this.x - other.x).absoluteValue + (this.y - other.y).absoluteValue + (this.z - other.z).absoluteValue

    fun face(facing: Int): Point3d =
        when (facing) {
            0 -> this
            1 -> Point3d(x, -y, -z)
            2 -> Point3d(x, -z, y)
            3 -> Point3d(-y, -z, x)
            4 -> Point3d(y, -z, -x)
            5 -> Point3d(-x, -z, -y)
            else -> error("Invalid facing")
        }

    fun rotate(rotating: Int): Point3d =
        when (rotating) {
            0 -> this
            1 -> Point3d(-y, x, z)
            2 -> Point3d(-x, -y, z)
            3 -> Point3d(y, -x, z)
            else -> error("Invalid rotation")
        }

    companion object {
        fun of(rawInput: String): Point3d =
            rawInput.split(",").let { part ->
                Point3d(part[0].toInt(), part[1].toInt(), part[2].toInt())
            }
    }
}

fun <T> Collection<T>.pairs(): List<Pair<T,T>> =
    this.flatMapIndexed { index, a ->
        this.drop(index).map { b -> a to b }
    }
