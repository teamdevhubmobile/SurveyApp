package com.example.surveyapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.databinding.DataBindingUtil
import com.example.surveyapp.R
import com.example.surveyapp.databinding.CustomerDetailOnlineActivityBinding

class CustomerDetailOnlineActivity : AppCompatActivity() {

    lateinit var binding: CustomerDetailOnlineActivityBinding
    var gender = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = DataBindingUtil.setContentView(this,R.layout.activity_customer_detail_online)

        var surveId = intent.getStringExtra("surveyId").toString()
        var surveyName = intent.getStringExtra("surveyName").toString()

        //radioGroup
        binding.radioGroup.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
            val intSelectButton: Int = binding.radioGroup!!.checkedRadioButtonId
            val btn = findViewById<RadioButton>(intSelectButton)
            val currentcigretSmoked = btn.text.toString()

            gender = currentcigretSmoked

        })


        binding.submitBtn.setOnClickListener {

            intent = Intent(this,SurveyQuestionsActivity::class.java)
            intent.putExtra("name",binding.fullnameEdt.text.toString())
            intent.putExtra("age",binding.ageEdt.text.toString())
            intent.putExtra("gender",gender)
            intent.putExtra("mobile",binding.mobileEdt.text.toString())
            intent.putExtra("address",binding.addressEdt.text.toString())
            intent.putExtra("surveyId",surveId)
            intent.putExtra("surveyName",surveyName)
            intent.putExtra("forcheckstate","true")
            startActivity(intent)
            finish()

        }
    }
}