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