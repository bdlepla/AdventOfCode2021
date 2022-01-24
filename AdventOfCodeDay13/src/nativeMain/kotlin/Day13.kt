class Day13(private val lines:List<String>) {

    fun solvePart1():Int {
        val (points, folds) = parseInput(lines)
        val matrix = Matrix.create(points)
        val newMatrix = folds.take(1).foldMatrix(matrix)
        return newMatrix.allPointValues().count { it }
    }

    fun solvePart2():String {
        val (points, folds) = parseInput(lines)
        val matrix = Matrix.create(points)
        val newMatrix = folds.foldMatrix(matrix)
        return newMatrix.print()
    }

    private fun List<Fold>.foldMatrix(matrix:Matrix):Matrix {
        return this.fold(matrix) { m, f ->
            if (f.which == 'y'){
                //println("in fold, current matrix:")
               // println(m.print())
                val (m1, m2) = m.splitAtY(f.where)
                //println("splitting at y=${f.where}")
                //println(m1.print())
                //println(m2.print())
                val flippedM2 = m2.flipUpDown()
                //println("m2 flipped up to down")
                //println(flippedM2.print())
                val (max1, max2) = if (m1.height < m2.height) Pair(flippedM2,m1) else Pair(m1,flippedM2)
                val ret = max1.mergeFromBottom(max2)
                //println(ret.print())
                ret
            } else {
                //println("in fold, current matrix:")
                //println(m.print())
                val (m1,m2) = m.splitAtX(f.where)
                //println("splitting at x=${f.where}")
                //println(m1.print())
                //println(m2.print())
                val flippedM2 = m2.flipRightLeft()
                //println("m2 flipped left to right")
                //println(flippedM2.print())
                val (max1, max2) = if (m1.width < m2.width) Pair(flippedM2,m1) else Pair(m1,flippedM2)
                val ret = max1.mergeFromLeft(max2)
                //println(ret.print())
                ret
            }
        }
    }

    private fun Matrix.splitAtY(y:Int):Pair<Matrix, Matrix> {
        val mTop = Matrix(width, y)
        (0 until y).forEach { py ->
            (0 until width).forEach { px ->
                mTop[px,py] = this[px,py]
            }
        }

        val mBottom = Matrix(width, height - y-1)
        (y+1 until height).forEachIndexed { idy, py ->
            (0 until width).forEach { px ->
                mBottom[px,idy] = this[px,py]
            }
        }

        return Pair(mTop, mBottom)
    }

    private fun Matrix.splitAtX(x:Int):Pair<Matrix,Matrix> {
        val mLeft = Matrix(x, height)
        (0 until height).forEach { py ->
            (0 until x).forEach { px ->
                mLeft[px,py] = this[px,py]
            }
        }

        val mRight = Matrix(width - x-1, height)
        (0 until height).forEach { py ->
            (x+1 until width).forEachIndexed {idx, px ->
                mRight[idx,py] = this[px,py]
            }
        }

        return Pair(mLeft, mRight)
    }

    private fun Matrix.flipUpDown():Matrix {
        val ret = Matrix(width, height)
        (height-1 downTo 0).forEachIndexed { idy, py ->
            (0 until width).forEach { px ->
                ret[px,idy] = this[px,py]
            }
        }
        return ret
    }

    private fun Matrix.flipRightLeft():Matrix {
        val ret = Matrix(width, height)
        (0 until height).forEach { py ->
            (width-1 downTo 0).forEachIndexed { idx, px ->
                ret[idx,py] = this[px,py]
            }
        }
        return ret
    }

    private fun Matrix.mergeFromBottom(other:Matrix):Matrix {
        // this is always bigger height (or same); other is always smaller (or same)
        val offset = this.height - other.height
        (0 until other.height).forEach { y ->
            (0 until other.width).forEach { x ->
                this[x,y+offset] = this[x,y+offset] ||  other[x,y]
            }
        }
        return this
    }

    private fun Matrix.mergeFromLeft(other:Matrix):Matrix {
        // this is always bigger width (or same); other is always smaller (or same)
        (0 until height).forEach { y ->
            (0 until other.width).forEach { x ->
                this[x,y] = this[x,y] ||  other[x,y]
            }
        }
        return this
    }

    private fun parseInput(lines:List<String>):Pair<List<Point>, List<Fold>> {
        val points = lines.takeWhile{it.isNotBlank()}
        val pointList = points
            .map{it.split(',')}
            .map{Point(it[0].toInt(), it[1].toInt())}
        val folds = lines.dropWhile{it.isNotBlank()}.dropWhile{it.isBlank()}
        val foldList = folds
            .map{it.substringAfter("fold along ")}
            .map{it.split('=')}
            .map{Fold(it[0][0], it[1].toInt())}
        return pointList to foldList
    }
}