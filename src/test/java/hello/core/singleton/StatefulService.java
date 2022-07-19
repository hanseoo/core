package hello.core.singleton;

public class StatefulService {

//    private int price; // 상태를 유지하는 필드

    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
//        this.price = price; //여기가 문제
        return price; // order 주문시 바로 return함으로서 해결이 가능하다.
    }

//    public int getPrice() {
//        return price;
//    }
}
