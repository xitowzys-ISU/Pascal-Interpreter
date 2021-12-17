package interpreter.Parser.AST

import interpreter.Lexer.Token.Token

open class BinOp(left: AST, op: Token?, right: AST?) : AST(left, op, right)