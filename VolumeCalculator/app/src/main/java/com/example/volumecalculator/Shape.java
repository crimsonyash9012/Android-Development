package com.example.volumecalculator;

// This class is acting as: "Model Class"
// it represents the data structure or individual data items
// that your adapter is going to display

public class Shape {
    int shapeImg;
    String shapeName;

    public Shape(int shapeImg, String shapeName) {
        this.shapeImg = shapeImg;
        this.shapeName = shapeName;
    }

    public int getShapeImg() {
        return shapeImg;
    }

    public void setShapeImg(int shapeImg) {
        this.shapeImg = shapeImg;
    }

    public String getShapeName() {
        return shapeName;
    }

    public void setShapeName(String shapeName) {
        this.shapeName = shapeName;
    }
}
