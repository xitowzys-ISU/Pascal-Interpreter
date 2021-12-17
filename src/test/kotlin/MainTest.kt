import com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions
import utils.readAllCharsOneByOne
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class MainTest {

    @Test
    fun testOne() {
        val output = tapSystemOut {
            printAsciiArt()
        }

        val file = File("./res/askii-art/art.txt")
        val reader = BufferedReader(FileReader(file))
        val text = reader.readAllCharsOneByOne()

        Assertions.assertEquals(
            text.trim(),
            output.trim()
        )
    }
}