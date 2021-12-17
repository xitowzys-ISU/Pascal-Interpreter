package interpreter.Parser.BinOp

import interpreter.Lexer.Token.Token

class UnaryOp(val token: Token, val expr: BinOp) : BinOp(null, token, null) {

}