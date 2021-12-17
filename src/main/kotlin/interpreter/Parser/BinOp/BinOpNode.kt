package interpreter.Parser.BinOp

import interpreter.Lexer.Token.Token

class BinOpNode(left: BinOp?, op: Token?, right: BinOp?) : BinOp(left, op, right) {
//    override fun toString(): String {
//        return "$left <- $op -> $right"
//    }
}