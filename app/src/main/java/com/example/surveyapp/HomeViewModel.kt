package com.example.surveyapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.requests.UploadAnswerRequest
import com.example.survey.RegisterResponse
import com.example.surveyapp.apiservice.ApiInterface
import com.example.data.response.ForgetPasswordResponse
import com.example.data.response.ShowResponse
import com.example.data.response.UpdateQuestionAnswerResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

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
  private var answerUploadLiveData = MutableLiveData<UpdateQuestionAnswerResponse>()
  private var showLiveData = MutableLiveData<ShowResponse>()



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
        Log.e("TAG", "login : "+ error.message )
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
        Log.e("TAG", "login : "+ error.message )
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

  fun getAnswerUploadPost(loginuserID:String, onlineExamID:String,  map: Map<String,String>, full_name:String, gender:String, phone:String, age:String, address:String){
    loaderLiveData.value = true
    dataLoadError.value = ""

    apiClient.getAnswersUploadPost(loginuserID, onlineExamID, map, full_name, gender, phone, age, address)
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeOn(Schedulers.io())
      .subscribe ({result ->

        answerUploadLiveData.value = result
        Log.e("TAG", "ans : "+ result.options )

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
*/
      }, { error ->
        loaderLiveData.value = false
        //    Toast.makeText(MainApplication.applicationInstance.baseContext,error.message, Toast.LENGTH_LONG).show()
        Log.e("TAG", "ans : "+ error.message )
      })
  }

  fun getAnswerUploadBody(request: UploadAnswerRequest,answer: HashMap<String,String>,){
    loaderLiveData.value = true
    dataLoadError.value = ""

    apiClient.getAnswersUploadBody(request,answer)
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeOn(Schedulers.io())
      .subscribe ({result ->

        answerUploadLiveData.value = result
        Log.e("TAG", "ans : "+ result.options )

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
*/
      }, { error ->
        loaderLiveData.value = false
        //    Toast.makeText(MainApplication.applicationInstance.baseContext,error.message, Toast.LENGTH_LONG).show()
        Log.e("TAG", "ans : "+ error.message )
      })
  }

  fun getShow(loginuserID:String,onlineExamID:String){
    loaderLiveData.value = true
    dataLoadError.value = ""

    apiClient.getShow(loginuserID,onlineExamID)
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeOn(Schedulers.io())
      .subscribe ({result ->

        showLiveData.value = result
        Log.e("TAG", "showdata : "+ result.onlineExam?.name.toString() )

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
        Log.e("TAG", "showdata : "+ error.message )
      })
  }


  fun getLoginLiveData() = loginLiveData
  fun getSurveyLiveData() = surveyLiveData
  fun getForgetPasswordLivedata() = forgetpasswordLiveData
  fun getQuestionsLiveData() = questionsLiveData
  fun getAnswerUploadLiveData() = questionsLiveData
  fun getShowLiveData() = showLiveData


}