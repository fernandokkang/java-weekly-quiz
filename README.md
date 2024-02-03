# 과제 - 위클리 2주차

## 🚀 과제 내용


> 💡 조건문, 상속, 인터페이스 구현 Quiz


### <구현 범위>

1. 클래스와 인터페이스 선언, 각 메소드와 필드 선언
2. 그림에 나온 리턴 타입과 다른 리턴 타입이어도 괜찮습니다.
    - getDeliveryCharge() 메소드의 리턴 타입은 double 혹은 int로 구현 OK
3. (Optional) 기능 세부 구현
4. (Optional) BigDecimal 사용은 심화 버전 입니다. 참고 자료를 보고 적용하는 것도 괜찮습니다.


> 해당 클래스 설계와 아래 설명을 만족하는 기능을 구현하세요
  
```
    쇼핑몰에서 판매하는 상품 종류는 아래와 같습니다.
    
    1. 식료품
    2. 화장품
    3. 대형가전
    
    각 상품들은 무게 / 크기 / 가격 속성을 가지고 있습니다.
    
    상품들은 무게 단위와 가격에 따라 아래 표에 따라 배송비가 측정됩니다.
    -------------------------------------
    무게           |       배송비
    -------------------------------------
    3kg 미만       |       1천원
    -------------------------------------
    3kg ~ 10kg미만 |       5천원
    -------------------------------------
    10kg 이상      |       1만원
    -------------------------------------
    
    무게 단위로 측정된 배송비들은 아래와 같은 상품 가격에 따라 추가 적용됩니다.
    -------------------------------------
    가격           |       배송비
    -------------------------------------
    3만원 미만      | 무게 단위 배송비 적용
    -------------------------------------
    3 ~ 10만원 미만 | 배송비 1000원 할인
    -------------------------------------
    10만원 이상     | 배송비 무료
    -------------------------------------
```
---

### 과제를 진행하며 생각해본 것

1. 배송료 산정 방식이 바뀌게 되면 어떻게 대응을 할 것인가?
   ```java
      public interface DeliveryChargeCalculator {
         int getDeliveryPrice(double weight, int price);
      }
   ```
   초기 코드는 배송료 산정을 위해 제품의 무게와 가격이 필요하다. 
   만약 배송료 산정이 제품 무게와 가격과 상관없이 제품의 제조사나 제조 일자만을 가지고 산정을 하는 방식으로 변경이 된다면 
   위 메소드를 사용하여 배송료를 산정하고 있는 부분을 모두 변경해야 할 것이다.

   ```java
      public interface DeliveryChargeCalculator {
         int getDeliveryPrice();
      }
   ```
   이렇게 하면 배송료 산정 로직이 변경된 경우에도 `Procuct` 내부 내용만 수정하면 되기 때문에 
   `Product` 제외한 코드들은 건드리지 않고 `Product`에게 배송료 산정을 요청할 수 있다. <br><br>

2. 현재 요구 사항에서는 상품 각각 배송료 산정을 하고 있는데, 총 금액 혹은 총 무게에 따라 배송료를 산정하기 위해 
   `Cart`가 추가 된다면 어떻게 될 것인가? 
   ```java
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
   ```
   초기 코드에서는 배송료 계산을 `Product`에서 했는데, 배송료 산정 방식의 변경으로 `Cart`가 추가가 된다면
   해당 로직의 코드가 `Cart`에서도 사용이 되어 중복 코드가 생기기 때문에 배송료 계산을 `Calculator` 클래스에서 하도록 하였다. 
   ```java
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
   ```
   ```java
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
   ```
   코드를 위와 같이 분리를 하니 눈에 한번에 들어와서 가독성도 좋아지고, 코드의 확장성도 좋아졌다.