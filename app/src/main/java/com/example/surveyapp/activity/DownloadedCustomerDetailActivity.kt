package com.example.surveyapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.surveyapp.R
import com.example.surveyapp.databinding.DownloadedCustomerDetailActivityBinding

class DownloadedCustomerDetailActivity : AppCompatActivity() {

    lateinit var binding : DownloadedCustomerDetailActivityBinding
    var spid = ""
    var sid = ""
    var surveyName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_downloaded_customer_detail)

         sid = intent.getStringExtra("sid").toString()
         spid = intent.getStringExtra("spid").toString()
        surveyName = intent.getStringExtra("surveyName").toString()

        binding.submitBtn.setOnClickListener {

            intent = Intent(this,DownloadedSurveyOuestionActivity::class.java)
            intent.putExtra("name",binding.fullnameEdt.text.toString())
            intent.putExtra("age",binding.ageEdt.text.toString())
            intent.putExtra("gender",binding.genderEdt.text.toString())
            intent.putExtra("mobile",binding.mobileEdt.text.toString())
            intent.putExtra("address",binding.addressEdt.text.toString())
            intent.putExtra("category",binding.categoryEdt.text.toString())
            intent.putExtra("spid",spid)
            intent.putExtra("sid",sid)
            intent.putExtra("surveyName",surveyName)
            startActivity(intent)
            finish()

        }
    }
}