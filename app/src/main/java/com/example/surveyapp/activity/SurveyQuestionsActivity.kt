package com.example.surveyapp.activity

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.data.requests.UploadAnswerRequest
import com.example.surveyapp.HomeViewModel
import com.example.surveyapp.OptionItem
import com.example.surveyapp.R
import com.example.surveyapp.ResponseItem
import com.example.surveyapp.adapter.ViewPagerAdapter
import com.example.surveyapp.databinding.QuestionsActivityBinding
import com.example.surveyapp.interfaces.OptionsListenerInterface
import com.example.data.response.SubmitAnswersModel
import com.example.data.response.SurvIdModel
import com.example.surveyapp.utils.Dbhelper
import com.google.gson.Gson
import kotlin.random.Random

class SurveyQuestionsActivity : AppCompatActivity(),OptionsListenerInterface {

    lateinit var binding: QuestionsActivityBinding
    private lateinit var viewModel: HomeViewModel
    var id = ""
    var nid = ""
    private lateinit var viewPager: ViewPager
     lateinit var mViewPagerAdapter: ViewPagerAdapter
    var textListt = ArrayList<OptionItem>()
    var survId = ""
    var qId = ""
    var answer = ""
    val dbhelper  = Dbhelper(this,null)
    var submitList = arrayListOf<SubmitAnswersModel>()
    var survIdList = arrayListOf<SurvIdModel>()
    var responseList = arrayListOf<ResponseItem>()
    var sharedPreferences: SharedPreferences? = null
    var s1 = ""
    var full_name = ""
    var gender = ""
    var age = ""
    var address = ""
    var phone = ""
    var surveId = ""





    val answr = arrayListOf<List<List<String>>>()

   // val testing = Gson().toJson(data)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_survey_ouestions)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        sharedPreferences = getSharedPreferences("MyPrefs", AppCompatActivity.MODE_PRIVATE)

        surveId = intent.getStringExtra("surveyId").toString()

         s1 = sharedPreferences!!.getString("loginuserID","").toString()

      //  Toast.makeText(this, "$s1", Toast.LENGTH_SHORT).show()

        binding.fabBtn.setOnClickListener {
          /*
            val answerList= arrayListOf<HashMap<String,String>>()

            val answermap = hashMapOf<String, String>()

            for (item in responseList) {

                val rnd1= Random.nextInt(50,80)

                for (it in submitList){
                    if (it.questionId.equals(item.questionBankID)){
                        val data = intArrayOf(surveId.toInt()).contentToString()+intArrayOf(it.questionId.toInt()).contentToString()+intArrayOf(it.item.optionID!!.toInt()).contentToString()

                        val answerData="answer$data"
                        Log.e("TAG2002", "Gson test: ${answerData}")

                        answermap.put(answerData, Random.nextInt(50,60).toString())
                    }
                }

            }


            val request= UploadAnswerRequest(
                s1,"1",full_name,gender,phone,age,address
            )

            viewModel.getAnswerUploadPost( s1,"1",answermap,full_name,gender,phone,age,address)

            if (!responseList.isNullOrEmpty()) {

                for (i in 0 until responseList.size){

                    dbhelper.addQuestion(

                        responseList.get(i).questionBankID.toString(),
                        responseList.get(i).question.toString(),
                        responseList.get(i).option as ArrayList<OptionItem>

                    )
                }

            }else {

                Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
            }*/
            }


        binding.submitBtn.setOnClickListener {

            val answerList= arrayListOf<HashMap<String,String>>()

            val answermap = hashMapOf<String, String>()

            for (item in responseList) {

                val rnd1= Random.nextInt(50,80)

                for (it in submitList){
                    if (it.questionId.equals(item.questionBankID)){
                        val data = intArrayOf(surveId.toInt()).contentToString()+intArrayOf(it.questionId.toInt()).contentToString()+intArrayOf(it.item.optionID!!.toInt()).contentToString()

                        val answerData="answer$data"
                        Log.e("TAG2002", "Gson test: ${answerData}")

                        answermap.put(answerData, Random.nextInt(50,60).toString())
                    }
                }

            }


            val request= UploadAnswerRequest(
                s1,"1",full_name,gender,phone,age,address
            )

            viewModel.getAnswerUploadPost( s1,"1",answermap,full_name,gender,phone,age,address)

            if (!responseList.isNullOrEmpty()) {

                for (i in 0 until responseList.size){

                    dbhelper.addQuestion(

                        responseList.get(i).questionBankID.toString(),
                        responseList.get(i).question.toString(),
                        responseList.get(i).option as ArrayList<OptionItem>

                    )
                }

            }else {

                Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
            }

            var myList = mViewPagerAdapter.getList()

            for (item in myList){

                Toast.makeText(this, "$survId", Toast.LENGTH_SHORT).show()
                Log.e("TAG987", "onCreate: $item")
            }
/*
            if (!submitList.isNullOrEmpty()) {


                for (item in submitList) {
                        answerlist.add(""+item.item.optionID!!)
                    Log.e("TAG97", "onCreate: ${item.questionId + item.item.name.toString()}")
                  //  dbhelper.answerSubmit(item.questionId, item.item.name.toString())
                    ans_name = item.item.name.toString()
                }

                Log.e("TAG97", "answerlist: ${ answerlist.toString()}")

            }else{

                Toast.makeText(this, "Select Answer", Toast.LENGTH_SHORT).show()

            }

            viewModel.getAnswerUpload(s1,"1",
        "shai",full_name,gender,phone,age,address)
*/
        }

          /*  textList.add("Slide 1")
            textList.add("Slide 2")
            textList.add("Slide 3")
            textList.add("Slide 4")
            textList.add("dff")*/

        viewPager = findViewById(R.id.viewPager)


        setObservers()
        viewModel.getShow(s1,"1")
        viewModel.getQuestions(intent.getStringExtra("surveyId").toString())


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
            private fun setObservers() {
                observeQuestion()
                observerShow()
                observeUpdateQandA()
            }

            private fun observeQuestion() {

                viewModel.getQuestionsLiveData()
                    .observe(this@SurveyQuestionsActivity, Observer {

                        //showToast("succes")

                        if (it != null) {

                            Log.e("TAG", "this is it : $it")

                            responseList = it.response as ArrayList<ResponseItem>


                            // textListt =  it.response?.get() as ArrayList<OptionItem>

                            mViewPagerAdapter = ViewPagerAdapter(this@SurveyQuestionsActivity,   it.response as ArrayList<ResponseItem>,this )
                            viewPager.pageMargin = 15
                            viewPager.setPadding(50, 0, 50, 0);
                            viewPager.setClipToPadding(false)
                            viewPager.setPageMargin(25)
                            viewPager.adapter = mViewPagerAdapter
                            viewPager.addOnPageChangeListener(viewPagerPageChangeListener)

                            //textList =

/*


                            binding.downloadQuestionfab.setOnClickListener {

                            }
*/

                            // binding.questionsRecyclerview.adapter = RecyclerAdapterSurveyQuestions(it.response as ArrayList<ResponseItem>,this)
                        }

                    })
            }
    private fun observeUpdateQandA() {

        viewModel.getAnswerUploadLiveData()
            .observe(this@SurveyQuestionsActivity, Observer {

                //showToast("succes")

                if (it != null) {

                    Log.e("TAG", "this is iyt : $it")

                   /* responseList = it.response as ArrayList<ResponseItem>


                    // textListt =  it.response?.get() as ArrayList<OptionItem>

                    mViewPagerAdapter = ViewPagerAdapter(this@SurveyQuestionsActivity,   it.response as ArrayList<ResponseItem>,this )
                    viewPager.pageMargin = 15
                    viewPager.setPadding(50, 0, 50, 0);
                    viewPager.setClipToPadding(false)
                    viewPager.setPageMargin(25)
                    viewPager.adapter = mViewPagerAdapter
                    viewPager.addOnPageChangeListener(viewPagerPageChangeListener)

                    //textList =

*/
                /*


                            binding.downloadQuestionfab.setOnClickListener {

                            }
*/

                    // binding.questionsRecyclerview.adapter = RecyclerAdapterSurveyQuestions(it.response as ArrayList<ResponseItem>,this)
                }

            })
    }
    private fun observerShow() {

        viewModel.getShowLiveData()
            .observe(this@SurveyQuestionsActivity, Observer {

                //showToast("succes")

                if (it != null) {

                  full_name =   it.userExamCheck?.fullName.toString()
                  gender =  it.userExamCheck?.gender.toString()
                   age =  it.userExamCheck?.age.toString()
                   phone =  it.userExamCheck?.phone.toString()
                    address = it.userExamCheck?.address.toString()

                    Log.e("TAG", "observerShow55: ${it.userExamCheck?.fullName.toString()}")
                    Log.e("TAG", "observerShow55: ${it.userExamCheck?.gender.toString()}")
                    Log.e("TAG", "observerShow55: ${it.userExamCheck?.address.toString()}")
                    Log.e("TAG", "observerShow55: ${it.userExamCheck?.phone.toString()}")
                    Log.e("TAG", "observerShow55: ${it.userExamCheck?.age.toString()}")

                    Log.e("TAG", "this is iit : $it")

                }

            })
    }


    override fun onOptionClick(item: OptionItem,qId: String) {

//        answer = item.name.toString()
//        this.qId = qId.toString()
//
//      //  Toast.makeText(this, "${item.name}", Toast.LENGTH_SHORT).show()
//        Log.e("TAG9999", "onOptionClick: ${item.name+""+qId} ", )

    var model = SubmitAnswersModel(item,qId)

    try {


        if (!submitList.isNullOrEmpty()) {

            for (i in 0 until submitList.size) {

                if (submitList.get(i).questionId.equals(qId)) {

                    Log.e("TAG77", "onOptionClick: " + submitList.get(i).questionId.equals(qId))

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

        Log.e("TAG444", "onOptionClick: $e")

    }

}

    override fun onSurveyId(surveyId: String) {

        var model = SurvIdModel(surveyId)
        survIdList.add(model)

        try {


            if (!survIdList.isNullOrEmpty()) {

                for (i in 0 until survIdList.size) {

                    if (survIdList.get(i).equals(survId)) {

                        Log.e("TAG77", "onOptionClick: " + survIdList.get(i).equals(survId))

                        survIdList.removeAt(i)
                        survIdList.add(model)
                        return
                    }

                }


                survIdList.add(model)


            } else {

                this.survId = surveyId
                survIdList.add(SurvIdModel(survId))

            }
        }catch (e:Exception){

            Log.e("TAG444", "onOptionClick: $e")

        }

    }

}