package interpreter.Parser

import interpreter.Lexer.Lexer
import interpreter.Lexer.Token.Token
import interpreter.Lexer.Token.Enums.ArithmeticOperators
import interpreter.Parser.AST.AST
import interpreter.Parser.AST.BinOp
import interpreter.Parser.AST.Num
import interpreter.Parser.AST.UnaryOp

class Parser(text: String) {
    private var lexer: Lexer = Lexer(text)
    private var currentToken: Token? = lexer.getNextToken()

    private fun eat() {
        currentToken = lexer.getNextToken()

        if (currentToken == null) throw ParserException("Error parsing input")
    }

    /**
     * Grammar: "[program] : [compoundStatement] DOT"
     */
    private fun program() {}

    /**
     * Grammar: "[compoundStatement]: BEGIN [statementList] END"
     */
    private fun compoundStatement() {}

    /**
     * Grammar: "[statementList] : [statement] | [statement] SEMI [statementList]"
     */
    private fun statementList() {}

    /**
     * Grammar: "[statement] : [compoundStatement] | [assignmentStatement] | [empty]"
     */
    private fun statement() {}

    /**
     * Grammar: [assignmentStatement] : [variable] ASSIGN [expr]
     */
    private fun assignmentStatement() {}

    /**
     * Grammar: "[variable] : ID"
     */
    private fun variable() {}

    /**
     * Grammar: "[empty] : NoOp"
     */
    private fun empty() {}

    /**
     * [factor] : INTEGER | LPAREN expr RPAREN"
     */
    private fun factor(): AST {
        val token = currentToken

        if (token!!.type == ArithmeticOperators.PLUS) {
            eat()
            val node = UnaryOp(token, factor())
            return node
        }

        if (token!!.type == ArithmeticOperators.MINUS) {
            eat()
            val node = UnaryOp(token, factor())
            return node
        }

        if (token!!.type == ArithmeticOperators.LPAREN) {
            eat()
            val node = expr()
            eat()
            return node
        }

        eat()
        return Num(token)
    }

    /**
     * [term] : [factor] ((MUL | DIV) [factor])*
     */
    private fun term(): AST {
        var node: AST = factor()

        while (currentToken!!.type == ArithmeticOperators.MUL || currentToken!!.type == ArithmeticOperators.DIV) {
            val token = currentToken

            when (token!!.type) {
                ArithmeticOperators.MUL -> {
                    eat()
                }
                ArithmeticOperators.DIV -> {
                    eat()
                }
                else -> {
                }
            }

            node = BinOp(left = node, op = token, right = factor())
        }

        return node
    }

    /**
     * [term] ((PLUS | MINUS) [term])*
     */
    private fun expr(): AST {
        var node: AST = term()

        while (currentToken!!.type == ArithmeticOperators.PLUS || currentToken!!.type == ArithmeticOperators.MINUS) {
            val token = currentToken

            when (token!!.type) {
                ArithmeticOperators.PLUS -> {
                    eat()
                }
                ArithmeticOperators.MINUS -> {
                    eat()
                }
                else -> {
                }
            }

            node = BinOp(left = node, op = token, right = term())
        }

        return node
    }

    fun parse(): AST {
        return expr()
    }
}