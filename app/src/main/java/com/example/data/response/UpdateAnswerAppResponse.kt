package com.example.data.response

import com.google.gson.annotations.SerializedName

data class UpdateAnswerAppResponse(

	@field:SerializedName("data")
	val data: Any? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)
