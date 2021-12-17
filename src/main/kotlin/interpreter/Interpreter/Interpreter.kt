package interpreter.Interpreter

import interpreter.Lexer.Token.Enums.ArithmeticOperators
import interpreter.Parser.AST.*
import interpreter.Parser.Parser

class Interpreter(private val text: String) {
    private lateinit var parser: Parser
    var globalScope = mutableMapOf<String, Any>()

    private fun visit(node: AST?): Any {

        when (node!!::class.simpleName) {
            "BinOp" -> return visitBinOp(node as BinOp)
            "UnaryOp" -> return visitUnaryOp(node as UnaryOp)
            "Compound" -> {
                visitCompound(node as Compound)
                return 0
            }
            "NoOp" -> {
                visitNoOp(node as NoOp)
                return 0
            }
            "Assign" -> {
                visitAssign(node as Assign)
                return 0
            }
            "Var" -> return visitVar(node as Var)!!
        }

        return visitNum(node as Num)
    }

    private fun visitCompound(node: Compound) {
        for (child in node.children) {
            visit(child)
        }
    }

    private fun visitNoOp(node: NoOp) {}

    private fun visitAssign(node: Assign) {
        val varName: String? = node.left!!.op!!.value
        if (varName != null) {
            globalScope[varName] = visit(node.right!!)
        }
    }

    private fun visitVar(node: Var): Any? {
        val varName: String? = node.op!!.value
        val vals = globalScope[varName]
        if (vals == null) {
            throw InterpreterException("Error interpreter")
        } else {
            return vals
        }

    }

    private fun visitBinOp(node: BinOp): Int {
        if (node.op!!.type == ArithmeticOperators.PLUS) {
            return visit(node.left) as Int + visit(node.right) as Int
        } else if (node.op.type == ArithmeticOperators.MINUS) {
            return visit(node.left) as Int - visit(node.right) as Int
        } else if (node.op.type == ArithmeticOperators.MUL) {
            return visit(node.left) as Int * visit(node.right) as Int
        }

        return visit(node.left) as Int / visit(node.right) as Int
    }

    private fun visitUnaryOp(node: UnaryOp): Int {
        if (node.op!!.type == ArithmeticOperators.PLUS) {
            return +(visit(node.expr) as Int)
        }

        return -(visit(node.expr) as Int)
    }

    private fun visitNum(node: Num): Int {
        return node.token.value!!.toInt()
    }

    fun interpret(): Any {
        parser = Parser(text)
        val tree = parser.parse()
        return visit(tree)
    }
}