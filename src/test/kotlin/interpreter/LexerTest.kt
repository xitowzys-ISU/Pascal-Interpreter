package interpreter

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class LexerTest {
    @Test
    @DisplayName("getNextToken() `Check the lexer 3+5`")
    fun lexer() {
        val laxer = Lexer("3+5")

        Assertions.assertAll({
            Assertions.assertAll({
                val token = laxer.getNextToken()
                Assertions.assertEquals(TokenType.INTENGER, token!!.type)
                Assertions.assertEquals("3", token.value)
            })
            Assertions.assertAll({
                val token = laxer.getNextToken()
                Assertions.assertEquals(TokenType.PLUS, token!!.type)
                Assertions.assertEquals("+", token.value)
            })
            Assertions.assertAll({
                val token = laxer.getNextToken()
                Assertions.assertEquals(TokenType.INTENGER, token!!.type)
                Assertions.assertEquals("5", token.value)
            })
            Assertions.assertAll({
                val token = laxer.getNextToken()
                Assertions.assertEquals(TokenType.EOS, token!!.type)
                Assertions.assertNull(null)
            })
        })
    }
}