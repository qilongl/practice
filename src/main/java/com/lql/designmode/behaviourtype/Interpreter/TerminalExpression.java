package com.lql.designmode.behaviourtype.Interpreter;

import java.util.StringTokenizer;

/**
 * Created by StrangeDragon on 2019/5/15 14:12
 **/
public class TerminalExpression extends Expression {
    private String literal = null;

    public TerminalExpression(String literal) {
        this.literal = literal;
    }


    @Override
    public boolean interpret(String str) {
        StringTokenizer st = new StringTokenizer(str);
        while (st.hasMoreTokens()) {
            String node = st.nextToken();
            if (node.equals(literal)) {
                return true;
            }
        }
        return false;
    }
}
