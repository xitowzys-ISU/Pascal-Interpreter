package interpreter.Lexer.Token.Enums

import interpreter.Lexer.Token.Interfaces.ITokenType

enum class HardKeywords(val value: String) : ITokenType {
    BEGIN("BEGIN"),
    END("END")
}