package interpreter

enum class TokenType(val value: String?) {
    INTENGER(""),
    PLUS("+"),
    MINUS("-"),
    MUL("*"),
    DIV("/"),
    EOS(null)
}