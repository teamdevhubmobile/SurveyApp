package com.example.surveyapp.apiservice

import com.example.survey.RegisterResponse
import com.example.surveyapp.response.ForgetPasswordResponse
import com.example.surveyapp.LoginResponse
import com.example.surveyapp.QuestionWithOptionResponse2
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("survey/api/surveyer/survey_list")
    fun getSurveyList(
    ): Single<RegisterResponse>

    @GET("survey/api/surveyer/login")
     fun getLogin(@Query("username") username: String,
                         @Query("password") password: String): Single<LoginResponse>

    @GET("survey/api/surveyer/forget_password")
    fun getForgetPassword(@Query("username") username: String,
                 @Query("cpassword") cpassword: String): Single<ForgetPasswordResponse>

 /*@GET("survey/api/surveyer/survey_question")
    fun getQuestions(@Query("surveyId") surveyId: String): Single<QuestionsResponse>
*/
@GET("survey/api/surveyer/getQuestionWithOption")
    fun getQuestions(@Query("surveyId") surveyId: String): Single<QuestionWithOptionResponse2>


}