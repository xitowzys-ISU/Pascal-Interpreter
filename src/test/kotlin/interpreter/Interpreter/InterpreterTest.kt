package interpreter.Interpreter

import interpreter.Lexer.TokenType
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName

internal class InterpreterTest {

    private lateinit var interpreter: Interpreter

    @Test
    @DisplayName("interpret() `Single-line examples. With spaces. Plus and minus.`")
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
            assertEquals(answer, interpreter.interpret())
        }
    }

    @Test
    @DisplayName("interpret() `Multi-line examples. With spaces. Plus and minus.`")
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
            assertEquals(answer, interpreter.interpret())

        }
    }

    @Test
    @DisplayName("interpret() `Multi-line examples. With spaces. Multiplication and division.`")
    fun exprTestThree() {
        val listTokenType = listOf(
            TokenType.MUL,
            TokenType.DIV
        )

        for (i in 0..100) {
            var answer: Int = (1..500).random()
            var instance: String = answer.toString() + " ".repeat((0..25).random())

            for (j in 0..5) {
                val type = listTokenType.random()
                val number = (1..500).random()

                answer = when (type) {
                    TokenType.MUL -> answer * number
                    TokenType.DIV -> answer / number
                    else -> {
                        0
                    }
                }

                instance += type.value.toString() + " ".repeat((0..25).random()) + number.toString() + " ".repeat((0..25).random())
            }


            interpreter = Interpreter(instance)
            assertEquals(answer, interpreter.interpret())

        }
    }

    @Test
    @DisplayName("interpret() `Multi-line examples. With spaces. Plus, minus, multiplication, division, brackets.`")
    fun exprTestFour() {
        assertAll({
            assertAll({
                interpreter = Interpreter("(10 + 20) * 40 / (2 + 1)")
                assertEquals(interpreter.interpret(), 400)
            })
            assertAll({
                interpreter = Interpreter("2 / 2 - 2 + 3 * ((1 + 1) + (1 + 1))")
                assertEquals(interpreter.interpret(), 11)
            })
            assertAll({
                interpreter = Interpreter("(12 + 8) - 3")
                assertEquals(interpreter.interpret(), 17)
            })
        })


    }

    @Test
    @DisplayName("interpret() `Multi-line examples. Unary operators`")
    fun exprTestFive() {
        assertAll({
            assertAll({
                interpreter = Interpreter("--(-10 + 20) * 40 / (2 + 1)")
                assertEquals(interpreter.interpret(), 133)
            })
            assertAll({
                interpreter = Interpreter("- --2 / 2 - 2 + 3 * ((1 + 1) + (1 + 1))")
                assertEquals(interpreter.interpret(), 9)
            })
            assertAll({
                interpreter = Interpreter("+-(12 + 8) - 3")
                assertEquals(interpreter.interpret(), -23)
            })
        })


    }
}