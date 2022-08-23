package com.example.surveyapp.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.survey.ResponseItem
import com.example.surveyapp.HomeViewModel
import com.example.surveyapp.adapter.RecyclerAdapterSurveyList
import com.example.surveyapp.activity.DownloadedSurveyActivity
import com.example.surveyapp.databinding.SurveyListBinding

class SurveyListFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    lateinit var binding : SurveyListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        binding = SurveyListBinding.inflate(inflater,container,false)

        binding.idText.setOnClickListener {

            val  intent = Intent(context, DownloadedSurveyActivity::class.java)
            startActivity(intent)

        }

        setObservers()
        viewModel.getSurveyList()


        return (binding.root)
    }

    private fun setObservers(){
        observeSurvey()
    }

    private fun observeSurvey(){

        viewModel.getSurveyLiveData().observe(requireActivity(), Observer {

            //showToast("succes")

            if (it != null) {

//                activity?.findNavController(R.id.nav_host_fragment_content_main)
//                    ?.navigate(R.id.redirect_navigation_surveyListQuestionsFragment)

                binding.recyclerView.adapter = RecyclerAdapterSurveyList(it.response as ArrayList<ResponseItem>,requireContext())

            }

        })
    }

}