package org.example;

public class Product {
    String name;
    int weight;
    int price;

    Product(String name, int weight, int price) {
        this.name = name;
        this.weight = weight;
        this.price = price;
    }

    int calculateDeliveryPrice(int weight, int price) {

        if(price < 30000) {
            return calculateByWeight(weight);
        }

        if(price >= 30000 && price < 100000) {
            return calculateByWeight(weight) - 1000;
        }

        return 0;
    }

    private int calculateByWeight(int weight) {

        if(weight < 3) {
            return 1000;
        }

        if(weight >= 3 && weight < 10) {
            return 5000;
        }

        return 10000;
    }
}
