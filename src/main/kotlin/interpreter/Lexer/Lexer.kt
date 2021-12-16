package interpreter.Lexer

class Lexer(val text: String) {

    private var pos: Int = 0
    private var currentChar: Char? = text[pos]

    fun getNextToken(): Token? {

        while (currentChar != null) {
            if (currentChar!!.isWhitespace()) {
                skipWhitespace()
                continue
            }

            if (currentChar!!.isDigit()) return Token(TokenType.INTENGER, integer())

            if (currentChar == '+') {
                advance()
                return Token(TokenType.PLUS, TokenType.PLUS.value)
            }

            if (currentChar == '*') {
                advance()
                return Token(TokenType.MUL, TokenType.MUL.value)
            }

            if (currentChar == '/') {
                advance()
                return Token(TokenType.DIV, TokenType.DIV.value)
            }

            if (currentChar == '-') {
                advance()
                return Token(TokenType.MINUS, TokenType.MINUS.value)
            }

            if (currentChar == '(') {
                advance()
                return Token(TokenType.LPAREN, TokenType.LPAREN.value)
            }

            if (currentChar == ')') {
                advance()
                return Token(TokenType.RPAREN, TokenType.RPAREN.value)
            }

            return null
        }

        return Token(TokenType.EOS, TokenType.EOS.value)
    }

    private fun skipWhitespace() {
        while (currentChar != null && currentChar!!.isWhitespace()) advance()
    }

    private fun integer(): String {
        var result = ""
        while (currentChar != null && currentChar!!.isDigit()) {
            result += currentChar
            advance()
        }

        return result
    }

    private fun advance() {
        pos++
        currentChar = if (pos > text.length - 1) null else text[pos]
    }
}