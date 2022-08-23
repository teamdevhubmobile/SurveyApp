package com.example.surveyapp

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("userdata")
	val userdata: Userdata? = null,

	@field:SerializedName("exp")
	val exp: Int? = null,

	@field:SerializedName("iat")
	val iat: Int? = null
)

data class Userdata(

	@field:SerializedName("loggedin")
	val loggedin: Boolean? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("usertype")
	val usertype: String? = null,

	@field:SerializedName("photo")
	val photo: String? = null,

	@field:SerializedName("usertypeID")
	val usertypeID: String? = null,

	@field:SerializedName("lang")
	val lang: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("loginuserID")
	val loginuserID: String? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("defaultschoolyearID")
	val defaultschoolyearID: Int? = null
)
