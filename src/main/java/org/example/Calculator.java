package org.example;

public class Calculator {

    public int calculateDeliveryPrice(double weight, int price) {

        if(price < 30000) {
            return calculateByWeight(weight);
        }

        if(price >= 30000 && price < 100000) {
            return calculateByWeight(weight) - 1000;
        }

        return 0;
    }

    public int calculateByWeight(double weight) {

        if(weight < 3.0) {
            return 1000;
        }

        if(weight >= 3.0 && weight < 10.0) {
            return 5000;
        }

        return 10000;
    }
}
