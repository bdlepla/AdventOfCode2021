class PriorityQueue<T> {
     data class PQPair<T>(val priority:Int, val data:T):Comparable<PQPair<T>> {
        override operator fun compareTo(other:PQPair<T>):Int = this.priority - other.priority
    }
    private val data = PairingHeap<PQPair<T>>()
    fun isEmpty() = data.isEmpty()
    fun isNotEmpty() = !isEmpty()
    private val size = data.size
    fun insert(e:T, priority:Int) {
        data.insert(PQPair(priority, e))
        //println("after insert, queue has $size elements")
    }

    fun pull():T = pullWithPriority().first

    fun pullWithPriority():Pair<T, Int> {
        val node = data.findMin()
        data.deleteMin()
        //println("after delete, queue has $size elements")
        return Pair(node.data, node.priority)
    }
}
