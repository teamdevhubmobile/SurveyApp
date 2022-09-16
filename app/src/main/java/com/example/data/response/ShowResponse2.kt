package com.example.data.response

import com.google.gson.annotations.SerializedName

data class ShowResponse2(

	/*@field:SerializedName("onlineExam")
	val ShowResponse2onlineExam: OnlineExam? = null,*/

	/*@field:SerializedName("onlineExamQuestions")
	val onlineExamQuestions: List<Any?>? = null,*/

	@field:SerializedName("userExamCheck")
	val userExamCheck: ShowResponse2UserExamCheck? = null,

	/*@field:SerializedName("questions")
	val questions: ShowResponse2Questions? = null,

	@field:SerializedName("options")
	val options: List<Any?>? = null,

	@field:SerializedName("answers")
	val answers: List<Any?>? = null,

	@field:SerializedName("online_exam")
	val onlineExam: ShowResponse2OnlineExam? = null*/
)

data class ShowResponse2JsonMember19(

	@field:SerializedName("create_userID")
	val createUserID: String? = null,

	@field:SerializedName("question")
	val question: String? = null,

	@field:SerializedName("totalOption")
	val totalOption: String? = null,

	@field:SerializedName("upload")
	val upload: String? = null,

	@field:SerializedName("hints")
	val hints: String? = null,

	@field:SerializedName("groupID")
	val groupID: String? = null,

	@field:SerializedName("explanation")
	val explanation: String? = null,

	@field:SerializedName("create_usertypeID")
	val createUsertypeID: String? = null,

	@field:SerializedName("parentID")
	val parentID: String? = null,

	@field:SerializedName("subjectID")
	val subjectID: Any? = null,

	@field:SerializedName("questionBankID")
	val questionBankID: String? = null,

	@field:SerializedName("typeNumber")
	val typeNumber: String? = null,

	@field:SerializedName("levelID")
	val levelID: String? = null,

	@field:SerializedName("time")
	val time: String? = null,

	@field:SerializedName("create_date")
	val createDate: String? = null,

	@field:SerializedName("modify_date")
	val modifyDate: String? = null,

	@field:SerializedName("mark")
	val mark: Any? = null,

	@field:SerializedName("totalQuestion")
	val totalQuestion: String? = null
)

data class ShowResponse2JsonMember18(

	@field:SerializedName("create_userID")
	val createUserID: String? = null,

	@field:SerializedName("question")
	val question: String? = null,

	@field:SerializedName("totalOption")
	val totalOption: String? = null,

	@field:SerializedName("upload")
	val upload: String? = null,

	@field:SerializedName("hints")
	val hints: String? = null,

	@field:SerializedName("groupID")
	val groupID: String? = null,

	@field:SerializedName("explanation")
	val explanation: String? = null,

	@field:SerializedName("create_usertypeID")
	val createUsertypeID: String? = null,

	@field:SerializedName("parentID")
	val parentID: String? = null,

	@field:SerializedName("subjectID")
	val subjectID: Any? = null,

	@field:SerializedName("questionBankID")
	val questionBankID: String? = null,

	@field:SerializedName("typeNumber")
	val typeNumber: String? = null,

	@field:SerializedName("levelID")
	val levelID: String? = null,

	@field:SerializedName("time")
	val time: String? = null,

	@field:SerializedName("create_date")
	val createDate: String? = null,

	@field:SerializedName("modify_date")
	val modifyDate: String? = null,

	@field:SerializedName("mark")
	val mark: Any? = null,

	@field:SerializedName("totalQuestion")
	val totalQuestion: String? = null
)

data class ShowResponse2OnlineExam(

	@field:SerializedName("create_userID")
	val createUserID: String? = null,

	@field:SerializedName("examTypeNumber")
	val examTypeNumber: String? = null,

	@field:SerializedName("img")
	val img: Any? = null,

	@field:SerializedName("description")
	val description: Any? = null,

	@field:SerializedName("sectionID")
	val sectionID: String? = null,

	@field:SerializedName("schoolYearID")
	val schoolYearID: String? = null,

	@field:SerializedName("create_usertypeID")
	val createUsertypeID: String? = null,

	@field:SerializedName("point")
	val point: String? = null,

	@field:SerializedName("duration")
	val duration: String? = null,

	@field:SerializedName("random")
	val random: String? = null,

	@field:SerializedName("public")
	val jsonMemberPublic: String? = null,

	@field:SerializedName("markType")
	val markType: String? = null,

	@field:SerializedName("percentage")
	val percentage: String? = null,

	@field:SerializedName("instructionID")
	val instructionID: String? = null,

	@field:SerializedName("onlineExamID")
	val onlineExamID: String? = null,

	@field:SerializedName("judge")
	val judge: String? = null,

	@field:SerializedName("create_date")
	val createDate: String? = null,

	@field:SerializedName("userTypeID")
	val userTypeID: String? = null,

	@field:SerializedName("cost")
	val cost: String? = null,

	@field:SerializedName("examStatus")
	val examStatus: String? = null,

	@field:SerializedName("showMarkAfterExam")
	val showMarkAfterExam: String? = null,

	@field:SerializedName("published")
	val published: String? = null,

	@field:SerializedName("endDateTime")
	val endDateTime: Any? = null,

	@field:SerializedName("subjectID")
	val subjectID: String? = null,

	@field:SerializedName("bonusMark")
	val bonusMark: String? = null,

	@field:SerializedName("examfor")
	val examfor: String? = null,

	@field:SerializedName("classID")
	val classID: String? = null,

	@field:SerializedName("negativeMark")
	val negativeMark: String? = null,

	@field:SerializedName("startDateTime")
	val startDateTime: Any? = null,

	@field:SerializedName("levelID")
	val levelID: Any? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("paid")
	val paid: String? = null,

	@field:SerializedName("studentGroupID")
	val studentGroupID: String? = null,

	@field:SerializedName("modify_date")
	val modifyDate: String? = null,

	@field:SerializedName("courseid")
	val courseid: Any? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("validDays")
	val validDays: String? = null
)

data class ShowResponse2JsonMember23(

	@field:SerializedName("create_userID")
	val createUserID: String? = null,

	@field:SerializedName("question")
	val question: String? = null,

	@field:SerializedName("totalOption")
	val totalOption: String? = null,

	@field:SerializedName("upload")
	val upload: String? = null,

	@field:SerializedName("hints")
	val hints: String? = null,

	@field:SerializedName("groupID")
	val groupID: String? = null,

	@field:SerializedName("explanation")
	val explanation: String? = null,

	@field:SerializedName("create_usertypeID")
	val createUsertypeID: String? = null,

	@field:SerializedName("parentID")
	val parentID: String? = null,

	@field:SerializedName("subjectID")
	val subjectID: Any? = null,

	@field:SerializedName("questionBankID")
	val questionBankID: String? = null,

	@field:SerializedName("typeNumber")
	val typeNumber: String? = null,

	@field:SerializedName("levelID")
	val levelID: String? = null,

	@field:SerializedName("time")
	val time: String? = null,

	@field:SerializedName("create_date")
	val createDate: String? = null,

	@field:SerializedName("modify_date")
	val modifyDate: String? = null,

	@field:SerializedName("mark")
	val mark: Any? = null,

	@field:SerializedName("totalQuestion")
	val totalQuestion: String? = null
)

data class ShowResponse2JsonMember21(

	@field:SerializedName("create_userID")
	val createUserID: String? = null,

	@field:SerializedName("question")
	val question: String? = null,

	@field:SerializedName("totalOption")
	val totalOption: String? = null,

	@field:SerializedName("upload")
	val upload: String? = null,

	@field:SerializedName("hints")
	val hints: String? = null,

	@field:SerializedName("groupID")
	val groupID: String? = null,

	@field:SerializedName("explanation")
	val explanation: String? = null,

	@field:SerializedName("create_usertypeID")
	val createUsertypeID: String? = null,

	@field:SerializedName("parentID")
	val parentID: String? = null,

	@field:SerializedName("subjectID")
	val subjectID: Any? = null,

	@field:SerializedName("questionBankID")
	val questionBankID: String? = null,

	@field:SerializedName("typeNumber")
	val typeNumber: String? = null,

	@field:SerializedName("levelID")
	val levelID: String? = null,

	@field:SerializedName("time")
	val time: String? = null,

	@field:SerializedName("create_date")
	val createDate: String? = null,

	@field:SerializedName("modify_date")
	val modifyDate: String? = null,

	@field:SerializedName("mark")
	val mark: Any? = null,

	@field:SerializedName("totalQuestion")
	val totalQuestion: String? = null
)

data class ShowResponse2Questions(

	@field:SerializedName("22")
	val jsonMember22: ShowResponse2JsonMember22? = null,

	@field:SerializedName("23")
	val jsonMember23: ShowResponse2JsonMember23? = null,

	@field:SerializedName("18")
	val jsonMember18: ShowResponse2JsonMember18? = null,

	@field:SerializedName("19")
	val jsonMember19: ShowResponse2JsonMember19? = null,

	@field:SerializedName("20")
	val jsonMember20: ShowResponse2JsonMember20? = null,

	@field:SerializedName("21")
	val jsonMember21: ShowResponse2JsonMember21? = null
)

data class ShowResponse2JsonMember22(

	@field:SerializedName("create_userID")
	val createUserID: String? = null,

	@field:SerializedName("question")
	val question: String? = null,

	@field:SerializedName("totalOption")
	val totalOption: String? = null,

	@field:SerializedName("upload")
	val upload: String? = null,

	@field:SerializedName("hints")
	val hints: String? = null,

	@field:SerializedName("groupID")
	val groupID: String? = null,

	@field:SerializedName("explanation")
	val explanation: String? = null,

	@field:SerializedName("create_usertypeID")
	val createUsertypeID: String? = null,

	@field:SerializedName("parentID")
	val parentID: String? = null,

	@field:SerializedName("subjectID")
	val subjectID: Any? = null,

	@field:SerializedName("questionBankID")
	val questionBankID: String? = null,

	@field:SerializedName("typeNumber")
	val typeNumber: String? = null,

	@field:SerializedName("levelID")
	val levelID: String? = null,

	@field:SerializedName("time")
	val time: String? = null,

	@field:SerializedName("create_date")
	val createDate: String? = null,

	@field:SerializedName("modify_date")
	val modifyDate: String? = null,

	@field:SerializedName("mark")
	val mark: Any? = null,

	@field:SerializedName("totalQuestion")
	val totalQuestion: String? = null
)

data class ShowResponse2JsonMember20(

	@field:SerializedName("create_userID")
	val createUserID: String? = null,

	@field:SerializedName("question")
	val question: String? = null,

	@field:SerializedName("totalOption")
	val totalOption: String? = null,

	@field:SerializedName("upload")
	val upload: String? = null,

	@field:SerializedName("hints")
	val hints: String? = null,

	@field:SerializedName("groupID")
	val groupID: String? = null,

	@field:SerializedName("explanation")
	val explanation: String? = null,

	@field:SerializedName("create_usertypeID")
	val createUsertypeID: String? = null,

	@field:SerializedName("parentID")
	val parentID: String? = null,

	@field:SerializedName("subjectID")
	val subjectID: Any? = null,

	@field:SerializedName("questionBankID")
	val questionBankID: String? = null,

	@field:SerializedName("typeNumber")
	val typeNumber: String? = null,

	@field:SerializedName("levelID")
	val levelID: String? = null,

	@field:SerializedName("time")
	val time: String? = null,

	@field:SerializedName("create_date")
	val createDate: String? = null,

	@field:SerializedName("modify_date")
	val modifyDate: String? = null,

	@field:SerializedName("mark")
	val mark: Any? = null,

	@field:SerializedName("totalQuestion")
	val totalQuestion: String? = null
)

data class ShowResponse2UserExamCheck(

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("sectionID")
	val sectionID: String? = null,

	@field:SerializedName("answer_option")
	val answerOption: String? = null,

	@field:SerializedName("userID")
	val userID: String? = null,

	@field:SerializedName("duration")
	val duration: String? = null,

	@field:SerializedName("score")
	val score: String? = null,

	@field:SerializedName("approved")
	val approved: String? = null,

	@field:SerializedName("examtimeID")
	val examtimeID: String? = null,

	@field:SerializedName("statusID")
	val statusID: String? = null,

	@field:SerializedName("percentage")
	val percentage: String? = null,

	@field:SerializedName("totalObtainedMark")
	val totalObtainedMark: String? = null,

	@field:SerializedName("onlineExamID")
	val onlineExamID: String? = null,

	@field:SerializedName("audio")
	val audio: String? = null,

	@field:SerializedName("totalPercentage")
	val totalPercentage: String? = null,

	@field:SerializedName("totalQuestion")
	val totalQuestion: String? = null,

	@field:SerializedName("arr")
	val arr: Any? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("onlineExamUserStatus")
	val onlineExamUserStatus: String? = null,

	@field:SerializedName("totalMark")
	val totalMark: String? = null,

	@field:SerializedName("nagetiveMark")
	val nagetiveMark: String? = null,

	@field:SerializedName("classesID")
	val classesID: String? = null,

	@field:SerializedName("totalCurrectAnswer")
	val totalCurrectAnswer: String? = null,

	@field:SerializedName("full_name")
	val fullName: String? = null,

	@field:SerializedName("answer")
	val answer: Any? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("time")
	val time: String? = null,

	@field:SerializedName("age")
	val age: String? = null,

	@field:SerializedName("totalAnswer")
	val totalAnswer: String? = null
)
