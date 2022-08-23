package com.example.surveyapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.surveyapp.HomeViewModel
import com.example.surveyapp.R
import com.example.surveyapp.databinding.ForgetPasswordActivityBinding

class ForgetPasswordActivity : AppCompatActivity() {
    lateinit var binding : ForgetPasswordActivityBinding
    private lateinit var viewModel: HomeViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forget_password)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        binding.confirmButton.setOnClickListener {

            viewModel.getForgetPassword(binding.newPassword.text.toString(), binding.confirmPassword.text.toString())

            setObservers()
        }

    }

    fun isValid():Boolean{

        if (binding.newPassword.text.isNullOrEmpty()){
            binding.newPassword.error ="please enter your name"
            binding.newPassword.requestFocus()
            return false
        }
        if (binding.confirmPassword.text.isNullOrEmpty()){
            binding.confirmPassword.error ="please enter your email"
            binding.confirmPassword.requestFocus()
            return false
        }


        return true
    }

    private fun setObservers(){
        observeForgetPassword()
    }

    private fun observeForgetPassword(){

        viewModel.getForgetPasswordLivedata().observe(this, Observer {

            if (it != null) {

                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)

            }

        })
    }
}