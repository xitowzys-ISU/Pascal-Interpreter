package interpreter.Interpreter

import interpreter.Lexer.Token.Enums.ArithmeticOperators
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import utils.readAllCharsOneByOne
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

internal class InterpreterTest {

    private lateinit var interpreter: Interpreter

    private fun wrapPrograms(number: String): String = "BEGIN a := $number END."

    private fun getVarInProgram() = interpreter.globalScope["a"]

    @Test
    @DisplayName("Single-line examples. With spaces. Plus and minus.")
    fun testOne() {
        val listArithmeticOperators = listOf(
            ArithmeticOperators.PLUS,
            ArithmeticOperators.MINUS
        )

        for (i in 0..100) {
            val firstNumber = (0..99999).random()
            val secondNumber = (0..99999).random()
            val type = listArithmeticOperators.random()

            val answer = when (type) {
                ArithmeticOperators.PLUS -> firstNumber + secondNumber
                ArithmeticOperators.MINUS -> firstNumber - secondNumber
                else -> {
                    0
                }
            }

            val instance =
                firstNumber.toString() + " ".repeat((0..25).random()) + type.value.toString() + " ".repeat((0..25).random()) + secondNumber.toString() + " ".repeat(
                    (0..25).random()
                )


            interpreter = Interpreter(wrapPrograms(instance))
            interpreter.interpret()
            assertEquals(answer, getVarInProgram())
        }
    }

    @Test
    @DisplayName("Multi-line examples. With spaces. Plus and minus.")
    fun testTwo() {
        val listArithmeticOperators = listOf(
            ArithmeticOperators.PLUS,
            ArithmeticOperators.MINUS
        )

        for (i in 0..100) {
            var answer: Int = (0..99999).random()
            var instance: String = answer.toString() + " ".repeat((0..25).random())

            for (j in 0..20) {
                val type = listArithmeticOperators.random()
                val number = (0..99999).random()

                answer = when (type) {
                    ArithmeticOperators.PLUS -> answer + number
                    ArithmeticOperators.MINUS -> answer - number
                    else -> {
                        0
                    }
                }

                instance += type.value.toString() + " ".repeat((0..25).random()) + number.toString() + " ".repeat((0..25).random())
            }


            interpreter = Interpreter(wrapPrograms(instance))
            interpreter.interpret()
            assertEquals(answer, getVarInProgram())

        }
    }

    @Test
    @DisplayName("Multi-line examples. With spaces. Multiplication and division.")
    fun testThree() {
        val listArithmeticOperators = listOf(
            ArithmeticOperators.MUL,
            ArithmeticOperators.DIV
        )

        for (i in 0..100) {
            var answer: Int = (1..500).random()
            var instance: String = answer.toString() + " ".repeat((0..25).random())

            for (j in 0..5) {
                val type = listArithmeticOperators.random()
                val number = (1..500).random()

                answer = when (type) {
                    ArithmeticOperators.MUL -> answer * number
                    ArithmeticOperators.DIV -> answer / number
                    else -> {
                        0
                    }
                }

                instance += type.value.toString() + " ".repeat((0..25).random()) + number.toString() + " ".repeat((0..25).random())
            }


            interpreter = Interpreter(wrapPrograms(instance))
            interpreter.interpret()
            assertEquals(answer, getVarInProgram())

        }
    }

    @Test
    @DisplayName("Multi-line examples. With spaces. Plus, minus, multiplication, division, brackets.")
    fun testFour() {
        assertAll({
            assertAll({
                interpreter = Interpreter(wrapPrograms("(10 + 20) * 40 / (2 + 1)"))
                interpreter.interpret()
                assertEquals(400, getVarInProgram())
            })
            assertAll({
                interpreter = Interpreter(wrapPrograms("2 / 2 - 2 + 3 * ((1 + 1) + (1 + 1))"))
                interpreter.interpret()
                assertEquals(11, getVarInProgram())
            })
            assertAll({
                interpreter = Interpreter(wrapPrograms("(12 + 8) - 3"))
                interpreter.interpret()
                assertEquals(17, getVarInProgram())
            })
        })


    }

    @Test
    @DisplayName("Multi-line examples. Unary operators")
    fun testFive() {
        assertAll({
            assertAll({
                interpreter = Interpreter(wrapPrograms("--(-10 + 20) * 40 / (2 + 1)"))
                interpreter.interpret()
                assertEquals(133, getVarInProgram())
            })
            assertAll({
                interpreter = Interpreter(wrapPrograms("- --2 / 2 - 2 + 3 * ((1 + 1) + (1 + 1))"))
                interpreter.interpret()
                assertEquals(9, getVarInProgram())
            })
            assertAll({
                interpreter = Interpreter(wrapPrograms("+-(12 + 8) - 3"))
                interpreter.interpret()
                assertEquals(-23, getVarInProgram())
            })
        })
    }

    @Test
    @DisplayName("Program example_1.pas")
    fun testSix() {
        val answer = mapOf<String, Any>()

        val file = File("./res/examples-programs/example_1.pas")
        val reader = BufferedReader(FileReader(file))
        val text = reader.readAllCharsOneByOne()

        interpreter = Interpreter(text)

        interpreter.interpret()
        assertTrue(interpreter.globalScope == answer)
    }

    @Test
    @DisplayName("Program example_2.pas")
    fun testSeven() {
        val answer = mapOf<String, Any>("x" to 17, "y" to 11)

        val file = File("./res/examples-programs/example_2.pas")
        val reader = BufferedReader(FileReader(file))
        val text = reader.readAllCharsOneByOne()

        interpreter = Interpreter(text)

        interpreter.interpret()
        assertTrue(interpreter.globalScope == answer)
    }

    @Test
    @DisplayName("Program example_3.pas")
    fun testEight() {
        val answer = mapOf<String, Any>("y" to 2, "a" to 3, "b" to 18, "c" to -15, "x" to 11)

        val file = File("./res/examples-programs/example_3.pas")
        val reader = BufferedReader(FileReader(file))
        val text = reader.readAllCharsOneByOne()

        interpreter = Interpreter(text)

        interpreter.interpret()
        assertTrue(interpreter.globalScope == answer)
    }


}