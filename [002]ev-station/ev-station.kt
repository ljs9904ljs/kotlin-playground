import java.io.File
import java.nio.charset.Charset


class CSVReader(
    csvFilePath: String = "ev-station-information.csv"
) {
    private val file = File(csvFilePath)
    private val charset = Charset.forName("UTF-8")
    

    fun dump() {
        file.bufferedReader(charset).use { reader ->
            reader.forEachLine { line ->
                val tokens = line.split(",")
                println(tokens)
            
            }
        }
    }
}



fun main(args: Array<String>) {
    println("hi!")
    val csvReader = CSVReader()
    csvReader.dump()
}