package com.lql.designmode.behaviourtype.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by StrangeDragon on 2019/5/16 10:42
 **/
public class Customer implements Element {
    private String name;
    private List<Order> orders = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    void addOrder(Order order) {
        orders.add(order);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
        for (Order order : orders) {
            order.accept(visitor);
        }
    }

    public String getName() {
        return name;
    }
}
