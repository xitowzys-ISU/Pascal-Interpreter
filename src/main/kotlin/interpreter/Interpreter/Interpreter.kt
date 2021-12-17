package interpreter.Interpreter

import interpreter.Lexer.Token.Enums.ArithmeticOperators
import interpreter.Parser.AST.AST
import interpreter.Parser.AST.BinOp
import interpreter.Parser.AST.Num
import interpreter.Parser.AST.UnaryOp
import interpreter.Parser.Parser

class Interpreter(private val text: String) {
    private lateinit var parser: Parser

    private fun visit(node: AST?): Int {

        when (node!!::class.simpleName) {
            "BinOp" -> {
                return visitBinOp(node as BinOp)
            }
            "UnaryOp" -> {
                return visitUnaryOp(node as UnaryOp)
            }
        }

        return visitNum(node as Num)
    }

    private fun visitBinOp(node: BinOp): Int {
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
        parser = Parser(text)
        val tree = parser.parse()
        return visit(tree)
    }
}