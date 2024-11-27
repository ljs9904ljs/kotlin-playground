/*
나만의 Kotlin annotation 만들기


1. 나의 annotation 클래스를 만든다.
    1.1. 그 annotation class에 meta annotation을 붙인다.
    1.2. annotation에 파라미터를 추가해줄 수도 있다.

2. 나의 annotation processor를 만든다.  
===>>> 이거는 KSP나 KAPT같은 별도의 dependency를 추가해야 해서 기본 환경에서는 불가능할 듯함.
    (장점1, boiler plate 코드를 만들어 줄 수 있다.)
    (장점2, anootated target에 대한 메타 데이터 관련 작업을 따로 분리하기 좋다.)

하지만 MyClass::class.annotations 라는 방식으로 annotation을 인식할 수 있기는 하다. -> 이거는 @Retention(Annotation.RUNTIME)이 필요하다. reflection을 사용해서 인식하는 방식이다.

*/

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class MyAnno(val value: Int)


class MyAnnoNotFoundException(
    message: String = "MyAnno annotation is not found.",
    cause: Throwable? = null
): Exception(message, cause)


class MyAnnoProcessor(
    private val instance: Any
) {
    init {
        checkMyAnno()
    }
    
    private fun checkMyAnno() {
        if (!instance::class.annotations.any({
            it is MyAnno
        })) {
            throw MyAnnoNotFoundException()
        }
    }
    
    fun process(): Int {
        val anno = instance::class.annotations.filterIsInstance<MyAnno>().first()
        
        return if (instance is Person) {
            instance.age + anno.value
        } else {
            -1
        }
    }
}

@MyAnno(value = 1234)
class Person(
    val name: String,
    val age: Int
)


class TestMyAnnoAndMyAnnoProcessor {
    private fun testWrongTypeInstance() {
        val instance = 1234321
        var expectedException: Exception? = null
        
        try {
            MyAnnoProcessor(instance)    
        } catch (e: Exception) {
            expectedException = e
        }
        
        assert(expectedException is MyAnnoNotFoundException) {
                "The type of exception must be MyAnnoNotFoundException."
            }
    }
    
    private fun testCorrectTypeInstance() {
        val person = Person(
            name = "Junseok Lee",
            age = 25
        )
        
        val ap = MyAnnoProcessor(person)
        assert(ap.process() == 1259) {
            "Annotation process result must be 1259."
        }
    }
    
    fun test() {
        val tests = listOf(
            ::testWrongTypeInstance,
            ::testCorrectTypeInstance
        )
        
        tests.forEach { it() }
    }
}


fun main(args: Array<String>) {
    TestMyAnnoAndMyAnnoProcessor().test()
}