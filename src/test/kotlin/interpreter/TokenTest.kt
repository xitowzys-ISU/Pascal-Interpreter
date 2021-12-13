package interpreter

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class TokenTest {
    lateinit var interpreter: Interpreter

    @Test
    fun testToString() {
        interpreter = Interpreter("3+5")

        assertAll({
            assertEquals("Token(INTENGER, 3)", interpreter.getNextToken().toString())
        })
    }
}