package interpreter.Parser

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ParserTest {

    private lateinit var parser: Parser

    @Test
    @DisplayName("eat() `Exception error parsing input.`")
    fun exceptionEat() {
        parser = Parser("BEGIN a:= 3_5 END.")
        val exception: Exception = Assertions.assertThrows(ParserException::class.java) {
            parser.parse()
        }

        Assertions.assertEquals("Error parsing", exception.message)
    }
}