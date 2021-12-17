package interpreter.Lexer

import interpreter.Lexer.Token.Enums.ArithmeticOperators
import interpreter.Lexer.Token.Enums.General
import interpreter.Lexer.Token.Enums.HardKeywords
import interpreter.Lexer.Token.Enums.SpecialSymbols
import interpreter.Lexer.Token.Token

class Lexer(private val text: String) {

    private var pos: Int = 0
    private var currentChar: Char? = text[pos]

    /**
     * Handle identifiers and reserved keywords
     */
    private fun id(): Token {
        var result = ""
        while (currentChar != null && currentChar!!.isLetter()) {
            result += currentChar
            advance()
        }


        return try {
            Token(HardKeywords.valueOf(result), HardKeywords.valueOf(result).value)
        } catch (e: IllegalArgumentException) {
            Token(General.ID, result)
        }
    }

    private fun peek(): Char? {
        val peekPos = pos++
        return if (peekPos > text.length - 1) null else text[pos]
    }

    fun getNextToken(): Token? {

        while (currentChar != null) {

            if (currentChar!!.isLetter()) {
                return id()
            }

            if (currentChar == ':' && peek() == '=') {
                advance()
                advance()
                return Token(SpecialSymbols.ASSIGN, SpecialSymbols.ASSIGN.value)
            }

            if (currentChar == ';') {
                advance()
                return Token(SpecialSymbols.SEMI, SpecialSymbols.SEMI.value)
            }

            if (currentChar == '.') {
                advance()
                return Token(SpecialSymbols.DOT, SpecialSymbols.DOT.value)
            }

            if (currentChar!!.isWhitespace()) {
                skipWhitespace()
                continue
            }

            if (currentChar!!.isDigit()) return Token(ArithmeticOperators.INTENGER, integer())

            if (currentChar == '+') {
                advance()
                return Token(ArithmeticOperators.PLUS, ArithmeticOperators.PLUS.value)
            }

            if (currentChar == '*') {
                advance()
                return Token(ArithmeticOperators.MUL, ArithmeticOperators.MUL.value)
            }

            if (currentChar == '/') {
                advance()
                return Token(ArithmeticOperators.DIV, ArithmeticOperators.DIV.value)
            }

            if (currentChar == '-') {
                advance()
                return Token(ArithmeticOperators.MINUS, ArithmeticOperators.MINUS.value)
            }

            if (currentChar == '(') {
                advance()
                return Token(ArithmeticOperators.LPAREN, ArithmeticOperators.LPAREN.value)
            }

            if (currentChar == ')') {
                advance()
                return Token(ArithmeticOperators.RPAREN, ArithmeticOperators.RPAREN.value)
            }

            return null
        }

        return Token(ArithmeticOperators.EOS, ArithmeticOperators.EOS.value)
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