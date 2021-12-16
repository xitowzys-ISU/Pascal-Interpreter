import interpreter.Interpreter.Interpreter

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
            result = interpreter.interpret()
            println(result)
        }
    }
//    interpreter = Interpreter("1+2+5 * 12")
//    interpreter.interpret()
}