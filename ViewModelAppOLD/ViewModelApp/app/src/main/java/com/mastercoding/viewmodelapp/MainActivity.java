package com.mastercoding.viewmodelapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.mastercoding.viewmodelapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    MyViewModel viewModel;
    ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_main);


        viewModel = new ViewModelProvider(this)
                .get(MyViewModel.class);

        mainBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.increaseCounter();
                mainBinding.textView2.setText(""+viewModel.getCounter());
            }
        });
        mainBinding.textView2.setText(""+viewModel.getCounter());

    }


}