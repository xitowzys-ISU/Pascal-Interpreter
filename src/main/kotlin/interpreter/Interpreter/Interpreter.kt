package interpreter.Interpreter

import interpreter.Lexer.Token.Enums.ArithmeticOperators
import interpreter.Parser.BinOp.BinOp
import interpreter.Parser.BinOp.BinOpNode
import interpreter.Parser.BinOp.Num
import interpreter.Parser.BinOp.UnaryOp
import interpreter.Parser.Parser

class Interpreter(text: String) {
    private var parser: Parser = Parser(text)

    private fun visit(node: BinOp?): Int {

        when (node!!::class.simpleName) {
            "BinOpNode" -> {
                return visitBinOp(node as BinOpNode)
            }
            "UnaryOp" -> {
                return visitUnaryOp(node as UnaryOp)
            }
        }

        return visitNum(node as Num)
    }

    private fun visitBinOp(node: BinOpNode): Int {
        if (node.op!!.type == ArithmeticOperators.PLUS) {
            return visit(node.left) + visit(node.right)
        } else if (node.op.type == ArithmeticOperators.MINUS) {
            return visit(node.left) - visit(node.right)
        } else if (node.op.type == ArithmeticOperators.MUL) {
            return visit(node.left) * visit(node.right)
        }

        return visit(node.left) / visit(node.right)
    }

    private fun visitUnaryOp(node: UnaryOp): Int {
        if (node.op!!.type == ArithmeticOperators.PLUS) {
            return +visit(node.expr)
        }

        return -visit(node.expr)
    }

    private fun visitNum(node: Num): Int {
        return node.token.value!!.toInt()
    }

    fun interpret(): Int {
        val tree = parser.parse()
        return visit(tree)
    }
}