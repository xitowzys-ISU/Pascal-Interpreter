package interpreter.Parser.AST

import interpreter.Lexer.Token.Token

class Assign(left: AST, op: Token, right: AST) : AST(left, op, right)