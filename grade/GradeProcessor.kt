import java.util.*


class Grade(
    val name: String,
    val korean: Int,
    val math: Int,
    val english: Int
) {
    fun getTotalScore(): Int = korean + math + english
}

class GradeProcessor(
    private val gradeList: ArrayList<Grade> = arrayListOf()
) {
    fun addGrade(grade: Grade): Boolean {
        return gradeList.add(grade)
    }

    fun removeGradeByName(name: String): Boolean {
        return gradeList.removeIf({ it.name == name })
    }

    private fun order() {
        // sortWith는 in-place
        // sortedWith는 새로운 정렬된 collection 리턴.
        gradeList.sortWith(
            compareByDescending<Grade> { it.getTotalScore() }
                .thenByDescending{ it.korean }
                .thenByDescending{ it.math }
                .thenByDescending{ it.english }
                .thenBy{ it.name }
        )
    }

    fun getAll(): List<Grade> {
        order()
        return gradeList
    }
}

class TestGrade() {

    private fun testConstruction() {
        val grade = Grade(
            "이준석",
            100,
            99,
            98
        )

        assert(grade.name == "이준석"
                && grade.korean == 100
                && grade.math == 99
                && grade.english == 98
                && grade.getTotalScore() == (100 + 99 + 98) ) {
            "Grade data were not correctly inserted and created."
        }
    }

    fun test() {
        val tests = mutableListOf<() -> Unit>()

        tests.add(::testConstruction)

        tests.forEach({it()})
    }
}

class TestGradeProcessor() {

    private fun testAddGrade() {
        val gradeProcessor = GradeProcessor()
        val grade = Grade("이준석", 100, 99, 98)

        val addGradeResult = gradeProcessor.addGrade(grade)

        val grades = gradeProcessor.getAll()

        assert(addGradeResult) { "ArrayList add error." }
        assert(grades.size == 1) { "The size of grade list must be 1." }
        assert(grades[0] == grade) { "Grade is not correctly added into the GradeProcessor." }
    }

    private fun testRemoveGradeByNameExistingGrade() {
        val gradeProcessor = GradeProcessor()
        val grade = Grade("이준석", 100, 99, 98)
        gradeProcessor.addGrade(grade)

        val result = gradeProcessor.removeGradeByName(grade.name)

        assert(result) { "Grade should have been removed, but wasn't." }
    }

    private fun testRemoveGradeByNameNotExistingGrade() {
        val gradeProcessor = GradeProcessor()
        val grade = Grade("이준석", 100, 99, 98)
        gradeProcessor.addGrade(grade)

        val result = gradeProcessor.removeGradeByName("없는 이름")

        assert(!result) { "Removing should have been failed, but wasn't." }
    }

    private fun testOrder() {
        val g1 = Grade("calice", 95, 95, 100)
        val g2 = Grade("abob", 95, 95, 100)
        val g3 = Grade("aafly", 90, 90, 90)
        val g4 = Grade("aabbq", 88, 91, 91)
        val g5 = Grade("aaamath", 88, 90, 92)
        val g6 = Grade("aaaeng", 80, 80, 80)
        val grades = listOf(g6, g5, g4, g3, g2, g1)

        val gradeProcessor = GradeProcessor()
        grades.forEach({gradeProcessor.addGrade(it)})

        val result = gradeProcessor.getAll()

        val answer = listOf(g2, g1, g3, g4, g5, g6)

        assert(result.size == 6) { "grade size is wrong." }

        result.indices.forEach({
            assert(result[it] == answer[it]) { "wrong ordering. wrong index is (${it})." }
        })
    }

    fun test() {
        val tests = mutableListOf<() -> Unit>()

        // 참고, this를 생략할 수 있다. 
        // 클래스 멤버에 접근하는 방식이라고 이해하면 cpp이랑 똑같다고 생각할 수 있겠다.
        tests.add(this::testAddGrade)
        tests.add(this::testRemoveGradeByNameExistingGrade)
        tests.add(::testRemoveGradeByNameNotExistingGrade)
        tests.add(::testOrder)

        tests.forEach({ it() })
    }
}


fun main(args: Array<String>) {
    TestGrade().test()
    TestGradeProcessor().test()
    println("Good~")
}