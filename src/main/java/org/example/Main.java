package org.example;

public class Main {
    public static void main(String[] args) {

        Beauty beauty = new Beauty("향수", 3.1, 55000);
        Grocery grocery = new Grocery("소고기", 10.1, 200000);
        LargeAppliance largeAppliance = new LargeAppliance("노트북", 4, 2000000);

        System.out.println("화장품 배송 가격은? "+beauty.getDeliveryPrice()+"원");
        System.out.println("식료품 배송 가격은? "+grocery.getDeliveryPrice()+"원");
        System.out.println("가전 제품 배송 가격은? "+largeAppliance.getDeliveryPrice()+"원");
    }
}