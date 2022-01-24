class StringIterator(string:String) {
    private val iter = string.iterator()
    private var hasSavedChar = false
    private var savedChar:Char = ' '

    fun hasNext() = hasSavedChar || iter.hasNext()
    fun next():Char {
        return if (hasSavedChar) {
            hasSavedChar = false
            savedChar
        }
        else iter.nextChar()
    }
    fun putBack(c:Char) {
        if (!hasSavedChar) {
            hasSavedChar = true
            savedChar = c
        }
    }
    fun takeUntil(c:Char):String =
        buildString{
            var ic = next()
            while (ic != c) {
                append(ic)
                ic = next()
            }
            putBack(ic)
        }

}