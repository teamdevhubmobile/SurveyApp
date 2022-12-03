package com.example.data.response

import com.google.gson.annotations.SerializedName

data class UploadedSurveyListResponse(

	@field:SerializedName("response")
	val response: Response? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class UploadedSurveyListResponseDataItem(

	@field:SerializedName("arr")
	val arr: Any? = null,

	@field:SerializedName("surveyIds")
	val surveyIds: String? = null,

	@field:SerializedName("surveyId")
	val surveyId: String? = null,

	@field:SerializedName("full_name")
	val fullName: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("age")
	val age: String? = null,

	@field:SerializedName("survey_name")
	val survey_name: String? = null
)

data class Response(

	@field:SerializedName("data")
	val data: List<UploadedSurveyListResponseDataItem?>? = null
)
