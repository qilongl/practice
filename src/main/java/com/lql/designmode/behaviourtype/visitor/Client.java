package com.lql.designmode.behaviourtype.visitor;

/**
 * Created by StrangeDragon on 2019/5/16 11:26
 **/
public class Client {
    public static void main(String[] args) {
        Customer customer1 = new Customer("消费者1");
        customer1.addOrder(new Order("订单1", "商品1"));
        customer1.addOrder(new Order("订单2", "商品1"));
        customer1.addOrder(new Order("订单3", "商品1"));

        Customer customer2 = new Customer("消费者2");
        Order order = new Order("订单N");
        order.addItem("商品1");
        order.addItem("商品2");
        order.addItem("商品3");
        customer2.addOrder(order);

        CustomerGroup customerGroup = new CustomerGroup();
        customerGroup.addCustomer(customer1);
        customerGroup.addCustomer(customer2);

        GeneralReport generalReport = new GeneralReport();
        customerGroup.accept(generalReport);
        generalReport.displayResults();
    }
}
