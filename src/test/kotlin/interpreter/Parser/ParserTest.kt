package interpreter.Parser

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ParserTest {

    lateinit var parser: Parser

    @Test
    @DisplayName("eat() `Exception error parsing input.`")
    fun exceptionEat() {
        parser = Parser("3_5")
        val exception: Exception = Assertions.assertThrows(ParserException::class.java) {
            parser.parse()
        }

        Assertions.assertEquals("Error parsing input", exception.message)
    }
}