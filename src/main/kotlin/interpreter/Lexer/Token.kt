package interpreter.Lexer

class Token(val type: TokenType, val value: String?) {
    override fun toString(): String {
        return "Token($type, $value)"
    }
}