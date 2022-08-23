package com.example.surveyapp.activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.surveyapp.R

class SplashActivity : AppCompatActivity(){

var handler: Handler? = null
var sharedPreferences: SharedPreferences? = null

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_splash)


    handler = Handler()
    handler!!.postDelayed({
        sharedPreferences = getSharedPreferences("MyPrefs", AppCompatActivity.MODE_PRIVATE)

        if (sharedPreferences!!.contains("NameKey")) {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }, 1000)
}
}