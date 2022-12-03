package com.example.surveyapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.data.response.UploadedQAModel
import com.example.data.response.UploadedSurveyListResponseDataItem
import com.example.surveyapp.HomeViewModel
import com.example.surveyapp.R
import com.example.surveyapp.adapter.UploadedQuestionRecyclerAdapter
import com.example.surveyapp.databinding.UploadedQuestionActivityBinding

class UploadedQuestionActivity : AppCompatActivity() {

    private lateinit var viewModel: HomeViewModel
    lateinit var binding : UploadedQuestionActivityBinding
    var questionlist = arrayListOf<String>()
    var answerlist = arrayListOf<String>()
    val data = ArrayList<UploadedQAModel>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_uploaded_question)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        setObservers()

        val surveyId = intent.getStringExtra("id")
        Log.e("TAG897", "onCreate: ${surveyId.toString()}" )
        viewModel.getUploadedSurveyQuestion(surveyId.toString())
    }

    private fun setObservers(){
        observeSurvey()
    }

    private fun observeSurvey(){

        viewModel.getUploadedSurveyQuestionListLiveData().observe(this, Observer {

            if (it != null) {


                questionlist = it.response?.question as ArrayList<String>
                answerlist = it.response?.answer as ArrayList<String>


                questionlist.forEachIndexed { index, s ->

                    data.add(UploadedQAModel(questionlist.get(index),answerlist.get(index)))

                }

                data.forEach {
                    Log.e("TAG", "observeSurvey: ${it.question}  ans : ${it.answer}", )
                }
                //data.add(UploadedQAModel(questionlist.toString(),answerlist.toString()))



                // Setting the Adapter with the recyclerview
                try {
                    binding.uploadedQuestionRecyclerView.adapter = UploadedQuestionRecyclerAdapter(data,this)

                }catch (e : Exception){

                    Log.e("TAG9090", "observeSurvey: ${e.message}", )
                }
            }

        })
    }

}