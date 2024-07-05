package com.example.thequizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.thequizapp.databinding.ActivityMainBinding;
import com.example.thequizapp.model.QuestionList;
import com.example.thequizapp.viewmodel.QuizViewModel;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    QuizViewModel quizViewModel;
    List<QuestionList> questionList;
    static int result = 0;
    static int totalQuest = 0;
    int i=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // data binding
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        // instance of QuizViewModel
        quizViewModel = new ViewModelProvider(this) // when create this instance  => pass activity or fragment as a parameter
                .get(QuizViewModel.class);

        // Displaying the 1st question
        DisplayFirstQuestion();

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                DisplayNextQuestions();
            }
        });
    }
    public void DisplayFirstQuestion(){
        //observing live data from view model
        quizViewModel.getQuestionListLiveData().observe(
                this,
                new Observer<QuestionList>() {

                    @Override
                    public void onChanged(QuestionList questions) {
                        // called when data inside LiveData changes
                        questionList = Collections.singletonList(questions);
                        binding.textView1.setText("Question 1" + questions.get(0).getQuestion());
                        binding.radio1.setText(questions.get(0).getOption1());
                        binding.radio2.setText(questions.get(0).getOption2());
                        binding.radio3.setText(questions.get(0).getOption3());
                        binding.radio4.setText(questions.get(0).getOption4());
                    }
                });
    }
}