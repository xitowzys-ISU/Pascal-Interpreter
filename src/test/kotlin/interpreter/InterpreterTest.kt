package interpreter

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName

internal class InterpreterTest {

    lateinit var interpreter: Interpreter

    @Test
    @DisplayName("eat() `Exception error parsing input`")
    fun exceptionEat() {
        interpreter = Interpreter("3_5")
        val exception: Exception = assertThrows(InterpreterException::class.java) {
            interpreter.expr()
        }

        assertEquals("Error parsing input", exception.message)
    }

    @Test
    @DisplayName("getNextToken() `Check the lexer 3+5`")
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
    @DisplayName("expr() `Single-line examples. With spaces`")
    fun exprTestOne() {
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

    @Test
    @DisplayName("expr() `Multi-line examples. With spaces`")
    fun exprTestTwo() {
        val listTokenType = listOf(
            TokenType.PLUS,
            TokenType.MINUS
        )

        for (i in 0..100) {
            var answer: Int = (0..99999).random()
            var instance: String = answer.toString() + " ".repeat((0..25).random())

            for (j in 0..20) {
                val type = listTokenType.random()
                val number = (0..99999).random()

                answer = when (type) {
                    TokenType.PLUS -> answer + number
                    TokenType.MINUS -> answer - number
                    else -> {
                        0
                    }
                }

                instance += type.value.toString() + " ".repeat((0..25).random()) + number.toString() + " ".repeat((0..25).random())
            }


            interpreter = Interpreter(instance)
            assertEquals(answer, interpreter.expr())

        }
    }
}