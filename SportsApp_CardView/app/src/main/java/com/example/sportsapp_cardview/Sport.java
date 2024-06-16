package com.example.sportsapp_cardview;

public class Sport {
    int sportImg;
    String sportName;
    public Sport(int sportImg, String sportName) {
        this.sportImg = sportImg;
        this.sportName = sportName;
    }

    public int getSportImg() {
        return sportImg;
    }

    public void setSportImg(int sportImg) {
        this.sportImg = sportImg;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }
}
