package interpreter

class Token(val type: TokenType, val value: Char?) {
    override fun toString(): String {
        return "Token($type, $value)"
    }
}