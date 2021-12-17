package interpreter.Parser.AST

import interpreter.Lexer.Token.Token

class UnaryOp(val token: Token, val expr: AST) : AST(null, token, null) {

}