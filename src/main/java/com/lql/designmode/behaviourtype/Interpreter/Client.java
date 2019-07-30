package com.lql.designmode.behaviourtype.Interpreter;

/**
 * Created by StrangeDragon on 2019/5/15 14:31
 **/
public class Client {
    public static Expression buildInterpreterTree() {
        Expression terminal1 = new TerminalExpression("A");
        Expression terminal2 = new TerminalExpression("B");
        Expression terminal3 = new TerminalExpression("C");
        Expression terminal4 = new TerminalExpression("D");
        //A B
        Expression expression1 = new OrExpression(terminal1, terminal2);
        //C OR (A B)
        Expression expression2 = new OrExpression(terminal3, expression1);
        //D AND (C OR (A B))
        Expression expression3 = new AndExpression(terminal4, expression2);


        return expression3;
    }

    public static void main(String[] args) {
        Expression define = buildInterpreterTree();
        String content1 = "A B"; //false
        String content2 = "C D"; //true
        System.out.println(define.interpret(content1));
        System.out.println(define.interpret(content2));
    }
}
