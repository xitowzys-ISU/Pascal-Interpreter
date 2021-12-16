package interpreter.Interpreter

import interpreter.Lexer.TokenType
import interpreter.Parser.BinOp.BinOp
import interpreter.Parser.BinOp.BinOpNode
import interpreter.Parser.BinOp.Num
import interpreter.Parser.Parser

class Interpreter(text: String) {
    private var parser: Parser = Parser(text)

    private fun visit(node: BinOp?): Int {

        when (node!!::class.simpleName) {
            "BinOpNode" -> {
                return visitBinOp(node as BinOpNode)
            }
        }

        return visitNum(node as Num)
    }

    private fun visitBinOp(node: BinOpNode): Int {
        if (node.op!!.type == TokenType.PLUS) {
            return visit(node.left) + visit(node.right)
        } else if (node.op.type == TokenType.MINUS) {
            return visit(node.left) - visit(node.right)
        } else if (node.op.type == TokenType.MUL) {
            return visit(node.left) * visit(node.right)
        }

        return visit(node.left) / visit(node.right)
    }

    private fun visitNum(node: Num): Int {
        return node.token.value!!.toInt()
    }

    fun interpret(): Int {
        val tree = parser.parse()
        return visit(tree)
    }
}