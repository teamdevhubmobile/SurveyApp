package com.example.data.requests

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import retrofit2.http.QueryMap

data class UploadAnswerRequest(
    @Expose
    @SerializedName("loginuserID")
    val loginuserID: String,
    @Expose
    @SerializedName("onlineExamID")
    val onlineExamID: String,
    @Expose
    @SerializedName("full_name")
    val full_name: String,
    @Expose
    @SerializedName("gender")
    val gender: String,
    @Expose
    @SerializedName("phone")
    val phone: String,
    @Expose
    @SerializedName("age")
    val age: String,
    @Expose
    @SerializedName("address")
    val address: String,

  /*  @Expose
    @SerializedName("answer")
    val answer: HashMap<String,String>,*/

    )
