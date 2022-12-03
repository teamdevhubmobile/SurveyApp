package com.example.surveyapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.requests.UploadAnswerRequest
import com.example.data.response.*
import com.example.survey.RegisterResponse
import com.example.surveyapp.apiservice.ApiInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Multipart

class HomeViewModel : ViewModel() {

  //  var apiClient: ApiInterface = retrofitMain.create<ApiInterface>(ApiInterface::class.java)
    var apiClient: ApiInterface = retrofitMain.create<ApiInterface>(ApiInterface::class.java)

    private var loaderLiveData = MutableLiveData<Boolean>()
    private var dataLoadError = MutableLiveData<String>()

  private var loginLiveData = MutableLiveData<LoginResponse>()
  private var surveyLiveData = MutableLiveData<RegisterResponse>()
  private var forgetpasswordLiveData = MutableLiveData<ForgetPasswordResponse>()
  //private var questionsLiveData = MutableLiveData<QuestionsResponse>()
  private var questionsLiveData = MutableLiveData<QuestionWithOptionResponse2>()
  private var answerUploadLiveData = MutableLiveData<Boolean>()
 // private var answerUploadLiveData = MutableLiveData<UpdateAnswerAppResponse>()
  private var showLiveData = MutableLiveData<ShowResponse2>()
  private var uploadedSurveyListLiveData = MutableLiveData<UploadedSurveyListResponse>()
  private var uploadedSurveyQuestionListLiveData = MutableLiveData<UploadedSurveyQuestionResponse>()
  private var audioUploadLiveData = MutableLiveData<Any>()



  fun getLoaderLiveData() = loaderLiveData
    fun getDataLoadErrorLiveData() = dataLoadError

   fun getLogin(username:String, password:String){
    loaderLiveData.value = true
    dataLoadError.value = ""

    apiClient.getLogin(username,password)
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeOn(Schedulers.io())
      .subscribe ({result ->

        if(result.userdata?.loginuserID.equals("2") ){

          loginLiveData.value = result
          loaderLiveData.value = false

          Log.e("TAG", "login : "+ result.userdata )
        }else{
          dataLoadError.value = result.userdata?.username.toString()
          loaderLiveData.value = false
          Log.e("TAG", "login : "+ result.userdata )

          // Toast.makeText(MainApplication.applicationInstance.baseContext,result.message, Toast.LENGTH_LONG).show()
        }

      }, { error ->
        loaderLiveData.value = false
        //    Toast.makeText(MainApplication.applicationInstance.baseContext,error.message, Toast.LENGTH_LONG).show()
        Log.e("TAG23", "login : "+ error.message )
      })
  }


  fun getSurveyList(){
    loaderLiveData.value = true
    dataLoadError.value = ""

    apiClient.getSurveyList()
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeOn(Schedulers.io())
      .subscribe ({result ->

        if(result.status.equals("ok") ){

          surveyLiveData.value = result
        Log.e("TAG", "getSurvey: ${listOf(result)}", )
          loaderLiveData.value = false

          //Log.e("TAG", "survey : "+ result.name )
        }else{
         // dataLoadError.value = result.name.toString()
        //  surveyLiveData.value = ""
         // Log.e("TAG", "survey : "+ result.surveyId )

          // Toast.makeText(MainApplication.applicationInstance.baseContext,result.message, Toast.LENGTH_LONG).show()
        }

      }, { error ->
        loaderLiveData.value = false
        //    Toast.makeText(MainApplication.applicationInstance.baseContext,error.message, Toast.LENGTH_LONG).show()
        Log.e("TAG", "login : "+ error.message )
      })
  }

  fun getForgetPassword(username:String, password:String){
    loaderLiveData.value = true
    dataLoadError.value = ""

    apiClient.getForgetPassword(username,password)
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeOn(Schedulers.io())
      .subscribe ({result ->

        if(result.status.equals("ok")){

          forgetpasswordLiveData.value = result
          loaderLiveData.value = false

          Log.e("TAG", "passforgeted : "+ result.response )
        }else{
          //dataLoadError.value = result..toString()
          loaderLiveData.value = false
          Log.e("TAG", "password didn't forgeted : "+ result.response )

          // Toast.makeText(MainApplication.applicationInstance.baseContext,result.message, Toast.LENGTH_LONG).show()
        }

      }, { error ->
        loaderLiveData.value = false
        //    Toast.makeText(MainApplication.applicationInstance.baseContext,error.message, Toast.LENGTH_LONG).show()
        Log.e("TAG12", "login : "+ error.message )
      })
  }

  fun getQuestions(surveyId:String){
    loaderLiveData.value = true
    dataLoadError.value = ""

    apiClient.getQuestions(surveyId)
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeOn(Schedulers.io())
      .subscribe ({result ->

        if(result.status.equals("ok")){

          questionsLiveData.value = result
          loaderLiveData.value = false

          Log.e("TAG", "surveyQuestions : "+ result.response )
        }else{
          //dataLoadError.value = result..toString()
          loaderLiveData.value = false
          Log.e("TAG", "surveyQuestions didn't get : "+ result.response )

          // Toast.makeText(MainApplication.applicationInstance.baseContext,result.message, Toast.LENGTH_LONG).show()
        }

      }, { error ->
        loaderLiveData.value = false
        //    Toast.makeText(MainApplication.applicationInstance.baseContext,error.message, Toast.LENGTH_LONG).show()
        Log.e("TAG", "login : "+ error.message )
      })
  }

  fun getAnswerUploadPost(loginuserID: RequestBody, onlineExamID:RequestBody, map: Map<String,String>, full_name:RequestBody, gender:RequestBody, phone:RequestBody, age:RequestBody, address:RequestBody, files:MultipartBody.Part){
    loaderLiveData.value = true
    dataLoadError.value = ""

    apiClient.getAnswersUploadPost(loginuserID, onlineExamID, map, full_name, gender, phone, age, address,files)
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeOn(Schedulers.io())
      .subscribe ({result ->

        answerUploadLiveData.value = result.status!!
     //   Log.e("TAG", "ans : "+ result.options )

     /*   if(result.status == true){

          answerUploadLiveData.value = result
          loaderLiveData.value = false

          Log.e("TAG", "login : "+ result.message )
        }else{
          dataLoadError.value = result.message.toString()
          loaderLiveData.value = false
          Log.e("TAG", "login : "+ result.message )

          // Toast.makeText(MainApplication.applicationInstance.baseContext,result.message, Toast.LENGTH_LONG).show()
        }*/

      }, { error ->
        loaderLiveData.value = false
        //    Toast.makeText(MainApplication.applicationInstance.baseContext,error.message, Toast.LENGTH_LONG).show()
        Log.e("TAG32122", "ans : "+ error.message )
      })
  }

/*
  fun getAnswerUploadBody(request: UploadAnswerRequest,answer: HashMap<String,String>,){
    loaderLiveData.value = true
    dataLoadError.value = ""

    apiClient.getAnswersUploadBody(request,answer)
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeOn(Schedulers.io())
      .subscribe ({result ->

        answerUploadLiveData.value = result
        Log.e("TAG", "ans : "+ result.options )

      */
/*  if(result..equals("ok") ){

          answerUploadLiveData.value = result
          loaderLiveData.value = false

          Log.e("TAG", "login : "+ result.response )
        }else{
          dataLoadError.value = result.response.toString()
          loaderLiveData.value = false
          Log.e("TAG", "login : "+ result.response )

          // Toast.makeText(MainApplication.applicationInstance.baseContext,result.message, Toast.LENGTH_LONG).show()
        }
*//*

      }, { error ->
        loaderLiveData.value = false
        //    Toast.makeText(MainApplication.applicationInstance.baseContext,error.message, Toast.LENGTH_LONG).show()
        Log.e("TAG", "ans : "+ error.message )
      })
  }
*/

  fun getShow(loginuserID:String,onlineExamID:String){
    loaderLiveData.value = true
    dataLoadError.value = ""

    apiClient.getShow(loginuserID,onlineExamID)
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeOn(Schedulers.io())
      .subscribe ({result ->

        showLiveData.value = result
        Log.e("TAG", "showdata : "+ result )

     /*   if(result..equals("ok")){

          showLiveData.value = result
          loaderLiveData.value = false

          Log.e("TAG", "surveyQuestions : "+ result.response )
        }else{
          //dataLoadError.value = result..toString()
          loaderLiveData.value = false
          Log.e("TAG", "surveyQuestions didn't get : "+ result.response )

          // Toast.makeText(MainApplication.applicationInstance.baseContext,result.message, Toast.LENGTH_LONG).show()
        }*/

      }, { error ->
        loaderLiveData.value = false
        //    Toast.makeText(MainApplication.applicationInstance.baseContext,error.message, Toast.LENGTH_LONG).show()
        Log.e("TAG", "showdata : "+ error.message +error.printStackTrace() )
      })
  }

 fun getUploadedSurveyList(loginuserID:String){
    loaderLiveData.value = true
    dataLoadError.value = ""

    apiClient.getUploadedSurveyList(loginuserID)
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeOn(Schedulers.io())
      .subscribe ({result ->

        uploadedSurveyListLiveData.value = result
        Log.e("TAG", "showdata : "+ result )

     /*   if(result..equals("ok")){

          showLiveData.value = result
          loaderLiveData.value = false

          Log.e("TAG", "surveyQuestions : "+ result.response )
        }else{
          //dataLoadError.value = result..toString()
          loaderLiveData.value = false
          Log.e("TAG", "surveyQuestions didn't get : "+ result.response )

          // Toast.makeText(MainApplication.applicationInstance.baseContext,result.message, Toast.LENGTH_LONG).show()
        }*/

      }, { error ->
        loaderLiveData.value = false
        //    Toast.makeText(MainApplication.applicationInstance.baseContext,error.message, Toast.LENGTH_LONG).show()
        Log.e("TAG", "showdata : "+ error.message +error.printStackTrace() )
      })
  }

 fun getUploadedSurveyQuestion(surveyId:String){
    loaderLiveData.value = true
    dataLoadError.value = ""

    apiClient.getUploadedSurveyQuestion(surveyId)
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeOn(Schedulers.io())
      .subscribe ({result ->

        uploadedSurveyQuestionListLiveData.value = result
        Log.e("TAG", "showdata : "+ result )

     /*   if(result..equals("ok")){

          showLiveData.value = result
          loaderLiveData.value = false

          Log.e("TAG", "surveyQuestions : "+ result.response )
        }else{
          //dataLoadError.value = result..toString()
          loaderLiveData.value = false
          Log.e("TAG", "surveyQuestions didn't get : "+ result.response )

          // Toast.makeText(MainApplication.applicationInstance.baseContext,result.message, Toast.LENGTH_LONG).show()
        }*/

      }, { error ->
        loaderLiveData.value = false
        //    Toast.makeText(MainApplication.applicationInstance.baseContext,error.message, Toast.LENGTH_LONG).show()
        Log.e("TAG", "showdata : "+ error.message +error.printStackTrace() )
      })
  }

  fun getMukulAudio(audio: MultipartBody.Part){
    loaderLiveData.value = true
    dataLoadError.value = ""

    apiClient.getMukulAudio(audio)
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeOn(Schedulers.io())
      .subscribe ({result ->

        audioUploadLiveData.value = result
        Log.e("TAG", "showdata : "+ result )

     /*   if(result..equals("ok")){

          showLiveData.value = result
          loaderLiveData.value = false

          Log.e("TAG", "surveyQuestions : "+ result.response )
        }else{
          //dataLoadError.value = result..toString()
          loaderLiveData.value = false
          Log.e("TAG", "surveyQuestions didn't get : "+ result.response )

          // Toast.makeText(MainApplication.applicationInstance.baseContext,result.message, Toast.LENGTH_LONG).show()
        }*/

      }, { error ->
        loaderLiveData.value = false
        //    Toast.makeText(MainApplication.applicationInstance.baseContext,error.message, Toast.LENGTH_LONG).show()
        Log.e("TAG", "showdata : "+ error.message +error.printStackTrace() )
      })
  }


  fun getLoginLiveData() = loginLiveData
  fun getSurveyLiveData() = surveyLiveData
  fun getForgetPasswordLivedata() = forgetpasswordLiveData
  fun getQuestionsLiveData() = questionsLiveData
  fun getAnswerUploadLiveData() = answerUploadLiveData
  fun getShowLiveData() = showLiveData
  fun getUploadedSurveyListLiveData() = uploadedSurveyListLiveData
  fun getUploadedSurveyQuestionListLiveData() = uploadedSurveyQuestionListLiveData
  fun getAudioUploadLiveData() = audioUploadLiveData


}