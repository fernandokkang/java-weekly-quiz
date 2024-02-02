package org.example;

public class Grocery extends Product implements DeliveryChargeCalculator {

    Grocery(String name, int weight, int price) {
        super(name, weight, price);
    }

    @Override
    public int getDeliveryPrice(int weight, int price) {
        return calculateDeliveryPrice(weight, price);
    }
}

