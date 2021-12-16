package interpreter

enum class TokenType(val value: String?) {
    INTENGER(""),
    PLUS("+"),
    MINUS("-"),
    MUL("*"),
    DIV("/"),
    LPAREN("("),
    RPAREN(")"),
    EOS(null)
}