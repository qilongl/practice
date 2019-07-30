package com.lql.designmode.behaviourtype.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by StrangeDragon on 2019/5/16 10:42
 **/
public class Order implements Element {

    private String name;
    private List<Item> items = new ArrayList<>();

    public Order(String name) {
        this.name = name;
    }

    public Order(String name, String itemName) {
        this.name = name;
        addItem(itemName);
    }

    void addItem(String name) {
        items.add(new Item(name));
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
        for (Item item : items) {
            item.accept(visitor);
        }

    }

    public String getName() {
        return name;
    }
}
