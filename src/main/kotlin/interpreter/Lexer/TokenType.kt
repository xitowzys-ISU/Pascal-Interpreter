package interpreter.Lexer

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