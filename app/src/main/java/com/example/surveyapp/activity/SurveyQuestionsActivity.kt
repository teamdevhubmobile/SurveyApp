package com.example.surveyapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.surveyapp.*
import com.example.surveyapp.databinding.QuestionsActivityBinding
import com.example.surveyapp.interfaces.OptionsListenerInterface
import com.example.surveyapp.utils.Dbhelper
import com.example.surveyapp.adapter.ViewPagerAdapter

class SurveyQuestionsActivity : AppCompatActivity(),OptionsListenerInterface {

    lateinit var binding: QuestionsActivityBinding
    private lateinit var viewModel: HomeViewModel
    var id = ""
    var nid = ""
    private lateinit var viewPager: ViewPager
     lateinit var mViewPagerAdapter: ViewPagerAdapter
    var textListt = ArrayList<OptionItem>()
    var qId = ""
    var answer = ""
    val dbhelper  = Dbhelper(this,null)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_survey_ouestions)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)


        binding.submitBtn.setOnClickListener {

            dbhelper.answerSubmit(qId,answer)

        }

          /*  textList.add("Slide 1")
            textList.add("Slide 2")
            textList.add("Slide 3")
            textList.add("Slide 4")
            textList.add("dff")*/

        viewPager = findViewById(R.id.viewPager)


        setObservers()
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
            }

            private fun observeQuestion() {

                viewModel.getQuestionsLiveData()
                    .observe(this@SurveyQuestionsActivity, Observer {

                        //showToast("succes")

                        if (it != null) {

                            Log.e("TAG", "this is it : $it", )



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

    override fun onOptionClick(item: OptionItem,qId: String) {

        answer = item.name.toString()
        this.qId = qId.toString()

        Toast.makeText(this, "${item.name}", Toast.LENGTH_SHORT).show()
        Log.e("TAG9999", "onOptionClick: ${item.name+""+qId} ", )
    }

}