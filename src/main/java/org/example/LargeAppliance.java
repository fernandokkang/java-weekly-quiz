package org.example;

public class LargeAppliance extends Product implements DeliveryChargeCalculator {

    LargeAppliance(String name, int weight, int price) {
        super(name, weight, price);
    }

    @Override
    public int getDeliveryPrice(int weight, int price) {

        return calculateDeliveryPrice(weight, price);
    }
}
