/**
 * Stack as type alias of Mutable List
 */
typealias Stack<T> = ArrayList<T>

/**
 * Pushes item to [Stack]
 * @param item Item to be pushed
 */
fun <T> Stack<T>.push(item: T) = add(item)

/**
 * Pops (removes and return) last item from [Stack]
 * @return item Last item if [Stack] is not empty, null otherwise
 */
fun <T> Stack<T>.pop(): T? = if (isNotEmpty()) removeAt(lastIndex) else null

/**
 * Peeks (return) last item from [Stack]
 * @return item Last item if [Stack] is not empty, null otherwise
 */
fun <T> Stack<T>.peek(): T? = if (isNotEmpty()) this[lastIndex] else null


class QueueFromStack {
    private var stack = Stack<Int>()

    private fun reversedStack(original: Stack<Int>): Stack<Int> {
        val temp = Stack(original)

        // temp stack에 들어있던 것을 다른 임시 stack으로 전부 옮긴다.
        val other = Stack<Int>()
        while (!temp.isEmpty()) {
            other.push(temp.peek()!!)
            temp.pop()
        }
        return other
    }

    fun push(n: Int) {
        stack.push(n)
    }

    fun pop() {
        if (stack.isEmpty()) return

        val reversed = reversedStack(stack)
        // 임시 stack의 가장 위에 있는 것을 제거하면 그게 Queue의 first element를 제거한 것이다.
        reversed.pop()
        
        stack = reversedStack(reversed)
    }

    fun front(): Int {
        if (stack.isEmpty()) throw Exception("unreachable")

        val reversed = reversedStack(stack)
        return reversed.peek()!!
    }

    fun isEmpty(): Boolean {
        return stack.isEmpty()
    }
}


class TestQueueFromStack {

    private fun testPushAndFront() {
        val q = QueueFromStack()
        q.push(1)
        q.push(3)
        q.push(4)

        assert(q.front() == 1) { "It was ${q.front()}, but answer is 1." }

        q.pop()
        assert(q.front() == 3) { "It was ${q.front()}, but answer is 3." }

        q.pop()
        assert(q.front() == 4) { "It was ${q.front()}, but answer is 4." }

        assert(!q.isEmpty()) { "It was ${q.isEmpty()}, but answer is false." }

        q.pop()
        assert(q.isEmpty()) { "It was ${q.isEmpty()}, but answer is true." }
    }

    fun test() {
        val tests = listOf(
            ::testPushAndFront
        )

        tests.forEach { it() }
        println("All tests passed!")
    }
}


fun main(args: Array<String>) {
    TestQueueFromStack().test()
}