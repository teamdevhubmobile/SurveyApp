package com.example.surveyapp.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.example.surveyapp.*
import com.example.surveyapp.databinding.DownloadedSurveyQuestionActivityBinding
import com.example.surveyapp.interfaces.OptionsListenerInterface
import com.example.surveyapp.utils.Dbhelper
import com.example.surveyapp.adapter.ViewPagerAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import java.lang.reflect.Type


class DownloadedSurveyOuestionActivity : AppCompatActivity(),OptionsListenerInterface {

    lateinit var binding: DownloadedSurveyQuestionActivityBinding
   // private lateinit var viewPager: ViewPager
    lateinit var mViewPagerAdapter: ViewPagerAdapter
    var mList = arrayListOf<ResponseItem>()

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_downloaded_survey_ouestion)

        val db = Dbhelper(this, null)
        val cursor = db.getQuestion()

        var textList = ArrayList<String>()
        textList.add("Slide 1")
        textList.add("Slide 2")
        textList.add("Slide 3")
        textList.add("Slide 4")
        textList.add("dff")



        val gson = Gson()


        cursor!!.moveToFirst()

        while(cursor.moveToNext()) {

            val id =  cursor.getString(cursor.getColumnIndex(Dbhelper.QUESTIONID))
            val question =  cursor.getString(cursor.getColumnIndex(Dbhelper.QUESTION))
            val OId =  cursor.getString(cursor.getColumnIndex(Dbhelper.OPTION))
//            val OptionId =  cursor.getString(cursor.getColumnIndex(Dbhelper.OPTIONID))
//            val OptionName =  cursor.getString(cursor.getColumnIndex(Dbhelper.QUESTIONID))

            Toast.makeText(this, ""+id.toString(), Toast.LENGTH_SHORT).show()
            //binding.dqTxt.setText(id.toString()+" "+OptionName.toString()+" "+OptionId.toString())

            val type: Type = object : TypeToken<ArrayList<OptionItem?>?>() {}.type



            val finalOutputString: ArrayList<OptionItem> = gson.fromJson(OId, type)

            var array12 = JSONArray(OId)


            Log.e("TAG12", "onCreate: "+array12 )

            var optionList = arrayListOf<OptionItem>()

            for (i in 0 until array12.length()){

                var optionItem  = array12.getJSONObject(i)

                //val objec: OptionItem = gson.fromJson(optionItem, OptionItem::class.java)
                val objecj = gson.fromJson(optionItem.toString(),OptionItem::class.java)

                optionList.add(objecj)
               // Log.e("TAG77", "onCreate: "+objecj.name )

            }

            mList.add(ResponseItem(id,question,optionList))
            Log.e("TAG111", "DataBase: QuestionId = "+id.toString()+"   Question = "+question.toString()+"   Option = "+OId )
            Log.e("TAG611", "DataBase: list = "+mList )

//            var mylist = DownloadedQuestionOptionModel(id, name,qId,)
//
//            list.add(mylist)
//            //     list2.add(mylist2)
        }
        cursor.close()

        viewPager()


    }

    var viewPagerPageChangeListener: ViewPager.OnPageChangeListener =
        object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
                // your logic here
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                // your logic here
            }

            override fun onPageSelected(position: Int) {
                // your logic here
            }
        }

    private fun viewPager() {

        try {


            mViewPagerAdapter = ViewPagerAdapter(this@DownloadedSurveyOuestionActivity, mList,this)
            binding.viewPager.pageMargin = 15
            binding.viewPager.setPadding(50, 0, 50, 0);
            binding.viewPager.setClipToPadding(false)
            binding.viewPager.setPageMargin(25)
            binding.viewPager.adapter = mViewPagerAdapter
            binding.viewPager.addOnPageChangeListener(viewPagerPageChangeListener)

        }catch (e:Exception){

            Log.e("TAG555", "viewPager: $e", )
        }
    }

    override fun onOptionClick(item: OptionItem,qId : String) {
        TODO("Not yet implemented")
    }
}