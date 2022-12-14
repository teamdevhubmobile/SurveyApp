package com.example.surveyapp

import com.example.data.response.OptionCheckModel
import com.google.gson.annotations.SerializedName
import java.text.FieldPosition

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

		@field:SerializedName("typeNumber")
	val typeNumber: String? = null,

		@field:SerializedName("option")
	val option: List<OptionItem?>? = null,

		var ansListCheck: List<OptionCheckModel>? = null


)

   data class OptionItem(

	   @field:SerializedName("name")
	val name: String? = null,

	   @field:SerializedName("optionID")
	val optionID: String? = null,

	   var answers: String? = null,
	   var position: Int? = null
)
