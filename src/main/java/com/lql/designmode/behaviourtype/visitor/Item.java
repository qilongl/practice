package com.lql.designmode.behaviourtype.visitor;

/**
 * Created by StrangeDragon on 2019/5/16 10:43
 **/
public class Item implements Element {
    private String name;

    public String getName() {
        return name;
    }

    public Item(String name) {
        this.name = name;
    }


    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
