package com.example.surveyapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.surveyapp.fragment.DownloadedSurvey
import com.example.surveyapp.R
import com.example.surveyapp.fragment.Profile
import com.example.surveyapp.fragment.SurveyListFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var bottomNavigationView : BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        val firstFragment= SurveyListFragment()
        val secondFragment= DownloadedSurvey()
        val thirdFragment= Profile()
        val fourth= Profile()
        val fifth= Profile()


        setCurrentFragment(firstFragment)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.profile ->setCurrentFragment(thirdFragment)
                R.id.sruvey_list ->setCurrentFragment(firstFragment)
                R.id.survey_history ->setCurrentFragment(secondFragment)
                R.id.manager_details ->setCurrentFragment(fourth)
                R.id.target ->setCurrentFragment(fifth)

            }
            true
        }

    }

    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }

}
