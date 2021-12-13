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
                assertEquals("3", token.value)
            })
            assertAll({
                val token = interpreter.getNextToken()
                assertEquals(TokenType.PLUS, token!!.type)
                assertEquals("+", token.value)
            })
            assertAll({
                val token = interpreter.getNextToken()
                assertEquals(TokenType.INTENGER, token!!.type)
                assertEquals("5", token.value)
            })
            assertAll({
                val token = interpreter.getNextToken()
                assertEquals(TokenType.EOS, token!!.type)
                assertNull(null)
            })
        })
    }

    @Test
    @DisplayName("Check the correct expr response")
    fun expr() {
        val listTokenType = listOf(
            TokenType.PLUS,
            TokenType.MINUS
        )

        for (i in 0..100) {
            val firstNumber = (0..99999).random()
            val secondNumber = (0..99999).random()
            val type = listTokenType.random()

            val answer = when (type) {
                TokenType.PLUS -> firstNumber + secondNumber
                TokenType.MINUS -> firstNumber - secondNumber
                else -> {
                    0
                }
            }

            val instance =
                firstNumber.toString() + " ".repeat((0..25).random()) + type.value.toString() + " ".repeat((0..25).random()) + secondNumber.toString() + " ".repeat(
                    (0..25).random()
                )

            interpreter = Interpreter(instance)
            assertEquals(answer, interpreter.expr())
        }
    }
}