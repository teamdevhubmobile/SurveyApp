package com.example.surveyapp.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.data.response.AudioModel
import com.example.survey.ResponseItem
import com.example.surveyapp.HomeViewModel
import com.example.surveyapp.OptionItem
import com.example.surveyapp.adapter.RecyclerAdapterSurveyList
import com.example.surveyapp.activity.DownloadedSurveyActivity
import com.example.surveyapp.activity.TakenSurveyActivity
import com.example.surveyapp.databinding.SurveyListBinding
import com.example.surveyapp.interfaces.OptionsListenerInterface
import com.example.surveyapp.mPrefs
import java.io.IOException

class SurveyListFragment : Fragment(),OptionsListenerInterface {

    private lateinit var viewModel: HomeViewModel
    lateinit var binding : SurveyListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        binding = SurveyListBinding.inflate(inflater,container,false)


        setObservers()
        viewModel.getSurveyList()

            binding.oflineSurveryBtn.setOnClickListener {

            val  intent = Intent(context, DownloadedSurveyActivity::class.java)
            startActivity(intent)

        }

        binding.taken.setOnClickListener {

playAudio()
        }

        return (binding.root)
    }

    private fun setObservers(){
        observeSurvey()
    }

    @SuppressLint("UseRequireInsteadOfGet")
    private fun observeSurvey(){

        viewModel.getSurveyLiveData().observe(requireActivity(), Observer {

            if (it != null) {

                try {
                    binding.recyclerView.adapter =

                        RecyclerAdapterSurveyList(it.response as ArrayList<ResponseItem>, context!!,this)

                }catch (e : Exception){

                    Log.e("TAG9090", "observeSurvey: ${e.message}", )
                }
            }

        })
    }

    override fun onOptionClick(item: OptionItem, qId: String) {

    }

    override fun onSurveyId(surveyId: String) {

        Log.e("TAG78", "onSurveyId: $surveyId", )
    }

    fun playAudio() {

        // for playing our recorded audio
        // we are using media player class.
       val mPlayer = MediaPlayer()



        try {
            mPlayer!!.setDataSource("/storage/emulated/0/Android/data/com.example.surveyapp/files/Download/Recording3.mp3")
            mPlayer!!.prepare()
            mPlayer!!.start()

        } catch (e: IOException) {
            Log.e("TAG", "prepare() failed")
        }
    }


}