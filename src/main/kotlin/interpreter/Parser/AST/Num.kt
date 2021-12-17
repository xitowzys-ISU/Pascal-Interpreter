package interpreter.Parser.AST

import interpreter.Lexer.Token.Token

class Num(val token: Token) : AST(null, token, null) {
}