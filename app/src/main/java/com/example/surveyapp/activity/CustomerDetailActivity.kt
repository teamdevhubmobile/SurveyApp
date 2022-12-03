package com.example.surveyapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.surveyapp.R
import com.example.surveyapp.databinding.CustomerDetailActivityBinding

class CustomerDetailActivity : AppCompatActivity() {

    lateinit var binding : CustomerDetailActivityBinding
    var surveyName = ""
    var surveyId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_customer_detail)

        surveyName = intent.getStringExtra("surveyName").toString()
        surveyId = intent.getStringExtra("surveyId").toString()

        binding.submitBtn.setOnClickListener {

            intent = Intent(this,SurveyQuestionsActivity::class.java)
            intent.putExtra("name",binding.fullnameEdt.text.toString())
            intent.putExtra("age",binding.ageEdt.text.toString())
            intent.putExtra("gender",binding.genderEdt.text.toString())
            intent.putExtra("mobile",binding.mobileEdt.text.toString())
            intent.putExtra("address",binding.addressEdt.text.toString())
            intent.putExtra("category",binding.categoryEdt.text.toString())
            intent.putExtra("surveyName",surveyName)
            intent.putExtra("surveyId",surveyId)
            startActivity(intent)
            finish()

        }

    }
}