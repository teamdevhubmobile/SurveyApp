package com.example.surveyapp.response

import com.google.gson.annotations.SerializedName

data class ForgetPasswordResponse(

	@field:SerializedName("response")
	val response: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)
