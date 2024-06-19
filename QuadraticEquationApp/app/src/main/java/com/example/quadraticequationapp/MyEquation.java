package com.example.quadraticequationapp;

import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.quadraticequationapp.databinding.ActivityMainBinding;

// extending base observable provides the notify property changed method
// property changed method - allows to notify the data binding library when a property has changed
public class MyEquation extends BaseObservable {
    // always make your variables as string in order to avoid any sort of confusion
    String a;
    String b;
    String c;
    ActivityMainBinding binding;

    public MyEquation(ActivityMainBinding binding) {
        this.binding = binding;
    }

    public MyEquation() {
    }

    @Bindable // signify to data binding to generate necessary code to observe changes for a
    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    @Bindable // signify to data binding to generate necessary code to observe changes for a
    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    @Bindable // signify to data binding to generate necessary code to observe changes for a
    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public void solveEquation(View view){
        int a = Integer.parseInt(getA());
        int b = Integer.parseInt(getB());
        int c = Integer.parseInt(getC());

        double disc = b*b-4*a*c;

        double x1,x2;

        if(disc>0){
            x1 = (-b + Math.sqrt(disc))/2*a;
            x2 = (-b - Math.sqrt(disc))/2*a;

            // display results in textView

            binding.textView.setText("X1 = " +x1+" ; X2 = " + x2);
        } else if (disc<0) {
            binding.textView.setText("No real roots (Complex roots!!)");
            
        }
        else{
            x1 = (double)-b/(2*a);
            binding.textView.setText("X = " + x1);
        }


    }
}
