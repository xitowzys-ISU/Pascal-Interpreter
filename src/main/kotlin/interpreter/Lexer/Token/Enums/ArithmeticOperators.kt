package interpreter.Lexer.Token.Enums

import interpreter.Lexer.Token.Interfaces.ITokenType

enum class ArithmeticOperators(val value: String?) : ITokenType {
    INTENGER(""),
    PLUS("+"),
    MINUS("-"),
    MUL("*"),
    DIV("/"),
    LPAREN("("),
    RPAREN(")"),
    EOS(null)
}