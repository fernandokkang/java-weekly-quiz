package org.example;

public class Beauty extends Product implements DeliveryChargeCalculator {

    Beauty(String name, int weight, int price) {
        super(name, weight, price);
    }

    @Override
    public int getDeliveryPrice(int weight, int price) {
        return calculateDeliveryPrice(weight, price);
    }
}