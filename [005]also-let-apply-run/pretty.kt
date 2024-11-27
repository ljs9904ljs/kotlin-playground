
enum class SpeedType {
    SLOW,
    NORMAL,
    FAST
}

data class Computer(
    val name: String,
    var price: Int,
    var speed: SpeedType
)

fun doAlso(c: Computer, extraPrice: Int): Int {
    val c2 = c.also {
        it.price += extraPrice
        println("[doAlso] ${it.name}, ${it.price}, ${it.speed}")
    }
    return c2.price
}

fun doLet(c: Computer, extraPrice: Int): Int {
    val c2 = c.let {
        Computer(c.name, c.price + extraPrice, c.speed)
    }
    return c2.price
}

fun doApply(c: Computer, extraPrice: Int) {
    c.apply {
        price += extraPrice
    }
}

fun doRun(c: Computer, extraPrice: Int): Int {
    return c.run {
        price + extraPrice
    }
}

class Test {
    private fun testDoAlso() {
        val c = Computer(
            name = "asus",
            price = 199,
            speed = SpeedType.NORMAL
        )
        val extraPrice = 1000
        
        val result = doAlso(c, extraPrice)

        assert(result == c.price && c.price == 1000 + 199) {
            "In also function, the original object can't be changed."
        }
    }

    private fun testDoLet() {
        val c = Computer(
            name = "asus",
            price = 199,
            speed = SpeedType.NORMAL
        )
        val extraPrice = 1000

        val result = doLet(c, extraPrice)

        assert(result == c.price + extraPrice && result == 1199) {
            "result was ${result}, but it must be ${c.price + extraPrice}"
        }
    }

    private fun testDoApply() {
        val c = Computer(
            name = "asus",
            price = 199,
            speed = SpeedType.NORMAL
        )
        val extraPrice = 1000

        doApply(c, extraPrice)

        assert(c.price == 199 + extraPrice) {
            "c.price was ${c.price}, but it must be ${199 + extraPrice}"
        }
    }

    private fun testDoRun() {
        val c = Computer(
            name = "asus",
            price = 199,
            speed = SpeedType.NORMAL
        )
        val extraPrice = 1000

        val result = doRun(c, extraPrice)

        assert(result == c.price + extraPrice && result == 1199) {
            "result was ${result}, but it must be ${c.price + extraPrice} and 1199."
        }
    }

    fun test() {
        val tests = listOf(
            ::testDoAlso,
            ::testDoLet,
            ::testDoApply,
            ::testDoRun
        )

        tests.forEach { it() }
    }
}

fun main(args: Array<String>) {
    Test().test()
}