import kotlin.math.absoluteValue
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.sqrt

typealias Point = Pair<Int,Int>
typealias TargetArea = Pair<IntRange, IntRange>
typealias Velocity = Pair<Int, Int>
typealias Path = List<Point>

class Day17(private val lines:List<String>) {

    fun solvePart1() = solve(processInput(lines))
        .maxOf{path -> path.maxOf{pt -> pt.second}}
    fun solvePart2() = solve(processInput(lines)).count()

    private fun solve(target:TargetArea):Sequence<Path> =
        // to solve one path, given starting point of 0,0 and some velocity vx,vy
        // do apply point.next(velocity); velocity.next() and collect points
        // until either in target or after target. If in target, return points
        // otherwise, return empty list.
        target.generateStartingVelocities().map{ velocity ->
            Point(0,0).generatePath(velocity, target)
        }
            .filter{path -> path.isNotEmpty()}
            //.onEach{println(it)}

    private fun processInput(inputLines:List<String>):Pair<IntRange,IntRange> {
        val theOnlyLine = inputLines.first()
        val splits = theOnlyLine.split(": x=", ", y=")
        val xString = splits[1]
        val xSplits = xString.split("..")
        val xRet = xSplits[0].toInt() .. xSplits[1].toInt()
        val yString = splits[2]
        val ySplits = yString.split("..")
        val yRet = ySplits[0].toInt() .. ySplits[1].toInt()
        return Pair(xRet,yRet)
    }

    // helpful extensions
    operator fun TargetArea.contains(pt:Point):Boolean =
        this.first.contains(pt.first) &&
            this.second.contains(pt.second)

    private fun TargetArea.isAfter(pt:Point):Boolean =
        pt.first > this.first.last || pt.second < this.second.first

    fun Point.next(velocity:Velocity) =
        Point(this.first + velocity.first, this.second+velocity.second)

    fun Velocity.next() = Velocity(
        when {
            this.first > 0 -> this.first-1
            this.first < 0 -> this.first+1
            else -> this.first
        }, this.second-1)

    private fun Point.generatePath(velocity:Velocity, target:TargetArea):Path{
        var workingPoint = this
        var workingVelocity = velocity
        val ret = mutableListOf(workingPoint)
        do {
            workingPoint = workingPoint.next(workingVelocity)
            workingVelocity = workingVelocity.next()
            ret.add(workingPoint)
        } while (workingPoint !in target && !target.isAfter(workingPoint))
        return if (target.isAfter(workingPoint)) emptyList() else ret
    }

    // given a target area, generate a sequence of velocities
    //
    // I know that it must be > 0,0
    // and < TargetArea.lasX+1 and > TargetArea.lastY-1
    // so, that is my pool of initial velocities
    private fun TargetArea.generateStartingVelocities() =
        sequence {
            val target = this@generateStartingVelocities
//            val minXStep = ceil(calculateXStep(target.first.first))
//                .toInt()/2
//            val maxXStep = floor(calculateXStep(target.first.last))
//                .toInt()*2
//            val minYStep = ceil(calculateYStep(minXStep,target.second.first))
//                .toInt()/2
//            val maxYStep = floor(calculateYStep(maxXStep, target.second.last))
//                .toInt()*2
            val xRange = 0..target.first.last
            val yRange = target.second.first..target.second.first.absoluteValue

            (yRange).forEach { y ->
                (xRange).forEach { x ->
                    yield(Velocity(x,y))
                }
            }
        }

    // using the Power series for addition (1+2+3+...)
    // = n*(n+1)/2
    // and the quadratic equation to solve for given distance X
    // in integer number of Steps.
    private fun calculateXStep(xDistance:Int)=
        (sqrt(1+8*xDistance.toDouble())/2)-1

    private fun calculateYStep(step:Int, yDistance:Int):Double
        // we are starting y at 0. We have some target y at ty
        // we are going up to some maxY which takes some steps s1. Then
        // we are going from maxY to tt in the remaining steps.
        // So, we have two power series
         = (2.0*yDistance+step*(step+1))/(2*(1+step))



}