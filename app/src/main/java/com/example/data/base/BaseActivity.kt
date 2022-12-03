package com.example.data.base

import android.Manifest
import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.surveyapp.BuildConfig
import com.example.surveyapp.R
import com.google.android.material.snackbar.Snackbar
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.util.*

public open class BaseActivity : AppCompatActivity(), View.OnClickListener {

  //  val TAG = BaseActivity::class.java.simpleName
    val REQ_IMAGE_PERM = 22
    lateinit var progressDialog: ProgressDialog

    var mDialog: AlertDialog? = null
    lateinit var handler: Handler


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Use an activity context to get the rewarded video instance.
       // mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this)
       // initAwsImageUploading()
       // db= FirebaseFirestore.getInstance()
        handler = Handler(Looper.getMainLooper())
    }



    /** function will be implemented in Child classes whenever required  **/
    override fun onClick(v: View?) { }




/*
    fun handleProgressLoader(isLoading: Boolean){
        if (progress_bar==null){
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            return
        }

        if (isLoading) {
            progress_bar.visibility = View.VISIBLE
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }else {
            progress_bar.visibility = View.GONE
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
    }
*/

    open fun isInternetConnection(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo!!.isConnectedOrConnecting
    }

    fun showAlertDialog(
            message: String?,
            positive: String,
            negative: String,
            neutral: String,
            listener: DialogInterface.OnClickListener?) {

        val builder = AlertDialog.Builder(this)
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
                mDialog!!.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(this@BaseActivity, R.color.teal_700));
                mDialog!!.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(this@BaseActivity, R.color.black));
            }
        })
        mDialog!!.show()
    }



    fun hideKeyBoard() {
        try {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(findViewById<View>(android.R.id.content).windowToken, 0)
        } catch (e: Exception) {
            printLog("BaseActivity", e.toString())
        }

    }

    fun printLog(tag: String, log: String) {
        if (BuildConfig.DEBUG)
            Log.d(tag, log)
    }
    fun showToast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }


    fun withBody(s: String): RequestBody? {
        return RequestBody.create(MultipartBody.FORM, "" + s)
    }

    open fun getBodyFromFile(f: File, paramName:String): MultipartBody.Part? {
        return if (f == null) null else { //File file=new File(uri.getPath());
            val  extension= f.extension
            Log.e("TAG453", "getBodyFromFile: $extension", )
            val requestFile = RequestBody.create("image/${extension}".toMediaTypeOrNull(), f)
            Log.e("ImageFile size", "" + f.length())
            // RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), f);
            //  RequestBody formBody = new FormBody.Builder().add("search", "Jurassic Park").build();
            MultipartBody.Part.createFormData(paramName, f.name, requestFile)
        }
    }



  open fun getBodyFromAudioFile(f: File, paramName:String): MultipartBody.Part? {
        return if (f == null) null else { //File file=new File(uri.getPath());
            val  extension= f.extension
            Log.e("TAG453", "getBodyFromFile: $extension", )
            val requestFile = RequestBody.create("audio/mp3".toMediaTypeOrNull(), f)
           // val requestFile = RequestBody.create("audio/${extension}".toMediaTypeOrNull(), f)
            Log.e("ImageFile size", "" + f.length())
            // RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), f);
            //  RequestBody formBody = new FormBody.Builder().add("search", "Jurassic Park").build();
            MultipartBody.Part.createFormData(paramName, f.name, requestFile)
        }
    }




    /* fun logOutFromApp(){
         showAlertDialog(getString(R.string.are_you_sure_to_logout), getString(R.string.yes), getString(R.string.no), "", DialogInterface.OnClickListener { _, which ->
             when (which) {
                 DialogInterface.BUTTON_POSITIVE -> {
                     hitLogoutAPI()
                 }
             }
         })
     }

     open fun hitLogoutAPI() {

         // any api call is here otherwise restart//
         mPrefs.clearUserDetails()
         restartApp()
     }*/

    fun restartApp(){
        val i = baseContext.packageManager.getLaunchIntentForPackage(baseContext.packageName)
        i!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        finish()
        startActivity(i)
    }

}
