package com.lql.designmode.behaviourtype.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by StrangeDragon on 2019/5/16 13:37
 **/
public class CustomerGroup implements Element {
    private List<Customer> customers = new ArrayList<>();


    void addCustomer(Customer customer) {
        customers.add(customer);
    }

    @Override
    public void accept(Visitor visitor) {
        for (Customer customer : customers) {
            customer.accept(visitor);
        }
    }
}
