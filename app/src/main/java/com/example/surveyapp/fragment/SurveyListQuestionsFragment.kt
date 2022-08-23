package com.example.surveyapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.surveyapp.HomeViewModel
import com.example.surveyapp.R
import com.example.surveyapp.ResponseItem
import com.example.surveyapp.adapter.ViewPagerAdapter

class SurveyListQuestionsFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var viewPager: ViewPager
    private lateinit var mViewPagerAdapter: ViewPagerAdapter
    var textList = ArrayList<ResponseItem>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

      //  viewPager = findViewById(R.id.viewPager)




//        setObservers()
//        viewModel.getQuestions("")
//

        return inflater.inflate(R.layout.fragment_survey_list_questions, container, false)
    }

    private fun setObservers(){
        observeSurvey()
    }

    private fun observeSurvey(){

        viewModel.getSurveyLiveData().observe(requireActivity(), Observer {

            //showToast("succes")

            if (it != null) {

//                textList =

               // mViewPagerAdapter = ViewPagerAdapter(requireActivity(), textList)

                var viewPagerPageChangeListener: ViewPager.OnPageChangeListener =
                    object : ViewPager.OnPageChangeListener{
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

               // binding.recyclerView.adapter = RecyclerAdapterSurveyList(it.response as ArrayList<ResponseItem>,requireContext())

            }

        })
    }


}