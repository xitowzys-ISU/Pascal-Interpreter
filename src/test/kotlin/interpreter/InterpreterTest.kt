package interpreter

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName

internal class InterpreterTest {

    lateinit var interpreter: Interpreter

    @Test
    @DisplayName("Exception error parsing input")
    fun exceptionExpr() {
        interpreter = Interpreter("3_5")
        val exception: Exception = assertThrows(InterpreterException::class.java) {
            interpreter.expr()
        }

        assertEquals("Error parsing input", exception.message)
    }

    @Test
    @DisplayName("Check the lexer 3+5")
    fun lexer() {
        interpreter = Interpreter("3+5")

        assertAll({
            assertAll({
                val token = interpreter.getNextToken()
                assertEquals(TokenType.INTENGER, token!!.type)
                assertEquals('3', token.value)
            })
            assertAll({
                val token = interpreter.getNextToken()
                assertEquals(TokenType.PLUS, token!!.type)
                assertEquals('+', token.value)
            })
            assertAll({
                val token = interpreter.getNextToken()
                assertEquals(TokenType.INTENGER, token!!.type)
                assertEquals('5', token.value)
            })
            assertAll({
                val token = interpreter.getNextToken()
                assertEquals(TokenType.EOS, token!!.type)
                assertEquals(null, token.value)
            })
        })
    }

    @Test
    @DisplayName("Check the correct expr response")
    fun expr() {
        interpreter = Interpreter("9+8")
        assertEquals(17, interpreter.expr())
    }
}