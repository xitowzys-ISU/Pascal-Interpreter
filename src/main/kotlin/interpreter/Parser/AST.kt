package interpreter.Parser

import interpreter.Lexer.Token.Token

open class AST(val left: AST?, val op: Token?, val right: AST?)

class Assign(left: AST, op: Token, right: AST) : AST(left, op, right)

class BinOp(left: AST, op: Token?, right: AST?) : AST(left, op, right)

class NoOp : AST(null, null, null)

class Num(val token: Token) : AST(null, token, null)

class UnaryOp(val token: Token, val expr: AST) : AST(null, token, null)

class Var(val token: Token) : AST(null, token, null)

class Compound() : AST(null, null, null) {
    val children: ArrayList<AST> = arrayListOf()
}