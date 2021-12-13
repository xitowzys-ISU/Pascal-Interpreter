package interpreter

class Interpreter(val text: String) {

    private var pos: Int = 0
    private var currentToken: Token? = null
    private var currentChar: Char? = text[pos]

    private fun advance() {
        pos++
        if (pos > text.length - 1) {
            currentChar = null
        } else {
            currentChar = text[pos]
        }
    }

    private fun skipWhitespace() {
        while (currentChar != null && currentChar!!.isWhitespace()) {
            advance()
        }
    }

    private fun integer(): String {
        var result = ""
        while (currentChar != null && currentChar!!.isDigit()) {
            result += currentChar
            advance()
        }

        return result
    }

    private fun eat() {
        currentToken = getNextToken()

        if (currentToken == null) throw InterpreterException("Error parsing input")
    }

    fun getNextToken(): Token? {

        while (currentChar != null) {
            if (currentChar!!.isWhitespace()) {
                skipWhitespace()
                continue
            }

            if (currentChar!!.isDigit()) {
                return Token(TokenType.INTENGER, integer())
            }

            if (currentChar == '+') {
                advance()
                return Token(TokenType.PLUS, TokenType.PLUS.value)
            }

            if (currentChar == '-') {
                advance()
                return Token(TokenType.MINUS, TokenType.MINUS.value)
            }

            return null
        }

        return Token(TokenType.EOS, TokenType.EOS.value)
    }

    fun expr(): Int {
        eat()

        val left = currentToken
        eat()

        val op = currentToken
        eat()

        val right = currentToken
        eat()

        val result: Int

        if (op!!.type == TokenType.PLUS) {
            result = left?.value.toString().toInt() + right?.value.toString().toInt()
        } else {
            result = left?.value.toString().toInt() - right?.value.toString().toInt()
        }

        return result
    }
}