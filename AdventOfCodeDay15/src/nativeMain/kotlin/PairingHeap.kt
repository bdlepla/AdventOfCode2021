

class HeapElement<T>(e:T?=null, val sublist:List<HeapElement<T>> = listOf()) where T:Comparable<T> {
    var element:T? = e
    fun isEmpty() = element == null
    operator fun compareTo(other: HeapElement<T>):Int =
        element!!.compareTo(other.element!!)
    fun countElements():Int =  if (isEmpty()) 0 else 1 +
            sublist.sumOf{it.countElements()}

    override fun toString(): String =
        if (isEmpty()) "<Empty>"
        else "$element $sublist"
}

class PairingHeap<T> where T:Comparable<T> {
    val emptyNode = HeapElement<T>()
    var root:HeapElement<T> = emptyNode
    fun isEmpty() = root == emptyNode
    fun isNotEmpty() = !isEmpty()
    fun findMin() = root.element!!
    val size:Int = root.countElements()

    fun deleteMin() {
        if (isEmpty()) throw Exception("Data is empty")
        root = mergePairs(root.sublist)
        //println("after delete root contains $root")
    }

    fun insert(element:T) {
        root = meld(HeapElement(element), root)
        //println("after insert root contains $root")
    }

    private fun meld(a:HeapElement<T>, b:HeapElement<T>):HeapElement<T> =
        when {
            a.isEmpty() -> b
            b.isEmpty() -> a
            a < b -> HeapElement(a.element!!, listOf(b) + a.sublist)
            else -> HeapElement(b.element!!, listOf(a) + b.sublist)
        }

    private fun mergePairs(list:List<HeapElement<T>>):HeapElement<T> =
        when {
            list.isEmpty() -> emptyNode
            list.size == 1 -> list[0]
            else -> meld(meld(list[0], list[1]), mergePairs(list.drop(2)))
        }
}