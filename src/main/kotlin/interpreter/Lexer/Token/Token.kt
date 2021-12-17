package interpreter.Lexer.Token

import interpreter.Lexer.Token.Interfaces.ITokenType

class Token(val type: ITokenType, val value: String?) {
    override fun toString(): String {
        return "Token($type, $value)"
    }
}