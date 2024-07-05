package com.example.thequizapp.retrofit;

import com.example.thequizapp.model.QuestionList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuestionsAPI {
    //used to define the structure & behavior
    //of network requests to a restful api
    //acts as a bridge between android app and web service

    @GET("myquizapi.php")
    // call used for wrap network request and represent results
    Call<QuestionList> getQuestions();



}
