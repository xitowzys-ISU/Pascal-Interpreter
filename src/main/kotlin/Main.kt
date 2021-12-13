import interpreter.Interpreter

fun main() {
    var text: String?
    var interpreter: Interpreter
    var result: Int

    while (true) {
        print("cal> ")
        text = readLine()
        if (text.isNullOrEmpty()) {
            continue
        } else {
            interpreter = Interpreter(text)
            result = interpreter.expr()
            println(result)
        }
    }
}