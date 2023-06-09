package interpreter.Lexer

import interpreter.Lexer.Token.Enums.ArithmeticOperators
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class LexerTest {
    @Test
    @DisplayName("getNextToken() `Check the lexer 3+5`")
    fun lexerTestOne() {
        val laxer = Lexer("3+5")

        Assertions.assertAll({
            Assertions.assertAll({
                val token = laxer.getNextToken()
                Assertions.assertEquals(ArithmeticOperators.INTENGER, token!!.type)
                Assertions.assertEquals("3", token.value)
            })
            Assertions.assertAll({
                val token = laxer.getNextToken()
                Assertions.assertEquals(ArithmeticOperators.PLUS, token!!.type)
                Assertions.assertEquals("+", token.value)
            })
            Assertions.assertAll({
                val token = laxer.getNextToken()
                Assertions.assertEquals(ArithmeticOperators.INTENGER, token!!.type)
                Assertions.assertEquals("5", token.value)
            })
            Assertions.assertAll({
                val token = laxer.getNextToken()
                Assertions.assertEquals(ArithmeticOperators.EOS, token!!.type)
                Assertions.assertNull(null)
            })
        })
    }

    @Test
    @DisplayName("getNextToken() `Check the lexer 7 * 5 / 3`")
    fun lexerTestTwo() {
        val laxer = Lexer("7 * 5 / 3")

        Assertions.assertAll({
            Assertions.assertAll({
                val token = laxer.getNextToken()
                Assertions.assertEquals(ArithmeticOperators.INTENGER, token!!.type)
                Assertions.assertEquals("7", token.value)
            })
            Assertions.assertAll({
                val token = laxer.getNextToken()
                Assertions.assertEquals(ArithmeticOperators.MUL, token!!.type)
                Assertions.assertEquals("*", token.value)
            })
            Assertions.assertAll({
                val token = laxer.getNextToken()
                Assertions.assertEquals(ArithmeticOperators.INTENGER, token!!.type)
                Assertions.assertEquals("5", token.value)
            })
            Assertions.assertAll({
                val token = laxer.getNextToken()
                Assertions.assertEquals(ArithmeticOperators.DIV, token!!.type)
                Assertions.assertEquals("/", token.value)
            })
            Assertions.assertAll({
                val token = laxer.getNextToken()
                Assertions.assertEquals(ArithmeticOperators.INTENGER, token!!.type)
                Assertions.assertEquals("3", token.value)
            })
            Assertions.assertAll({
                val token = laxer.getNextToken()
                Assertions.assertEquals(ArithmeticOperators.EOS, token!!.type)
                Assertions.assertNull(null)
            })
        })
    }
}

