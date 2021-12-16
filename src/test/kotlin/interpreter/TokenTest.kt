package interpreter

import interpreter.Lexer.Lexer
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName

internal class TokenTest {

    @Test
    @DisplayName("toString() `Token output to string`")
    fun testToString() {

        val laxer = Lexer("3+5")

        assertAll({
            assertEquals("Token(INTENGER, 3)", laxer.getNextToken().toString())
        })
    }
}