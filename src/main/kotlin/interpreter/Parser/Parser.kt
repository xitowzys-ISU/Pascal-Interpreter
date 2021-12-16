package interpreter.Parser

import interpreter.Lexer.Lexer
import interpreter.Lexer.Token
import interpreter.Lexer.TokenType
import interpreter.Parser.BinOp.BinOp
import interpreter.Parser.BinOp.BinOpNode
import interpreter.Parser.BinOp.Num
import interpreter.Parser.BinOp.UnaryOp

class Parser(text: String) {
    private var lexer: Lexer = Lexer(text)
    private var currentToken: Token? = lexer.getNextToken()

    private fun eat() {
        currentToken = lexer.getNextToken()

        if (currentToken == null) throw ParserException("Error parsing input")
    }

    /**
     * [factor] : INTEGER | LPAREN expr RPAREN"
     */
    private fun factor(): BinOp {
        val token = currentToken

        if (token!!.type == TokenType.PLUS) {
            eat()
            val node = UnaryOp(token, factor())
            return node
        }

        if (token!!.type == TokenType.MINUS) {
            eat()
            val node = UnaryOp(token, factor())
            return node
        }

        if (token!!.type == TokenType.LPAREN) {
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
    private fun term(): BinOp {
        var node: BinOp = factor()

        while (currentToken!!.type == TokenType.MUL || currentToken!!.type == TokenType.DIV) {
            val token = currentToken

            when (token!!.type) {
                TokenType.MUL -> {
                    eat()
                }
                TokenType.DIV -> {
                    eat()
                }
                else -> {
                }
            }

            node = BinOpNode(left = node, op = token, right = factor())
        }

        return node
    }

    /**
     * [term] ((PLUS | MINUS) [term])*
     */
    private fun expr(): BinOp {
//        eat()

        var node: BinOp = term()

        while (currentToken!!.type == TokenType.PLUS || currentToken!!.type == TokenType.MINUS) {
            val token = currentToken

            when (token!!.type) {
                TokenType.PLUS -> {
                    eat()
                }
                TokenType.MINUS -> {
                    eat()
                }
                else -> {
                }
            }

            node = BinOpNode(left = node, op = token, right = term())
        }

        return node
    }

    fun parse(): BinOp {
        return expr()
    }
}