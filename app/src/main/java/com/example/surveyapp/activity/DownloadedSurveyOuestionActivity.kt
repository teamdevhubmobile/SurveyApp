package com.example.surveyapp.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.surveyapp.*
import com.example.surveyapp.databinding.DownloadedSurveyQuestionActivityBinding
import com.example.surveyapp.interfaces.OptionsListenerInterface
import com.example.surveyapp.utils.Dbhelper
import com.example.surveyapp.adapter.ViewPagerAdapter
import com.example.data.response.SubmitAnswersModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import java.lang.reflect.Type


class DownloadedSurveyOuestionActivity : AppCompatActivity(),OptionsListenerInterface {

    lateinit var binding: DownloadedSurveyQuestionActivityBinding
   // private lateinit var viewPager: ViewPager
    lateinit var mViewPagerAdapter: ViewPagerAdapter
    var mList = arrayListOf<ResponseItem>()
    var qId = ""
    var answer = ""
    var submitList = arrayListOf<SubmitAnswersModel>()
   // val dbhelper  = Dbhelper(this,null)
   var id = ""
    var question = ""
    var OId = ""
    private lateinit var viewModel: HomeViewModel
    var sharedPreferences: SharedPreferences? = null
    var s1 = ""
    var full_name = ""
    var gender = ""
    var age = ""
    var address = ""
    var phone = ""
    var surveId = ""
    var surveyName = ""




    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_downloaded_survey_ouestion)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        val db = Dbhelper(this, null)

        val sid = intent.getStringExtra("sid")
        val spid = intent.getStringExtra("spid")

        val cursor = db.getQuestion(spid!!.toInt())

        binding.submitBtn.setOnClickListener {

            var newSList = mViewPagerAdapter.getList()

            if (!newSList.isNullOrEmpty()){

                for (item in newSList){

                    var optionList = item.option

                    var answer =""
                    var opId =""
                    var opPosition =""

                    Log.e("TAGop", "onCreate: "+ item)

                    for (op in optionList!!){
                        Log.e("TAGop1", "onCreate: "+ op)
                        if (!op?.answers.isNullOrEmpty()){
                           answer = op?.answers.toString()
                            opId = op?.optionID.toString()
                            opPosition = op?.position.toString()
                        }

                    }
                    db.answerSubmit(item.questionBankID.toString(), answer,spid.toInt(),opId,opPosition)

                }

                db.submitSurvey(spid.toInt(),1)

                val intent = Intent(this,DownloadedSurveyActivity::class.java)
                startActivity(intent)
                finish()

            }else{
                Toast.makeText(this, "Select Answer", Toast.LENGTH_SHORT).show()
            }

            /*if (!submitList.isNullOrEmpty()) {

                for (item in submitList) {

                    Log.e("TAG97", "onCreate: ${item.questionId+ item.item.name.toString()}",)



                   // db.submitSurvey("1".toInt(),1)

                }
            }else{

                Toast.makeText(this, "Select Answer", Toast.LENGTH_SHORT).show()

            }*/
        }


        val gson = Gson()


        cursor!!.moveToFirst()

        id =  cursor.getString(cursor.getColumnIndex(Dbhelper.QUESTIONID))
        question =  cursor.getString(cursor.getColumnIndex(Dbhelper.QUESTION))
        OId =  cursor.getString(cursor.getColumnIndex(Dbhelper.OPTION))
        val answers =  cursor.getString(cursor.getColumnIndex(Dbhelper.ANSWER))

        Log.e("TAG 122112", "onCreate: ${answers.toString()}", )
//            val OptionId =  cursor.getString(cursor.getColumnIndex(Dbhelper.OPTIONID))
//            val OptionName =  cursor.getString(cursor.getColumnIndex(Dbhelper.QUESTIONID))

        //  Toast.makeText(this, ""+id.toString(), Toast.LENGTH_SHORT).show()
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

       // Log.e("TAG 122112", "onCreate: ${answers.toString()}", )

        while(cursor.moveToNext()) {

             id =  cursor.getString(cursor.getColumnIndex(Dbhelper.QUESTIONID))
            question =  cursor.getString(cursor.getColumnIndex(Dbhelper.QUESTION))
             OId =  cursor.getString(cursor.getColumnIndex(Dbhelper.OPTION))
            val answers =  cursor.getString(cursor.getColumnIndex(Dbhelper.ANSWER))

            Log.e("TAG 122112", "onCreate: ${answers.toString()}", )
//            val OptionId =  cursor.getString(cursor.getColumnIndex(Dbhelper.OPTIONID))
//            val OptionName =  cursor.getString(cursor.getColumnIndex(Dbhelper.QUESTIONID))

          //  Toast.makeText(this, ""+id.toString(), Toast.LENGTH_SHORT).show()
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

        var model = SubmitAnswersModel(item,qId)

        try {


            if (!submitList.isNullOrEmpty()) {

                for (i in 0 until submitList.size) {

                    if (submitList.get(i).questionId.equals(qId)) {

                        Log.e("TAG7", "onOptionClick: " + submitList.get(i).questionId.equals(qId))

                        submitList.removeAt(i)
                        submitList.add(model)
                        return
                    }

                }


                    submitList.add(model)


            } else {

                answer = item.name.toString()
                this.qId = qId.toString()
                submitList.add(SubmitAnswersModel(item, qId))

            }
        }catch (e:Exception){

            Log.e("TAG44", "onOptionClick: $e", )

        }

    }

    override fun onSurveyId(surveyId: String) {

    }

    private fun setObservers() {
        observerShow()
        observeUpdateQandA()
    }


    private fun observeUpdateQandA() {

        viewModel.getAnswerUploadLiveData()
            .observe(this@DownloadedSurveyOuestionActivity, Observer {

                //showToast("succes")

                if (it != null) {

                    Log.e("TAG", "this is iyt : $it")

                }

            })
    }
    private fun observerShow() {

        viewModel.getShowLiveData()
            .observe(this@DownloadedSurveyOuestionActivity, Observer {

                //showToast("succes")

                if (it != null) {

                    full_name =   it.userExamCheck?.fullName.toString()
                    gender =  it.userExamCheck?.gender.toString()
                    age =  it.userExamCheck?.age.toString()
                    phone =  it.userExamCheck?.phone.toString()
                    address = it.userExamCheck?.address.toString()

                /*    Log.e("TAG", "observerShow55: ${it.userExamCheck?.fullName.toString()}")
                    Log.e("TAG", "observerShow55: ${it.userExamCheck?.gender.toString()}")
                    Log.e("TAG", "observerShow55: ${it.userExamCheck?.address.toString()}")
                    Log.e("TAG", "observerShow55: ${it.userExamCheck?.phone.toString()}")
                    Log.e("TAG", "observerShow55: ${it.userExamCheck?.age.toString()}")
*/
                    Log.e("TAG", "this is iit : $it")

                }

            })
    }


}