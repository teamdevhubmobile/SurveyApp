package com.example.surveyapp

import com.google.gson.annotations.SerializedName

data class QuestionWithOptionResponse2(

	@field:SerializedName("response")
	val response: List<ResponseItem?>? = null,

	@field:SerializedName("option")
	val option: List<OptionItem?>? = null,

	@field:SerializedName("status")
	val status: String? = null
)

	data class ResponseItem(

	@field:SerializedName("questionBankID")
	val questionBankID: String? = null,

	@field:SerializedName("question")
	val question: String? = null,

	@field:SerializedName("option")
	val option: List<OptionItem?>? = null
)

   data class OptionItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("optionID")
	val optionID: String? = null
)
