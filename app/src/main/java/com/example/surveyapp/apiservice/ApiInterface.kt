package com.example.surveyapp.apiservice

import com.example.data.requests.UploadAnswerRequest
import com.example.survey.RegisterResponse
import com.example.data.response.ForgetPasswordResponse
import com.example.surveyapp.LoginResponse
import com.example.surveyapp.QuestionWithOptionResponse2
import com.example.data.response.ShowResponse
import com.example.data.response.UpdateQuestionAnswerResponse
import io.reactivex.Single
import retrofit2.http.*

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

    @FormUrlEncoded
    @POST("survey/api/surveyer/updateQuestionAnswer")
        fun getAnswersUploadPost(@Field("loginuserID") loginuserID: String,
                                 @Field("onlineExamID") onlineExamID: String,
                            // @Query("answer") answer: List<HashMap<String,String>>,
                                 @FieldMap map: Map<String,String>,

                                 @Field("full_name") full_name: String,
                                 @Field("gender") gender: String,
                                 @Field("phone") phone: String,
                                 @Field("age") age: String,
                                 @Field("address") address: String
    ): Single<UpdateQuestionAnswerResponse>

    @GET("survey/api/surveyer/updateQuestionAnswer")
        fun getAnswersUpload(@Query("loginuserID") loginuserID: String,
                             @Query("onlineExamID") onlineExamID: String,
                            // @Query("answer") answer: List<HashMap<String,String>>,
                             @QueryMap map: Map<String,String>,

                             @Query("full_name") full_name: String,
                             @Query("gender") gender: String,
                             @Query("phone") phone: String,
                             @Query("age") age: String,
                             @Query("address") address: String
    ): Single<UpdateQuestionAnswerResponse>


    @POST("survey/api/surveyer/updateQuestionAnswer")
        fun getAnswersUploadBody(
        @Body request: UploadAnswerRequest,
        @FieldMap answer: HashMap<String,String>,
        ): Single<UpdateQuestionAnswerResponse>


    @GET("survey/api/surveyer/show")
    fun getShow(@Query("loginuserID") loginuserID: String,
                 @Query("onlineExamID") onlineExamID: String): Single<ShowResponse>


}