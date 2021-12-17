package interpreter.Parser.BinOp

import interpreter.Lexer.Token.Token
import interpreter.Parser.AST

open class BinOp(val left: BinOp?, val op: Token?, val right: BinOp?) : AST()