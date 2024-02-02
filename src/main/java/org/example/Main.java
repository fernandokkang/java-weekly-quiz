package org.example;

public class Main {

    static class Product {

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

    static class Beauty extends Product implements DeliveryChargeCalculator{

        Beauty(String name, int weight, int price) {
            super(name, weight, price);
        }

        @Override
        public int getDeliveryPrice(int weight, int price) {
            return calculateDeliveryPrice(weight, price);
        }
    }

    static class Grocery extends Product implements DeliveryChargeCalculator{

        Grocery(String name, int weight, int price) {
            super(name, weight, price);
        }

        @Override
        public int getDeliveryPrice(int weight, int price) {
            return calculateDeliveryPrice(weight, price);
        }
    }

    static class LargeAppliance extends Product implements DeliveryChargeCalculator{

        LargeAppliance(String name, int weight, int price) {
            super(name, weight, price);
        }

        @Override
        public int getDeliveryPrice(int weight, int price) {
            return calculateDeliveryPrice(weight, price);
        }
    }

    interface DeliveryChargeCalculator {

        int getDeliveryPrice(int weight, int price);
    }

    public static void main(String[] args) {

        Beauty beauty = new Beauty("향수", 5, 55000);
        Grocery grocery = new Grocery("소고기", 10, 200000);
        LargeAppliance largeAppliance = new LargeAppliance("노트북", 4, 2000000);

        System.out.println("화장품 배송 가격은? "+beauty.getDeliveryPrice(beauty.weight, beauty.price)+"원");
        System.out.println("식료품 배송 가격은? "+grocery.getDeliveryPrice(grocery.weight, grocery.price)+"원");
        System.out.println("가전 제품 배송 가격은? "+largeAppliance.getDeliveryPrice(largeAppliance.weight, largeAppliance.price)+"원");
    }
}