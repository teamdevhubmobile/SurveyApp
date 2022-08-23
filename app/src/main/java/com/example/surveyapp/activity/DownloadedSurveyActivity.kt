package com.example.surveyapp.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.surveyapp.utils.Dbhelper
import com.example.surveyapp.response.DownloadedSurveyListModel
import com.example.surveyapp.adapter.DownloadedSurveyListRecyclerAdapter
import com.example.surveyapp.R
import com.example.surveyapp.databinding.SettingsActivityBinding

class DownloadedSurveyActivity : AppCompatActivity() {

    lateinit var binding : SettingsActivityBinding
    private lateinit var downloadedRecyclerAdapter: DownloadedSurveyListRecyclerAdapter

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_downloaded_survey)


        val list = java.util.ArrayList<DownloadedSurveyListModel>()


        downloadedRecyclerAdapter = DownloadedSurveyListRecyclerAdapter(list,this)

        val db = Dbhelper(this, null)
        val cursor = db.getName()

        cursor!!.moveToFirst()


        while(cursor.moveToNext()) {
            val Sno =  cursor.getString(cursor.getColumnIndex(Dbhelper.ID))
            val name =  cursor.getString(cursor.getColumnIndex(Dbhelper.SURVEYNAME))
            val id =  cursor.getString(cursor.getColumnIndex(Dbhelper.SURVEYID))

//        val name =  cursor.getString(cursor.getColumnIndex(Dbhelper.SURVEYNAME))
            var mylist = DownloadedSurveyListModel(Sno, name,id)
            list.add(mylist)
        }
        cursor.close()


        binding.downloadedRecycler.adapter = downloadedRecyclerAdapter



    }
}