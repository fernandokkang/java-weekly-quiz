package org.example;

public class Product implements DeliveryChargeCalculator {

    String name;
    double weight;
    int price;

    Product(String name, double weight, int price) {
        this.name = name;
        this.weight = weight;
        this.price = price;
    }

    @Override
    public int getDeliveryPrice() {

        Calculator calculator = new Calculator();

        return calculator.calculateDeliveryPrice(weight, price);
    }
}
