import interpreter.Interpreter.Interpreter
import interpreter.Lexer.Lexer

fun main() {
    var text: String?
    var interpreter: Interpreter
    var result: Int

//    while (true) {
//        print("cal> ")
//        text = readLine()
//        if (text.isNullOrEmpty()) {
//            continue
//        } else {
//            interpreter = Interpreter(text)
//            result = interpreter.interpret()
//            println(result)
//        }
//    }
//    val lexer = Lexer("BEGIN a := 2; END.")
//    println(lexer.getNextToken().toString())
//    println(lexer.getNextToken().toString())
//    println(lexer.getNextToken().toString())
//    println(lexer.getNextToken().toString())
//    println(lexer.getNextToken().toString())
//    println(lexer.getNextToken().toString())
//    println(lexer.getNextToken().toString())
//    println(lexer.getNextToken().toString())
//    println(lexer.getNextToken().toString())
//    println(lexer.getNextToken().toString())
    interpreter = Interpreter(
        "BEGIN\n" +
                "    BEGIN\n" +
                "        number := 2;\n" +
                "        a := number;\n" +
                "        b := 10 * a + 10 * number / 4;\n" +
                "        c := a - - b\n" +
                "    END;\n" +
                "    x := 11;\n" +
                "END."
    )
    interpreter.interpret()
}