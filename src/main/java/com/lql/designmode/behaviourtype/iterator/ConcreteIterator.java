package com.lql.designmode.behaviourtype.iterator;

/**
 * Created by StrangeDragon on 2019/5/15 15:49
 **/
public class ConcreteIterator<Item> implements Iterator {
    private Item[] items;
    private int position = 0;

    public ConcreteIterator(Item[] items) {
        this.items = items;
    }

    @Override
    public Object next() {
        return items[position++];
    }

    @Override
    public boolean hasNext() {
        return position < items.length;
    }
}
