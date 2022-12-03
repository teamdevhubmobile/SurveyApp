package com.example.surveyapp.utils

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import com.example.data.response.AudioModel
import com.example.surveyapp.LoginResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder


class AppPrefs (context : Context) {

    val APP_PREF_FILE = "com.cricket.premiumliveline.prefs"
    val prefs : SharedPreferences = context.getSharedPreferences(APP_PREF_FILE, 0) /** 0 --> MODE_PRIVATE **/

    val PREF_AUTH_TOKEN = "pref_auth_token"
    val PREF_USER_DETAILS = "pref_user_details"
    val PREF_Audio_File_DETAILS = "pref_audio_file_details"
    val PREF_Audio_File_Online_DETAILS = "pref_audio_file_online_details"
    val PREF_OTP_DETAILS = "pref_otp_details"
    val PREF_POST_DETAILS = "pref_post_details"
    val PREF_USER_UPDATE_DETAILS = "pref_user_update_details"



   var prefAuthToken: String?
       get() = prefs.getString(PREF_AUTH_TOKEN, "")
       set(value) = prefs.edit().putString(PREF_AUTH_TOKEN, value).apply()


    var prefUserDetails: LoginResponse?
        get() {
            if (!TextUtils.isEmpty(prefs.getString(PREF_USER_DETAILS, ""))) {
                return Gson().fromJson(prefs.getString(PREF_USER_DETAILS, ""), LoginResponse::class.java)
            } else
                return null
        }
            set(value){
                val gson = GsonBuilder().create()
                val inString = gson.toJson(value)
                prefs.edit().putString(PREF_USER_DETAILS, inString).apply()
            }

    var prefAudioFileDetails: AudioModel?
        get() {
            if (!TextUtils.isEmpty(prefs.getString(PREF_Audio_File_DETAILS, ""))) {
                return Gson().fromJson(prefs.getString(PREF_Audio_File_DETAILS, ""), AudioModel::class.java)
            } else
                return null
        }
            set(value){
                val gson = GsonBuilder().create()
                val inString = gson.toJson(value)
                prefs.edit().putString(PREF_Audio_File_DETAILS, inString).apply()
            }


    fun clearUserDetails(){
        prefs.edit().clear().apply()
    }
}
