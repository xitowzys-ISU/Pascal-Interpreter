package interpreter

class Interpreter(text: String) {

    private var lexer: Lexer = Lexer(text)
    private var currentToken: Token? = lexer.getNextToken()

    private fun eat() {
        currentToken = lexer.getNextToken()

        if (currentToken == null) throw InterpreterException("Error parsing input")
    }

    /**
     * [factor] : INTEGER
     */
    private fun factor(): String? {
        val token = currentToken
        eat()
        return token!!.value
    }

    /**
     * [term] : [factor] ((MUL | DIV) [factor])*
     */
    private fun term(): String {
        var result: Int = factor()?.toInt() ?: throw InterpreterException("Error parsing input")

        while (currentToken!!.type == TokenType.MUL || currentToken!!.type == TokenType.DIV) {
            val token = currentToken

            when (token!!.type) {
                TokenType.MUL -> {
                    eat()
                    result *= factor()!!.toInt()
                }
                TokenType.DIV -> {
                    eat()
                    result /= factor()!!.toInt()
                }
                else -> {
                }
            }
        }

        return result.toString()
    }

    /**
     * [term] ((PLUS | MINUS) [term])*
     */
    fun expr(): Int {
//        eat()

        var result: Int = term().toInt()

        while (currentToken!!.type == TokenType.PLUS || currentToken!!.type == TokenType.MINUS) {
            val token = currentToken

            when (token!!.type) {
                TokenType.PLUS -> {
                    eat()
                    result += term().toInt()
                }
                TokenType.MINUS -> {
                    eat()
                    result -= term().toInt()
                }
                else -> {
                }
            }
        }

        return result
    }
}