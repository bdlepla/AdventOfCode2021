import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PriorityQueueTests {
    @Test
    fun pqInitTest() {
        val queue = PriorityQueue<String>()
        val actual = queue.isEmpty()
        val expected = true
        assertEquals(expected, actual)
    }

    @Test
    fun pqPriorityTest() {
        val queue = PriorityQueue<String>()
        queue.insert("Bryan", 100)
        queue.insert("Jennifer", 50)
        val actual = queue.pull()
        val expected = "Jennifer"
        assertEquals(expected, actual)
    }

    @Test
    fun pqSamePriorityTest(){
        val queue = PriorityQueue<String>()
        queue.insert("Bryan", 100)
        queue.insert("Jennifer", 100)
        val actual = queue.pull()
        val expected = "Bryan"
        assertEquals(expected, actual)
    }

    @Test
    fun pqPerformanceTest() {
        val queue = PriorityQueue<Int>()
        val random = Random(1000)
        val sampleSize = 1_000_000
        (0..sampleSize).forEach { idx ->
            val randomPriority = random.nextInt()
            queue.insert(idx, randomPriority)
            if (idx % 100 == 0) {
                val wow = queue.pull()
                if (wow == Int.MAX_VALUE) return
            }
        }
        assertTrue { true }
    }
}