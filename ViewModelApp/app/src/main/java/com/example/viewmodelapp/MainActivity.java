package com.example.viewmodelapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviderGetKt;

import android.os.Bundle;
import android.view.View;

import com.example.viewmodelapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    MyViewModel viewModel;
    ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);


        // creates a view model provider instance
        // and retreives or creates a view model instance of the given class
        viewModel = new ViewModelProvider(this)
                .get(MyViewModel.class);

        mainBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.increaseCounter();
//                if below line not implemented => no change in counter
//                to solve it, we use live data
//                mainBinding.counter.setText(""+viewModel.getCounter());
            }
        });


        // observing the live data

        viewModel.getCounter().observe(this,
                new Observer<Integer>() {
                    @Override
                    public void onChanged(Integer counter) {
                        mainBinding.counter.setText(""+counter);
                    }
                });
    }
}