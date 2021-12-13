package interpreter

class Interpreter(val text: String) {

    private var pos: Int = 0
    private var currentToken: Token? = null

    fun getNextToken(): Token? {
        if (pos > text.length - 1) {
            return Token(TokenType.EOS, null)
        }

        val currentChar: Char = text[pos]

        if (currentChar.isDigit()) {
            pos++
            return Token(TokenType.INTENGER, currentChar)
        }

        if (currentChar == '+') {
            pos++
            return Token(TokenType.PLUS, currentChar)
        }

        return null
    }

    private fun eat() {
        currentToken = getNextToken()

        if(currentToken == null) throw InterpreterException("Error parsing input")
    }

    fun expr(): Int {
        eat()

        val left = currentToken
        eat()

        currentToken
        eat()

        val right = currentToken
        eat()

        val result: Int = left?.value.toString().toInt() + right?.value.toString().toInt()

        return result
    }
}