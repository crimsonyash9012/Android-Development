package com.example.grocerryapp_recyclerview;

public class item {
    int itemImg;
    String itemName, itemDesc;

    public item(int itemImg, String itemName, String itemDesc) {
        this.itemImg = itemImg;
        this.itemName = itemName;
        this.itemDesc = itemDesc;
    }

    public int getItemImg() {
        return itemImg;
    }

    public void setItemImg(int itemImg) {
        this.itemImg = itemImg;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }
}
