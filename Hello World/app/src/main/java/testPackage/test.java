package testPackage;

import com.example.helloworld.car;

public class test {
    void justTesting(){
        car yellowCar = new car(180,1990);
        yellowCar.year = 10;
//        yellowCar.speed = 12;
        System.out.print(yellowCar.year);
    }

    public static void main(String[] args) {
        test a = new test();
        a.justTesting();
    }
}
