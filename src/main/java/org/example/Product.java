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
    public int getDeliveryPrice(double weight, int price) {

        return calculateDeliveryPrice(weight, price);
    }

    private int calculateDeliveryPrice(double weight, int price) {

        if(price < 30000) {
            return calculateByWeight(weight);
        }

        if(price >= 30000 && price < 100000) {
            return calculateByWeight(weight) - 1000;
        }

        return 0;
    }

    private int calculateByWeight(double weight) {

        if(weight < 3.0) {
            return 1000;
        }

        if(weight >= 3.0 && weight < 10.0) {
            return 5000;
        }

        return 10000;
    }
}
