package interpreter.Parser.AST

import interpreter.Lexer.Token.Token

open class AST(val left: AST?, val op: Token?, val right: AST?)