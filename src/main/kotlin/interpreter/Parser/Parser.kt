package interpreter.Parser

import interpreter.Lexer.Lexer
import interpreter.Lexer.Token.Token
import interpreter.Lexer.Token.Enums.ArithmeticOperators
import interpreter.Lexer.Token.Enums.General
import interpreter.Lexer.Token.Enums.HardKeywords
import interpreter.Lexer.Token.Enums.SpecialSymbols

class Parser(text: String) {
    private var lexer: Lexer = Lexer(text)
    private var currentToken: Token? = lexer.getNextToken()

    private fun callException() {
        throw ParserException("Error parsing")
    }

    private fun eat() {
        currentToken = lexer.getNextToken()

        if (currentToken == null) callException()
    }

    /**
     * Grammar: "[program] : [compoundStatement] DOT"
     */
    private fun program(): AST {
        val node = compoundStatement()
        eat()
        return node
    }

    /**
     * Grammar: "[compoundStatement]: BEGIN [statementList] END"
     */
    private fun compoundStatement(): AST {
        eat()
        val nodes = statementList()
        eat()

        val root = Compound()

        for (node in nodes) {
            root.children.add(node)
        }

        return root
    }

    /**
     * Grammar: "[statementList] : [statement] | [statement] SEMI [statementList]"
     */
    private fun statementList(): ArrayList<AST> {
        val node = statement()
        val results = arrayListOf(node)

        while (currentToken!!.type == SpecialSymbols.SEMI) {
            eat()
            results.add(statement())
        }

        if (currentToken!!.type == General.ID) callException()

        return results
    }

    /**
     * Grammar: "[statement] : [compoundStatement] | [assignmentStatement] | [empty]"
     */
    private fun statement(): AST {
        val node: AST

        if (currentToken!!.type == HardKeywords.BEGIN) {
            node = compoundStatement()
        } else if (currentToken!!.type == General.ID) {
            node = assignmentStatement()
        } else {
            node = empty()
        }

        return node
    }

    /**
     * Grammar: [assignmentStatement] : [variable] ASSIGN [expr]
     */
    private fun assignmentStatement(): AST {
        val left = variable()
        val token = currentToken
        eat()
        val right = expr()
        return Assign(left, token!!, right)
    }

    /**
     * Grammar: "[variable] : ID"
     */
    private fun variable(): AST {
        val node = Var(currentToken!!)
        eat()
        return node
    }

    /**
     * Grammar: "[empty] : NoOp"
     */
    private fun empty(): AST {
        return NoOp()
    }

    /**
     * [factor] : INTEGER | LPAREN expr RPAREN"
     */
    private fun factor(): AST {
        val token = currentToken

        if (token!!.type == ArithmeticOperators.PLUS) {
            eat()
            val node = UnaryOp(token, factor())
            return node
        } else if (token.type == ArithmeticOperators.MINUS) {
            eat()
            val node = UnaryOp(token, factor())
            return node
        } else if (token.type == ArithmeticOperators.LPAREN) {
            eat()
            val node = expr()
            eat()
            return node
        } else if (token.type == ArithmeticOperators.INTENGER) {
            eat()
            return Num(token)
        } else {
            return variable()
        }
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
        val node = program()
        if (currentToken!!.type != ArithmeticOperators.EOS) callException()
        return node
    }
}