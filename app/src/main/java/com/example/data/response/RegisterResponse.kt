package com.example.survey

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

	@field:SerializedName("response")
	val response: List<ResponseItem?>? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class ResponseItem(

	@field:SerializedName("surveyId")
	val surveyId: String? = null,

	@field:SerializedName("name")
	val name: String? = null
)
