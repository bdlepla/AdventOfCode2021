private val Int.isOdd: Boolean
    get() {return this.rem(2) == 1}

class SnailfishPair(private val root:Node) {
    constructor(line:String):this(Node.of(StringIterator(line)))

    open class Node(var left:Node?=null,
                    var right:Node?=null) {
        open var value = 0
        open val isNode = true
        var parent:Node? = null
        override fun toString(): String = "[$left,$right]"
         fun countLevels():Int =
             if (parent == null) 0
            else 1 + parent!!.countLevels()

        fun allChildren():List<Node> = leftChildren() + listOf(this) + rightChildren()
        private fun leftChildren() = left?.allChildren() ?: listOf()
        private fun rightChildren() = right?.allChildren() ?: listOf()

        open fun magnitude():Long =
            (3L * (if (left != null) left!!.magnitude() else 0L)) +
            (2L * (if (right != null) right!!.magnitude() else 0L))

        companion object{
            fun of(iter:StringIterator):Node{
                val c = iter.next()
                if (c != '[') throw Exception("Expecting '[' but received $c")
                return parseNode(iter)
            }
            private fun parseNode(iter:StringIterator):Node {
                // call this if just consumed '['
                val left = ofLeft(iter)
                val right = ofRight(iter)
                val node = Node(left, right)
                left.parent = node
                right.parent = node
                return node
            }
            private fun ofLeft(iter:StringIterator):Node {
                val ret = when (val c = iter.next()) {
                    '[' -> parseNode(iter)
                    else -> {
                        iter.putBack(c)
                        NodeLiteral(iter.takeUntil(',').toInt())
                    }
                }
                val c2 = iter.next()
                if (c2 != ',') throw Exception("expecting ',' but received $c2")
                return ret
            }
            private fun ofRight(iter:StringIterator):Node {
                val ret = when(val c = iter.next()) {
                    '[' -> parseNode(iter)
                    else -> {
                        iter.putBack(c)
                        NodeLiteral(iter.takeUntil(']').toInt())
                    }
                }
                val c2 = iter.next()
                if (c2 != ']') throw Exception("expecting ',' but received $c2")
                return ret
            }
        }
    }

    class NodeLiteral(override var value:Int):Node(){
        override fun toString(): String = "$value"
        override val isNode = false
        override fun magnitude() = value.toLong()
    }

    override fun toString() = root.toString()

    operator fun plus(other:SnailfishPair):SnailfishPair {
        val newNode = Node(this.root, other.root)
        this.root.parent = newNode
        other.root.parent = newNode
        val beforeReduce = SnailfishPair(newNode)

        //println("")
        //println(this.root)
        //println("plus")
        //println(other.root)
        //println("equals")
        //println(beforeReduce)
        //println("reducing")
        val ret = beforeReduce.reduce()
        //println("after reducing")
        //println(ret)
        return ret
    }


    private fun findNodesToSplit():List<Node> {
        val children = root.allChildren()
        val regular = children.filter { !it.isNode }
        val ret = regular.filter{it.value > 9}
        //println("nodes to split = $ret")
        return ret
    }

    fun split():Boolean {
        val nodeToSplit = findNodesToSplit().firstOrNull()
        if (nodeToSplit != null)
        {
            val leftVal = nodeToSplit.value/2
            val leftNode = NodeLiteral(leftVal)
            val rightVal = leftVal + if (nodeToSplit.value.isOdd) 1 else 0
            val rightNode = NodeLiteral(rightVal)

            val newNode = Node(leftNode, rightNode)
            rightNode.parent = newNode
            leftNode.parent = newNode
            newNode.parent = nodeToSplit.parent
            if (nodeToSplit == nodeToSplit.parent!!.left) {
                newNode.parent!!.left = newNode
            } else {
                newNode.parent!!.right = newNode
            }
            return true
        }
        return false
    }

    fun reduce():SnailfishPair {
        do {
            val didSomething = explode() || split()
        } while (didSomething)
        return this
    }

    private fun findNodesToExplode():List<Node> {
        val children = root.allChildren()
        //val literalsWithLevel = children.map{Pair(it, it.countLevels())}
        //literalsWithLevel.onEach{println("level ${it.second} for ${it.first}")}
        val literals = children.filter { !it.isNode }
        val literalsWithLevel4 = literals.filter { it.countLevels() > 4 }
        val ret = literalsWithLevel4.mapNotNull{it.parent}.distinct()
        //println("nodes to explode = $ret")
        return ret
    }

    private fun findNextNodeToRight(node:Node):Node? {
        var thisNode = node
        while ((thisNode.parent != null)) {
            if (thisNode.parent!!.right != thisNode) break
            thisNode = thisNode.parent!!
        }
        if (thisNode.parent == null) return null
        thisNode = thisNode.parent!!.right!!
        while (thisNode.isNode) thisNode = thisNode.left!!
        return thisNode
    }

    private fun findNextNodeToLeft(node:Node):Node? {
        var thisNode = node
        while ((thisNode.parent != null)) {
            if (thisNode.parent!!.left != thisNode) break
            thisNode = thisNode.parent!!
        }
        if (thisNode.parent == null) return null
        thisNode = thisNode.parent!!.left!!
        while (thisNode.isNode) thisNode = thisNode.right!!
        return thisNode
    }

    fun explode():Boolean {
        val nodesToExplode = findNodesToExplode()
        val nodeToExplode = nodesToExplode.firstOrNull()
        if (nodeToExplode != null) {
            val targetNodeToTheRight = findNextNodeToRight(nodeToExplode)
            if (targetNodeToTheRight != null) {
                targetNodeToTheRight.value += nodeToExplode.right!!.value
            }

            val targetNodeToTheLeft = findNextNodeToLeft(nodeToExplode)
            if (targetNodeToTheLeft != null) {
                targetNodeToTheLeft.value += nodeToExplode.left!!.value
            }

            val newNode = NodeLiteral(0)
            newNode.parent = nodeToExplode.parent
            if (nodeToExplode == nodeToExplode.parent!!.left) {
                newNode.parent!!.left = newNode
            }
            else {
                newNode.parent!!.right = newNode
            }
            return true
        }
        return false
    }

    fun magnitude() = root.magnitude()
}