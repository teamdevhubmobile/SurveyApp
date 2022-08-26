package com.example.surveyapp.activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.surveyapp.HomeViewModel
import com.example.surveyapp.R
import com.example.surveyapp.databinding.LoginActivityBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: HomeViewModel
    lateinit var binding: LoginActivityBinding
    var myPreference = "MyPrefs"
    var name = "NameKey"
    var pass = "PassKey"
    var loginuserID = ""
    var sharedPreferences: SharedPreferences? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)


        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        sharedPreferences = getSharedPreferences(myPreference, MODE_PRIVATE)

        setObservers()

        binding.loginButton.setOnClickListener {


            viewModel.getLogin(binding.editTextTextPersonName.text.toString(), binding.editTextTextPersonName2.text.toString())




            /*  val intent = Intent(this, MainActivity::class.java)
              startActivity(intent)
              finish()*/


        }

        binding.forgetPassword.setOnClickListener {

            val  intent = Intent(this, ForgetPasswordActivity::class.java)
            startActivity(intent)
            finish()

        }
    }

    fun isValid():Boolean {

        if (binding.editTextTextPersonName.text.toString().length <10){
            binding.editTextTextPersonName.error ="please enter correct mobile number"
            binding.editTextTextPersonName.requestFocus()
            return false
        }
        if (binding.editTextTextPersonName2.text.isNullOrEmpty()) {
            binding.editTextTextPersonName2.error = "please enter your password"
            binding.editTextTextPersonName2.requestFocus()
            return false
        }

        return true
    }

    private fun setObservers(){
        observeLogin()
    }

    private fun observeLogin(){

        viewModel.getLoginLiveData().observe(this, Observer {

            //showToast("succes")

            if (it != null) {
                loginuserID = it.userdata?.loginuserID.toString()
                val editor = sharedPreferences!!.edit()

                editor.putString(name, binding.editTextTextPersonName.text.toString())
                editor.putString(pass,  binding.editTextTextPersonName2.text.toString())
                editor.putString("loginuserID",loginuserID)
                editor.commit()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
                //                binding.recyclerBrows.adapter = SeriesAdapter(it as ArrayList<SeriesListResponseItem>,activity,this)
            }

        })
    }
}