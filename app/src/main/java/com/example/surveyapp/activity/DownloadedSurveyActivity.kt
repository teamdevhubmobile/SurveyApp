package com.example.surveyapp.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.data.requests.UploadAnswerRequest
import com.example.surveyapp.utils.Dbhelper
import com.example.data.response.DownloadedSurveyListModel
import com.example.surveyapp.HomeViewModel
import com.example.surveyapp.adapter.DownloadedSurveyListRecyclerAdapter
import com.example.surveyapp.R
import com.example.surveyapp.ResponseItem
import com.example.surveyapp.adapter.ViewPagerAdapter
import com.example.surveyapp.databinding.SettingsActivityBinding
import kotlin.random.Random

class DownloadedSurveyActivity : AppCompatActivity() ,OnCLick{

    lateinit var binding : SettingsActivityBinding
    private lateinit var downloadedRecyclerAdapter: DownloadedSurveyListRecyclerAdapter
    private lateinit var viewModel: HomeViewModel


    var full_name = ""
    var gender = ""
    var age = ""
    var address = ""
    var phone = ""
    var surveId = ""
    var surveyName = ""
    lateinit var db : Dbhelper
    var sharedPreferences: SharedPreferences? = null
    var s1 = ""

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_downloaded_survey)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)


        s1 = sharedPreferences!!.getString("loginuserID","").toString()
        db = Dbhelper(this, null)

        val list = java.util.ArrayList<DownloadedSurveyListModel>()

        setObservers()

    downloadedRecyclerAdapter = DownloadedSurveyListRecyclerAdapter(list,this,this)

        try {


            val db = Dbhelper(this, null)
            val cursor = db.getName()

            cursor!!.moveToFirst()

            val Sno =  cursor.getString(cursor.getColumnIndex(Dbhelper.ID))
            val name =  cursor.getString(cursor.getColumnIndex(Dbhelper.SURVEYNAME))
            val id =  cursor.getString(cursor.getColumnIndex(Dbhelper.SURVEYID))
            val upload = cursor.getInt(cursor.getColumnIndex(Dbhelper.UPLOADED))
            val submit = cursor.getInt(cursor.getColumnIndex(Dbhelper.SUBMIT))

            var uploaded = false
            if (upload == 1){

                uploaded = true

            }
            var submited = false
            if (submit == 1){

                submited = true

            }

//        val name =  cursor.getString(cursor.getColumnIndex(Dbhelper.SURVEYNAME))
            var mylist = DownloadedSurveyListModel(Sno, name,id,uploaded,submited)
            list.add(mylist)



            while(cursor.moveToNext()) {
                val Sno =  cursor.getString(cursor.getColumnIndex(Dbhelper.ID))
                val name =  cursor.getString(cursor.getColumnIndex(Dbhelper.SURVEYNAME))
                val id =  cursor.getString(cursor.getColumnIndex(Dbhelper.SURVEYID))
                val upload = cursor.getInt(cursor.getColumnIndex(Dbhelper.UPLOADED))
                val submit = cursor.getInt(cursor.getColumnIndex(Dbhelper.SUBMIT))

                var uploaded = false
                if (upload == 1){

                    uploaded = true

                }
                var submited = false
                if (submit == 1){

                    submited = true

                }

//        val name =  cursor.getString(cursor.getColumnIndex(Dbhelper.SURVEYNAME))
                var mylist = DownloadedSurveyListModel(Sno, name,id,uploaded,submited)
                list.add(mylist)
            }
            cursor.close()

            binding.downloadedRecycler.adapter = downloadedRecyclerAdapter

        }catch (e:Exception){
            Log.e("EOORO", "onCreate: $e", )

}

    }


    var spId =0
    var sid =0
    override fun onClick(sid: Int,spId: Int) {

        this.spId =spId
        this.sid =sid
        Log.e("TAG90", "onClick: $sid", )

        viewModel.getShow(s1,"1")


       // db.uploadSurvey(spId.toInt(),1)
         // viewModel.getAnswerUploadPost( s1,"1",answermap,full_name,gender,phone,age,address)
    }

    @SuppressLint("Range")
    fun uploadSurvay(spId: Int,sid: Int){
        val cursor = db.getQuestion(spId)

        cursor?.moveToFirst()

        val qid =  cursor?.getString(cursor.getColumnIndex(Dbhelper.QUESTIONID))
        val position =  cursor?.getString(cursor.getColumnIndex(Dbhelper.OPPOSITION))
        val opId =  cursor?.getString(cursor.getColumnIndex(Dbhelper.OPID))

        val answermap = hashMapOf<String, String>()

        if (position.isNullOrEmpty() || opId.isNullOrEmpty()){

            if (position.isNullOrEmpty()){
                val data = intArrayOf(sid.toInt()).contentToString()+intArrayOf(qid!!.toInt()).contentToString()+intArrayOf(0).contentToString()
                val answerData="answer$data"
                answermap.put(answerData, opId.toString())
            }else{
                val data = intArrayOf(sid.toInt()).contentToString()+intArrayOf(qid!!.toInt()).contentToString()+intArrayOf(position!!.toInt()).contentToString()
                val answerData="answer$data"
                answermap.put(answerData, "0")
            }

        }else{
            val data = intArrayOf(sid.toInt()).contentToString()+intArrayOf(qid!!.toInt()).contentToString()+intArrayOf(position!!.toInt()).contentToString()
            val answerData="answer$data"
            answermap.put(answerData, opId.toString())
        }

Log.e("TAGHAT", "onCreate: ${qid +  position + opId}", )
        while(cursor!!.moveToNext()) {

            val qid =  cursor?.getString(cursor.getColumnIndex(Dbhelper.QUESTIONID))
            val position =  cursor?.getString(cursor.getColumnIndex(Dbhelper.OPPOSITION))
            val opId =  cursor?.getString(cursor.getColumnIndex(Dbhelper.OPID))


            if (position.isNullOrEmpty() || opId.isNullOrEmpty()){

                if (position.isNullOrEmpty()){
                    val data = intArrayOf(sid.toInt()).contentToString()+intArrayOf(qid!!.toInt()).contentToString()+intArrayOf(0).contentToString()
                    val answerData="answer$data"
                    answermap.put(answerData, opId.toString())
                }else if (opId.isNullOrEmpty()){
                    val data = intArrayOf(sid.toInt()).contentToString()+intArrayOf(qid!!.toInt()).contentToString()+intArrayOf(position!!.toInt()).contentToString()
                    val answerData="answer$data"
                    answermap.put(answerData, "0")
                }
                else{
                    val data = intArrayOf(sid.toInt()).contentToString()+intArrayOf(qid!!.toInt()).contentToString()+intArrayOf(0).contentToString()
                    val answerData="answer$data"
                    answermap.put(answerData, "0")
                }

            }else{
                val data = intArrayOf(sid.toInt()).contentToString()+intArrayOf(qid!!.toInt()).contentToString()+intArrayOf(position!!.toInt()).contentToString()
                val answerData="answer$data"
                answermap.put(answerData, opId.toString())
            }

           }

        viewModel.getAnswerUploadPost( s1,"1",answermap,full_name,gender,phone,age,address)

    }

    override fun onListClick(sId: Int, spId: Int) {

    }

    private fun setObservers() {
        observerShow()
        observeUpdateQandA()
    }

    private fun observeUpdateQandA() {

        viewModel.getAnswerUploadLiveData()
            .observe(this, Observer {


                if (it != null) {


                    Log.e("TAG", "this is iyt : $it")

                    db.uploadSurvey(spId.toInt(),1)
                }

            })
    }
    private fun observerShow() {

        viewModel.getShowLiveData()
            .observe(this, Observer {

                //showToast("succes")

                if (it != null) {

                    full_name =   it.userExamCheck?.fullName.toString()
                    gender =  it.userExamCheck?.gender.toString()
                    age =  it.userExamCheck?.age.toString()
                    phone =  it.userExamCheck?.phone.toString()
                    address = it.userExamCheck?.address.toString()

                    Log.e("TAG", "this is iit : $it")

                    uploadSurvay(spId, sid)

                }

            })
    }
}