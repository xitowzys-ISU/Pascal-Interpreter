package interpreter.Lexer.Token.Enums

import interpreter.Lexer.Token.Interfaces.ITokenType

enum class SpecialSymbols(val value: String) : ITokenType {
    ASSIGN(":="),
    SEMI(";"),
    DOT(".")
}