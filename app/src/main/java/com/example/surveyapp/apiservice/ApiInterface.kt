package com.example.surveyapp.apiservice

import com.example.data.requests.UploadAnswerRequest
import com.example.data.response.*
import com.example.survey.RegisterResponse
import com.example.surveyapp.LoginResponse
import com.example.surveyapp.QuestionWithOptionResponse2
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
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

    @Multipart
    @POST("survey/api/surveyer/updateQuestionAnswerApp")
        fun getAnswersUploadPost(@Part("loginuserID") loginuserID: RequestBody,
                                 @Part("onlineExamID") onlineExamID: RequestBody,
                            // @Query("answer") answer: List<HashMap<String,String>>,
                                 @PartMap map: Map<String,String>,

                                 @Part("full_name") full_name: RequestBody,
                                 @Part("gender") gender: RequestBody,
                                 @Part("phone") phone: RequestBody,
                                 @Part("age") age: RequestBody,
                                 @Part("address") address: RequestBody,
                                // @Part("files") files:RequestBody,
                                 @Part files: MultipartBody.Part

    ): Single<UpdateAnswerAppResponse>

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
    fun getShow(
        @Query("loginuserID") loginuserID: String,
        @Query("onlineExamID") onlineExamID: String
    ): Single<ShowResponse2>

  @GET("survey/api/surveyer/getSurveyListSurveyer")
    fun getUploadedSurveyList(
        @Query("loginuserID") loginuserID: String
    ): Single<UploadedSurveyListResponse>

 @GET("survey/api/surveyer/getSurveyListDetail")
    fun getUploadedSurveyQuestion(
        @Query("surveyId") loginuserID: String
    ): Single<UploadedSurveyQuestionResponse>


    @Multipart
    @POST("apiPublic/testMukul")
    fun getMukulAudio(
        @Part audio: MultipartBody.Part
    ): Single<Any>


}