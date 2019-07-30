package com.lql.designmode.behaviourtype.visitor;

/**
 * Created by StrangeDragon on 2019/5/16 10:42
 **/
public interface Visitor {
    void visit(Customer customer);

    void visit(Order order);

    void visit(Item item);
}
