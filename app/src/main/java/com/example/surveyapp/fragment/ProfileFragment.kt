package com.example.surveyapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.surveyapp.R
import com.example.surveyapp.databinding.ProfileFragmentBinding
import com.example.surveyapp.databinding.SurveyListBinding
import com.example.surveyapp.mPrefs

class Profile : Fragment() {

    lateinit var binding: ProfileFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = ProfileFragmentBinding.inflate(inflater,container,false)

        //binding.usernameTxt.setText(mPrefs.prefAudioFileDetails?.filename.toString())
        Log.e("TAG", "onCreateView: ${mPrefs.prefAudioFileDetails?.filename.toString()}", )

        return binding.root
    }

}