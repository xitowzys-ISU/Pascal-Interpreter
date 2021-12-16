package interpreter.Parser.BinOp

import interpreter.Lexer.Token

class UnaryOp(val token: Token, val expr: BinOp) : BinOp(null, token, null) {

}