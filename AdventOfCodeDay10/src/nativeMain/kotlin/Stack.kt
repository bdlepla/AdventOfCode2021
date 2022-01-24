class Stack<T> {
    private val data = ArrayDeque<T>()//mutableListOf<T>()
    fun count() = data.count()
    fun isEmpty() = data.isEmpty()
    fun push(i:T) {
        data.add(i)
    }

    fun pop():T {
        if (data.isEmpty()) throw Exception("Stack is empty on Pop")
        return data.removeLast()
    }

    fun peek():T {
        if (data.isEmpty()) throw Exception("Stack is empty on Peek")
        return data.last()
    }

    fun asSequence(): Sequence<T> = data.asReversed().asSequence()
}