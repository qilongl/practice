package com.lql.designmode.behaviourtype.iterator;

/**
 * Created by StrangeDragon on 2019/5/15 15:58
 **/
public class Client {
    public static void main(String[] args) {
        Aggregate aggregate = new ConcreteAggregate();
        Iterator<Integer> iterator = aggregate.createIterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}

