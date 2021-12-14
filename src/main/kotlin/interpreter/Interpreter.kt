package interpreter

class Interpreter(text: String) {

    private var lexer: Lexer = Lexer(text)
    private var currentToken: Token? = null

    private fun eat() {
        currentToken = lexer.getNextToken()

        if (currentToken == null) throw InterpreterException("Error parsing input")
    }

    private fun term(): String? {
        val token = currentToken
        eat()
        return token!!.value
    }

    fun expr(): Int {
        eat()

        var result: Int = term()?.toInt() ?: 0

        while (currentToken!!.type == TokenType.PLUS || currentToken!!.type == TokenType.MINUS) {
            val token = currentToken

            when (token!!.type) {
                TokenType.PLUS -> {
                    eat()
                    result += term()!!.toInt()
                }
                TokenType.MINUS -> {
                    eat()
                    result -= term()!!.toInt()
                }
                else -> {}
            }
        }

        return result
    }
}