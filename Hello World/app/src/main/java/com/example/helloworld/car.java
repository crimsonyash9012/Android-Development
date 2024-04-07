package com.example.helloworld;

public class car {
    public int year;
    protected int speed;

    public car(int speed, int year){
        this.speed = speed;
        this.year = year;
    }
    protected void accelerate(){
        speed+=10;
        System.out.printf("the new speed is %d", speed);
    }
    public void brake(){
        speed-=5;
        System.out.printf("the new speed is %d", speed);
    }

    public static void main(String[] args) {
        car redCar = new car(10,9090);
        redCar.speed = 90;
        redCar.accelerate();
    }

}
