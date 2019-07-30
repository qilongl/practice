package com.lql.designmode.behaviourtype.visitor;

/**
 * Created by StrangeDragon on 2019/5/16 11:22
 **/
public class GeneralReport implements Visitor {
    private int customersNo;
    private int orderwsNo;
    private int itemsNo;

    @Override
    public void visit(Customer customer) {
        System.out.println(customer.getName());
        customersNo++;
    }

    @Override
    public void visit(Order order) {
        System.out.println(order.getName());
        orderwsNo++;
    }

    @Override
    public void visit(Item item) {
        System.out.println(item.getName());
        itemsNo++;
    }

    public void displayResults() {
        System.out.println("Customer: " + customersNo);
        System.out.println("Order: " + orderwsNo);
        System.out.println("Item: " + itemsNo);
    }
}
