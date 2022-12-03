package com.example.data.base

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.text.*
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.surveyapp.BuildConfig
import com.example.surveyapp.R

open class BaseFragment : Fragment(), View.OnClickListener {

    val TAG = BaseFragment::class.java.simpleName

    var mDialog: AlertDialog? = null

    lateinit var activity:BaseActivity
   // lateinit var mRewardedVideoAd: RewardedVideoAd
    //lateinit var db:FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity = getActivity() as BaseActivity;
       // db= FirebaseFirestore.getInstance()
        //mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(activity)
        //mRewardedVideoAd.loadAd(getString(R.string.video_ad_unit_id), adRequest)
    }

    /** function will be implemented in Child classes whenever required  **/
    override fun onClick(v: View?) {}



    open fun isInternetConnection(): Boolean {
        val connectivityManager =
            activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo!!.isConnectedOrConnecting
    }

/*
    fun handleProgressLoader(isLoading: Boolean){
        if (progress_bar==null){
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            return
        }

        if (isLoading) {
            progress_bar.visibility = View.VISIBLE
            activity.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }else {
            progress_bar.visibility = View.GONE
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
    }
*/




    fun showAlertDialog(
        message: String?,
        positive: String,
        negative: String,
        neutral: String,
        cancalable:Boolean,
        listener: DialogInterface.OnClickListener?) {

        val builder = AlertDialog.Builder(activity)
        builder.setTitle(getString(R.string.app_name))
        builder.setMessage(message)
        if (!TextUtils.isEmpty(positive)) {
            builder.setPositiveButton(positive, listener)
        }
        if (!TextUtils.isEmpty(negative)) {
            builder.setNegativeButton(negative, listener)
        }
        if (!TextUtils.isEmpty(neutral)) {
            builder.setNeutralButton(neutral, listener)
        }
        if (mDialog != null) {
            mDialog!!.dismiss()
        }
        mDialog = builder.create()

        mDialog!!.setOnShowListener(object : DialogInterface.OnShowListener {
            override fun onShow(dialog: DialogInterface?) {
                mDialog!!.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(activity, R.color.teal_700));
                mDialog!!.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(activity, R.color.black));
            }
        })
        mDialog!!.show()
    }


    fun hideKeyBoard() {
        try {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(
                activity.findViewById<View>(android.R.id.content).windowToken,
                0
            )
        } catch (e: Exception) {
            printLog("BaseActivity", e.toString())
        }

    }

    fun checkForInternet(context: Context): Boolean {

        // register activity with the connectivity manager service
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // if the android version is equal to M
        // or greater we need to use the
        // NetworkCapabilities to check what type of
        // network has the internet connection
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // Returns a Network object corresponding to
            // the currently active default data network.
            val network = connectivityManager.activeNetwork ?: return false

            // Representation of the capabilities of an active network.
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                // Indicates this network uses a Wi-Fi transport,
                // or WiFi has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                // Indicates this network uses a Cellular transport. or
                // Cellular has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                // else return false
                else -> false
            }
        } else {
            // if the android version is below M
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }

    /*fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
        val safeClickListener = SafeClickListener {
            onSafeClick(it)
        }
        setOnClickListener(safeClickListener)
    }*/

    fun printLog(tag: String, log: String) {
        if (BuildConfig.DEBUG)
            Log.d(tag, log)
    }


    fun showToast(msg: String) {

        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }



   /* fun logOutFromApp(){
        showAlertDialog(getString(R.string.are_you_sure_to_logout),getString(R.string.yes),getString(R.string.no),"",true,DialogInterface.OnClickListener { _, which ->
            when (which) {
                DialogInterface.BUTTON_POSITIVE -> {
                    hitLogoutAPI()
                }
            }
        })
    }


    open fun hitLogoutAPI() {
       // mPrefs?.clearUserDetails()
        restartApp()
    }*/

    fun restartApp(){
        val i = activity.packageManager.getLaunchIntentForPackage(activity.packageName)
        i!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        activity.finish()
        startActivity(i)
    }



    override fun onDestroyView() {
        super.onDestroyView()

    }
}
