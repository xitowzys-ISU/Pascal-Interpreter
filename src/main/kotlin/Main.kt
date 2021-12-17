import interpreter.Interpreter.Interpreter
import utils.readAllCharsOneByOne
import utils.searchFileExtension
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

fun printAsciiArt() {
    val file = File("./res/askii-art/art.txt")
    val reader = BufferedReader(FileReader(file))
    val text = reader.readAllCharsOneByOne()
    println(text)
}

fun main() {

    var interpreter: Interpreter

    printAsciiArt()

    val file = File("./res/examples-programs")

    val fileList: ArrayList<String> = file.searchFileExtension("pas")

    for ((key, value) in fileList.withIndex()) {
        println("#$key -> $value")
    }

    while (true) {
        print("Введите номер файла: ")
        val fileNumber = readLine()

        if (fileNumber.isNullOrEmpty()) {
            continue
        } else {
            val fileNumberInt = fileNumber.toInt()
            val file = File("./res/examples-programs/${fileList[fileNumberInt]}")
            val reader = BufferedReader(FileReader(file))
            val text = reader.readAllCharsOneByOne()

            interpreter = Interpreter(text)

            interpreter.interpret()

            println(interpreter.globalScope)
        }
        // TODO: Добавить обработку исключения IndexOutOfBoundsException
    }
}