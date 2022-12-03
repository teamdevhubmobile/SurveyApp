package com.example.data.response

import com.google.gson.annotations.SerializedName

data class UploadedSurveyQuestionResponse(

	@field:SerializedName("response")
	val response: UploadedSurveyQuestionResponseResponse? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class UploadedSurveyQuestionResponseResponse(

	@field:SerializedName("question")
	val question: List<String?>? = null,

	@field:SerializedName("answer")
	val answer: List<String?>? = null,

	@field:SerializedName("user")
	val user: User? = null
)

data class User(

	@field:SerializedName("full_name")
	val fullName: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null
)
