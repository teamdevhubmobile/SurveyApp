package com.example.surveyapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.example.surveyapp.R
import com.example.surveyapp.databinding.DashBoardFragmentBinding
import com.example.surveyapp.mPrefs

class DashBoardFragment : Fragment() {

    lateinit var binding : DashBoardFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DashBoardFragmentBinding.inflate(inflater, container, false)

        binding.dashboardWebview.webViewClient = WebViewClient()

        binding.dashboardWebview.loadUrl("http://creatos.net/survey/apphome?user_id="+ mPrefs.prefUserDetails?.userdata?.loginuserID)

        Log.e("TAG11213", "onCreateView: ${"http://creatos.net/survey/apphome?user_id="+ mPrefs.prefUserDetails?.userdata?.loginuserID}" )

        binding.dashboardWebview.settings.javaScriptEnabled = true

        binding.dashboardWebview.settings.setSupportZoom(true)

     /*   override fun onBackPressed() {
            // if your webview can go back it will go back
            if  ( binding.dashboardWebview.canGoBack())
                binding.dashboardWebview.goBack()
            // if your webview cannot go back
            // it will exit the application
            else
                super.onBackPressed()
        }*/

        return binding.root
    }

    // if you press Back button this code will work


}