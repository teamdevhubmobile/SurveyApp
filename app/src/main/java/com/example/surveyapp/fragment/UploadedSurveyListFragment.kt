package com.example.surveyapp.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.data.response.UploadedSurveyListResponseDataItem
import com.example.survey.ResponseItem
import com.example.surveyapp.HomeViewModel
import com.example.surveyapp.adapter.RecyclerAdapterSurveyList
import com.example.surveyapp.adapter.UploadedSurveyListRecyclerAdapter
import com.example.surveyapp.utils.Dbhelper
import com.example.surveyapp.databinding.DownloadedSurveyBinding
import com.example.surveyapp.mPrefs


class UploadedSurveyListFragment : Fragment() {

    lateinit var binding: DownloadedSurveyBinding
    private lateinit var viewModel: HomeViewModel

    @SuppressLint("Range")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DownloadedSurveyBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        setObservers()

        val userId = mPrefs.prefUserDetails?.userdata?.loginuserID.toString()

        Log.e("TAG8990", "onCreate: ${userId}" )

        viewModel.getUploadedSurveyList(userId)

        return binding.root
    }

    private fun setObservers(){
        observeSurvey()
    }

    private fun observeSurvey(){

        viewModel.getUploadedSurveyListLiveData().observe(requireActivity(), Observer {

            if (it != null) {

                try {
                    binding.uploadSurveyListRecycler.adapter =

                        UploadedSurveyListRecyclerAdapter(it.response?.data as ArrayList<UploadedSurveyListResponseDataItem>,requireContext())

                }catch (e : Exception){

                    Log.e("TAG9090", "observeSurvey: ${e.message}", )
                }
            }

        })
    }
}