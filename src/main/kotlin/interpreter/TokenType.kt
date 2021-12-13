package interpreter

enum class TokenType(val value: String?) {
    INTENGER(""),
    PLUS("+"),
    MINUS("-"),
    EOS(null)
}