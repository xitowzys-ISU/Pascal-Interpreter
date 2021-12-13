package interpreter

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName

internal class TokenTest {
    lateinit var interpreter: Interpreter

    @Test
    @DisplayName("Token output to string")
    fun testToString() {
        interpreter = Interpreter("3+5")

        assertAll({
            assertEquals("Token(INTENGER, 3)", interpreter.getNextToken().toString())
        })
    }
}