package interpreter.Parser.AST

import interpreter.Lexer.Token.Token

class Var(val token: Token) : AST(null, token, null)